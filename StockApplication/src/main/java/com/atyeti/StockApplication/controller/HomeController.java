package com.atyeti.StockApplication.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atyeti.StockApplication.model.Company;
import com.atyeti.StockApplication.model.OrderHistory;
import com.atyeti.StockApplication.model.Role;
import com.atyeti.StockApplication.model.User;
import com.atyeti.StockApplication.model.UserFunds;
import com.atyeti.StockApplication.model.UserOrder;
import com.atyeti.StockApplication.model.UserPayment;
import com.atyeti.StockApplication.model.UserRole;
import com.atyeti.StockApplication.repo.UserFundsRepository;
import com.atyeti.StockApplication.repo.UserPaymentRepository;
import com.atyeti.StockApplication.service.CompanyService;
import com.atyeti.StockApplication.service.OrderHistoryService;
import com.atyeti.StockApplication.service.UserOrderService;
import com.atyeti.StockApplication.service.UserService;
import com.atyeti.StockApplication.serviceImpl.SmsService;
import com.atyeti.StockApplication.serviceImpl.UserFundsServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private UserFundsRepository userFundsRepository;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private UserOrderService userOrderService;

	@Autowired
	private OrderHistoryService orderHistoryService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private UserPaymentRepository userPaymentRepository;

	@ResponseBody
	@PostMapping("/addCompanyDetails")
	public List<Company> addCompanyDetails(Company company) {
		System.out.println("HIIII");
		return companyService.addCompanyDetails(company);
	}

	@GetMapping("/login")
	public String loginPage() {
		return "register";
	}

	@GetMapping(path = "/vai{id}")
	public List<Company> addC() {
		return companyService.getAll();

	}

	@GetMapping("/register")
	public String register(Model model) {

		return "register";
	}

	@GetMapping("/order")
	public String userOrder(Model model) {
		return "order";
	}

	@GetMapping("/")
	public String home(Model model) {
		System.out.println(companyService.findCompanyDetails().size());
		model.addAttribute("companyList", companyService.findCompanyDetails());
		return "home";
	}

	@PostMapping("/newUser")
	public String newUser(String firstname, String username, String email, String password, String phoneNo,
			Model model) {
		System.out.println("firstname :" + firstname);
		System.out.println("username :" + username);
		System.out.println("email :" + email);
		System.out.println("password :" + password);
		System.out.println("phoneNo :" + phoneNo);

		if (userService.findByusername(username) != null) {
			model.addAttribute("usernameExists", true);
			return "register";
		}

		if (userService.findByEmail(email) != null) {
			model.addAttribute("emailExists", true);
			return "register";
		}

		User user = new User();

		user.setEmail(email);
		user.setName(firstname);
		user.setPassword(passwordEncoder.encode(password));
		user.setPhoneNo(phoneNo);
		user.setUsername(username);
		Role role = new Role();
		role.setRoleId(1);
		role.setName("ROLE_USER");

		Set<UserRole> userRoles = new HashSet<>();

		userRoles.add(new UserRole(user, role));

		userService.saveUserDetails(user, userRoles);
		model.addAttribute("registerSuccess", true);

		return "register";

	}

	@PostMapping("/doOrder")
	public String doOrder(Company company, int quantity, Principal principle, Model model) {
		User user = userService.findByusername(principle.getName());
		UserFunds userFunds = user.getUserFunds();
		Company companyDetails = companyService.findCompanyDetailsById(company);
		if (companyDetails.getAvailableStock() < quantity) {
			model.addAttribute("stocksNotAvilable", true);
			model.addAttribute("companyList", companyService.findCompanyDetails());

			return "home";
		}
		if (userFunds.getAmmount() < companyDetails.getPricePerStock() * quantity) {
			model.addAttribute("lessFunds", true);
			model.addAttribute("companyList", companyService.findCompanyDetails());
			return "home";
		}
		List<UserOrder> userOrderList = user.getUserOrderList();

		for (UserOrder userOrder : userOrderList) {

			if (userOrder.getCompany().getId().longValue() == company.getId().longValue()) {
				System.out.println("update ");
				model.addAttribute("companyList", companyService.findCompanyDetails());
				model.addAttribute("orderUpdate", true);

				return "home";
			}
		}

		UserOrder userOrder = new UserOrder();
		UserOrder newuserOrder = userOrderService.createUserOrder(userOrder, user, companyDetails, quantity, userFunds);
		model.addAttribute("orderPlaced", true);
		model.addAttribute("companyList", companyService.findCompanyDetails());
		//smsService.send(newuserOrder, user);

		return "home";
	}

	@GetMapping("/companys")
	public String getCompanyData(Model model) {

		model.addAttribute("companyList", companyService.findCompanyDetails());
		List<Company> companyList = companyService.findCompanyDetails();

		for (Company company : companyList) {

		}
		return "order";
	}

	@GetMapping("/getUserOrder")
	public String getUserOrderDetails(Model model, Principal principal) {

		User user = userService.findByusername(principal.getName());

		List<UserOrder> userOrderList = user.getUserOrderList();

		model.addAttribute("userOrderList", userOrderList);

		return "order";

	}

	@GetMapping("/sellStock")
	public String userSellStocks(Model model, Principal principal, @RequestParam("userOrderId") Long orderId) {
		System.out.println("orderId :: " + orderId);
		User user = userService.findByusername(principal.getName());
		UserOrder userOrder = userOrderService.findUserOrder(orderId);
		List<OrderHistory> orderHistoryList = orderHistoryService.createOrderHistory(userOrder, user);
		model.addAttribute("orderHistoryList", orderHistoryList);
		model.addAttribute("ordersell", true);
		return "orderhistory";

	}

	@GetMapping("/orderHistory")
	public String userOrderHistory(Model model, Principal principal) {
		User user = userService.findByusername(principal.getName());
		List<OrderHistory> orderHistoryList = user.getUserOrderHistoryList();
		model.addAttribute("orderHistoryList", orderHistoryList);
		return "orderhistory";

	}

	@GetMapping("/addFunds")
	public String useraddFunds(Model model, Principal principal) {
		User user = userService.findByusername(principal.getName());

		model.addAttribute("userFunds", user.getUserFunds());
		return "addFunds";

	}

	@PostMapping("/updateOrder")
	public String userUpdateStocks(Model model, Principal principal, UserOrder userOrder, int quantity) {
		System.out.println("orderId :: " + userOrder.getId());
		System.out.println("quantity :: " + quantity);

		User user = userService.findByusername(principal.getName());
		UserOrder newUserOrder = userOrderService.findUserOrder(userOrder.getId());

		userOrderService.updateUserOrder(user, newUserOrder, newUserOrder.getCompany(), quantity, user.getUserFunds());

		model.addAttribute("userOrderList", user.getUserOrderList());

		model.addAttribute("orderUpdate", true);
		return "order";

	}

	@PostMapping("/addUserFunds")

	public String addUserFunds(Model model, Principal principle, String cardname, String cardnumber, String expmonth,
			String expyear, String cvv, Double ammount) {

		User user = userService.findByusername(principle.getName());

		UserPayment userPayment = new UserPayment();

		userPayment.setCardNumber(cardnumber);
		userPayment.setCardUserName(cardname);
		userPayment.setExpDate(expyear);
		userPayment.setCvvNo(cvv);
		userPayment.setUser(user);
		userPaymentRepository.save(userPayment);

		UserFunds userFunds = user.getUserFunds();
		userFunds.setAmmount(ammount + userFunds.getAmmount());

		userFundsRepository.save(userFunds);
		
		model.addAttribute("userFunds", user.getUserFunds());

		model.addAttribute("addfunds", true);

		return "addFunds";

	}

}

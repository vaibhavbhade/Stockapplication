package com.atyeti.StockApplication.serviceImpl;

import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atyeti.StockApplication.model.User;
import com.atyeti.StockApplication.model.UserFunds;
import com.atyeti.StockApplication.model.UserRole;
import com.atyeti.StockApplication.repo.RoleRepository;
import com.atyeti.StockApplication.repo.UserRepository;
import com.atyeti.StockApplication.service.UserService;


@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepostiory;
	

@Transactional
	@Override
	public User saveUserDetails(User user, Set<UserRole> userRoles) {
		User localUser = userRepository.findByusername(user.getUsername());

		if (localUser != null) {
			// throw new Exception("User already exists.Nothing will be done.");
			// LOG.info("User {} already exists.Nothing will be done.",user.getUsername());
		} else {
			for (UserRole ur : userRoles) {
				roleRepostiory.save(ur.getRole());
			}

		}

		user.getUserRoles().addAll(userRoles);
		
		UserFunds userFunds=new UserFunds();
		
		userFunds.setUser(user);
		userFunds.setAmmount(0.0);
		user.setUserFunds(userFunds);
		localUser = userRepository.save(user);

		
		return localUser;
	}



	@Override
	public User findByusername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByusername(username);
	}



	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByemail(email);
	}

	
}

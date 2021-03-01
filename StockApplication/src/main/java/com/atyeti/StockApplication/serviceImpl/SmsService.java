package com.atyeti.StockApplication.serviceImpl;

import org.springframework.stereotype.Component;

import com.atyeti.StockApplication.model.User;
import com.atyeti.StockApplication.model.UserOrder;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Component
public class SmsService {
	   private final String ACCOUNT_SID ="ACec46cfd6118b1daa185c309c18cf3a76";

	    private final String AUTH_TOKEN = "f6e173977b2f60da764da9d6384a7d7d";

	    private final String FROM_NUMBER = "+16036050722";

	    public void send(UserOrder newuserOrder, User user) {
	    	String msg="Dear "+user.getName()+" traded value for "+newuserOrder.getOrderDate()+" cm Rs "+newuserOrder.getFinalprice()+" check your registred email. for details contact us.";
	    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	        Message message = Message.creator(new PhoneNumber(user.getPhoneNo()), new PhoneNumber(FROM_NUMBER), msg)
	                .create();
	        System.out.println("here is my id:"+message.getSid());// Unique resource ID created to manage this transaction

	    }
}

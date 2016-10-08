package com.park.test;

import com.park.conserver.ConServer;
import com.park.data.Data;
import com.park.dto.Message;

public class Test {
	
	public static void main(String[] args) {
		Message message = new Message();
		message.setMessageType(Data.Login);
		message.setParkId(11);
		message.setPassword("123");
		Boolean boolean1 = new ConServer().conToServer(message);
		System.out.println(boolean1);
	}

}

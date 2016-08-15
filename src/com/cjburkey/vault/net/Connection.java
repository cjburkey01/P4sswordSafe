package com.cjburkey.vault.net;

import java.net.URL;

public class Connection {
	
	private boolean init = false;
	private URL url;
	
	public Connection() {  }
	
	public void initConnection() {
		if(!init) {
			init = true;
			System.out.println("Openning connection to servers");
			
			// Connect to the website
			//   Prepare to send requests
		}
	}
	
	public int loginRequest(String email, String password) {
		if(init) {
			System.out.println("Testing login");
			// Login test
			//   If success, return user's id, else return -1(an invalid id)
		}
		//return -1;		// Not initialized
		
		return 1;
	}
	
	public void closeConnection() {
		init = false;
		// Close connection.
	}
	
}
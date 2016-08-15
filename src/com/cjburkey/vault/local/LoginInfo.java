package com.cjburkey.vault.local;

import java.io.Serializable;

public class LoginInfo implements Serializable {
	
	private static final long serialVersionUID = -6043037437741860236L;
	
	public String username;
	public String password;
	public String siteName;
	public String siteUrl;
	
	public LoginInfo(String n, String ur, String u, String p) {
		this.username = u;
		this.password = p;
		this.siteName = n;
		this.siteUrl = ur;
	}
	
}
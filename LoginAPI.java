package com.alom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginAPI {

	@GetMapping("/security-checking")
	public String login() {
		System.out.println("inside login method....");
		return "login";

	}
	
	@GetMapping("/access-denied")
	public String accDenied() {
		System.out.println("inside access-denied method....");
		return "accessDeniedPage";
		
	}
	@GetMapping("/user-dashboard")
	public String user() {
		System.out.println("inside user method....");
		return "userIndex";
		
	}
	@GetMapping("/admin-dashboard")
	public String admin() {
		System.out.println("inside admin method....");
		return "adminIndex";
		
	}

}

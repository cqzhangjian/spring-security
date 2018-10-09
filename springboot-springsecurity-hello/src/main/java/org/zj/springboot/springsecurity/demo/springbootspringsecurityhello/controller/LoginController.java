package org.zj.springboot.springsecurity.demo.springbootspringsecurityhello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@Autowired
	Environment environment;

	@GetMapping("/")
	public String welCome() {
		return "redirect:/index";
	}
	
	@GetMapping("/index")
	public String index () {
		
		return "index";
	}
	
	@GetMapping("/login")
	public String login() {
		
		return "login";
	}			 
	@GetMapping("/login-error") 
	public String loginError(Model model) {
		System.out.println("======");
		model.addAttribute("loginError",true);
		model.addAttribute("loginErrorMsg", environment.getProperty("login.error.msg"));
		return "login";
	}
	
}

package com.honsoft.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class HomeController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/session")
	public @ResponseBody String session(ServletRequest req) {
		ServletContext context = req.getServletContext();
		
		return "session info";
		
	}

}

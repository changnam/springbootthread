package com.honsoft.controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.honsoft.service.BlogService;

@Controller
@RequestMapping("/")
public class HomeController {
	private static Logger logger = LoggerFactory.getLogger(HomeController.class);
	
    private BlogService blogService;
    
    @Autowired
    private Environment env;
    
    @Autowired
    public HomeController(BlogService blogService) {
        this.blogService = blogService;
    }
    
	@GetMapping("/")
	public String index() {
		logger.info("!!!!!!!!!!!!!!!!!!!!!!!"+env.getProperty("user.dir"));
		return "index";
	}
	
	@GetMapping("/session")
	public @ResponseBody String session(ServletRequest req) {
		ServletContext context = req.getServletContext();
		
		return "session info";
		
	}
	
	@GetMapping("/blog/{id}")
	public String blog(@PathVariable("id") int id, Model m) {
		m.addAttribute("blog", blogService.getBlogById(id));
		
		return "blog";
	}
	
	@GetMapping("/ex")
	public String ex(Model m) {
		m.addAttribute("blog", blogService.getException());
		
		return "blog";
	}
	
	@GetMapping("/my-error-page")
	public String myErrorPage(Model m) {
				
		return "my-error-page";
	}
	

}

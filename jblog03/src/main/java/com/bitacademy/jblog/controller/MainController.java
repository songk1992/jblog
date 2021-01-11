package com.bitacademy.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	
	@RequestMapping("")
	public String index() {
		return "main/index";
	}
	
	@RequestMapping("/search")
	public String index(
			@PathVariable("keyword") String keyword, 
			@PathVariable("which") String which, 
			Model model
			) {
		
		System.out.println(keyword);
		System.out.println(which);
		return "main/index";
	}
	
}
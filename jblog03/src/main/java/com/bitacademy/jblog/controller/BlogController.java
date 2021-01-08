package com.bitacademy.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@ResponseBody
	@RequestMapping({"", "/{category}", "/{category}/{post}" } )
	public String index(
		@PathVariable String id,
		@PathVariable Optional<Long> category,
		@PathVariable Optional<Long> post) {
		System.out.println(id + ":" + category + ":" + post);
		
		
		
		
		
		
		return "main/index";
	}

	
}
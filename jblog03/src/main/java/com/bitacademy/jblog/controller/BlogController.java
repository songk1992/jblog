package com.bitacademy.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@RequestMapping({"", "/{category}", "/{category}/{post}" } )
	public String index(
		@PathVariable String id,
		@PathVariable Optional<Long> category,
		@PathVariable Optional<Long> post) {
		String path_str = id;
		
		// 해당 유저가 존재 하는지 확인
		
		// 존재 하지 않으면 
		
		
		
		if(category.isPresent()) {
			path_str += "/" + category;
			if(post.isPresent()) {
				path_str += "/" + post;
			}
		}else {
			
			
			
			
			return "blog/blog-main";
		}
		
		return "str";
	}
	
	

	
}
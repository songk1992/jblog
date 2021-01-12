package com.bitacademy.jblog.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.jblog.service.MainService;

@Controller
public class MainController {
	
	@Autowired
	private MainService mainService;
	
	@RequestMapping("")
	public String index() {
		return "main/index";
	}
	
	@RequestMapping("/search")
	public String index(
			@RequestParam("keyword") String keyword, 
			@RequestParam("which") String which, 
			Model model
			) {
		Map<String, Object> map = mainService.findWithKeyWord(keyword, which);
		model.addAttribute("map", map);
		return "main/index";
	}
	
}

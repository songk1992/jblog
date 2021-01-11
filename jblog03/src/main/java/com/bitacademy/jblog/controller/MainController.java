package com.bitacademy.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitacademy.jblog.service.MainService;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.UserVo;

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
		
		Boolean str_noting = false;
		// TODO : 제네릭을 써서 바꿔보기 + 중복 되는 코드 줄이기 
		
		if(which.equals("blog-title"))
		{
			List<BlogVo> list = mainService.searchBlog(keyword);
			
			str_noting = true;
			model.addAttribute(list);
		}
		else if(which.equals("tag"))
		{
			List<CategoryVo> list = mainService.searchCategory(keyword);
			model.addAttribute(list);
		}
		else if(which.equals("blog-user"))
		{
			List<UserVo> list = mainService.getCategoryList(keyword);
			model.addAttribute(list);
		}
		else {
			str_noting = true;
		}
				
		if(str_noting) {
			String str_message = "결과를 찾을수 없습니다";
			model.addAttribute(str_message);
			return "main/index";
		}
		return "main/index";
		
	}
	
}

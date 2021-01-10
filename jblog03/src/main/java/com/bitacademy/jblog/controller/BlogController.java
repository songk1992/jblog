package com.bitacademy.jblog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bitacademy.jblog.service.BlogService;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;
import com.bitacademy.security.Auth;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	
	@Autowired
	private BlogService blogService;

	@RequestMapping({ "", "/{category}", "/{category}/{post}" })
	public String index(
			@PathVariable String id, 
			@PathVariable Optional<Long> category,
			@PathVariable Optional<Long> post, 
			Model model) {

		model.addAttribute("id", id);

		if (category.isPresent()) {
			model.addAttribute("category", category);
			if (post.isPresent()) {
				model.addAttribute("post", post);
			}
		}
		
		return "blog/blog-main";
	}
	
	@Auth
	@RequestMapping("/basic")
	public String basic() {
		return "blog/blog-admin-basic";
	}
	
	
	
	/*
	 *  CategoryVo 와 해당 페이지 개수를 가지고 옴
	 */
	@Auth
	@RequestMapping(value="/category", method=RequestMethod.GET)
		public String category(@PathVariable String id, Model model) {
			Map<String, Object> map = blogService.getCategoryListAndCountPages(id);
			model.addAttribute("map", map);
			return "blog/blog-admin-category";
		}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write(@PathVariable String id, Model model) {
		List<CategoryVo> list = blogService.getCategoryList(id);
		model.addAttribute("list", list);
		return "blog/blog-admin-write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(PostVo postVo) {
		blogService.writePost(postVo);
		return "redirect:/{id}";
	}
	
	
	
}

package com.bitacademy.jblog.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.bitacademy.jblog.service.BlogService;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;
import com.bitacademy.security.Auth;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;

	@RequestMapping({ "", "/{category}", "/{category}/{post}" })
	public String index(@PathVariable String id, @PathVariable Optional<Long> category,
			@PathVariable Optional<Long> post, Model model) {

		model.addAttribute(id);

		Map<String, Object> map = blogService.getCategoryListAndGetPostList(id);

		// 아이디가 존재하는지 확인
		int blogExist = blogService.idExist(id);
		if(blogExist==0) {
			return "error/404";
		}
		
		
		if (category.isEmpty()) {
			map.put("category", null);
		} else {
			
			map.put("category", category.get());
			CategoryVo categoryVo = blogService.getCategoryfromCatNo(String.valueOf(category.get()));	
			map.put("categoryVo", categoryVo);
			
			if (post.isEmpty()) {
				map.put("post", null);
				
			} else {
				PostVo postVo = blogService.getPostfromPosNo(String.valueOf(post.get()));	
				map.put("postVo", postVo);
				map.put("post", post.get());
			}

		}
		// 블로그 제목 + 블로그 로고 추가
		BlogVo blogVo = blogService.getLogoPathAndTitle(id);
		map.put("blogVo", blogVo);

		model.addAttribute("map", map);

		return "blog/blog-main";
	}

	@Auth
	@RequestMapping("/basic")
	public String basic(@PathVariable String id, Model model) {
		BlogVo blogVo = blogService.getLogoPathAndTitle(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/blog-admin-basic";
	}

	/*
	 * CategoryVo 와 해당 페이지 개수를 가지고 옴
	 */
	@Auth
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	public String category(@PathVariable String id, Model model) {
		Map<String, Object> map = blogService.getCategoryListAndCountPages(id);
		model.addAttribute("map", map);
		return "blog/blog-admin-category";
	}

	@Auth
	@RequestMapping("/addcat")
	public String add(CategoryVo categoryVo) {
		blogService.writeCategory(categoryVo);
		return "redirect:/{id}/category";
	}
	
	@Auth
	@RequestMapping("/deletecat")
	public String deleteCat(@RequestParam String no) {
		blogService.deleteCategory(no);
		return "redirect:/{id}/category";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(@PathVariable String id, Model model) {
		List<CategoryVo> list = blogService.getCategoryList(id);
		model.addAttribute("list", list);
		return "blog/blog-admin-write";
	}

	@Auth
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String write(PostVo postVo) {
		blogService.writePost(postVo);
		return "redirect:/{id}";
	}

	@Auth
	@RequestMapping(value = "/updateBlogInfo", method = RequestMethod.POST)
	public String updateBlogInfo(@RequestParam(value = "logo-file") MultipartFile multipartFile, BlogVo blogVo) {

		System.out.println(blogVo.getTitle());
		String url = blogService.restore(multipartFile);
		blogVo.setLogo(url);
		blogService.updateBlogInfo(blogVo);
		return "redirect:/{id}";
	}
	
	

}

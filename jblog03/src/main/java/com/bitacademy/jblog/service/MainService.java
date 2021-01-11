package com.bitacademy.jblog.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.BlogRepository;
import com.bitacademy.jblog.repository.PostRepository;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostCategoryVo;
import com.bitacademy.jblog.vo.PostVo;
import com.bitacademy.jblog.vo.UserVo;

@Service
public class MainService {

	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	
	

	public Map<String, Object> findWithKeyWord(String keyword, String which) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		int keyCounter = 0;
		// TODO : 제네릭을 써서 바꿔보기 + 중복 되는 코드 줄이기 

		if(which.equals("blog-title"))
		{
			List<BlogVo> list = blogRepository.searchBlog(keyword);
			map.put("list", list);
			if(!list.isEmpty()) {
				keyCounter = 1;
			}
		}
		else if(which.equals("tag"))
		{
			List<CategoryVo> list = blogRepository.searchCategory(keyword);
			map.put("list", list);
			if(!list.isEmpty()) {
				keyCounter = 3;
			}
		}
		else if(which.equals("blog-user"))
		{
			List<UserVo> list = blogRepository.searchUser(keyword);
			map.put("list", list);
			if(!list.isEmpty()) {
				keyCounter = 5;
			}
		}
		else if(which.equals("blog-post"))
		{
			List<PostCategoryVo> list = postRepository.searchPost(keyword);
			map.put("list", list);
			if(!list.isEmpty()) {
				keyCounter = 7;
			}
		}
		else 
		{
			keyCounter = 0;
		}
		
		map.put("keyCounter", keyCounter);
		
		return map;
	}

}

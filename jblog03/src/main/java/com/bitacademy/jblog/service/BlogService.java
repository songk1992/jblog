package com.bitacademy.jblog.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.BlogRepository;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public int writePost(PostVo postVo) {
		return blogRepository.createNewPost(postVo);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return blogRepository.getCategoryList(id);
	}

	public Map<String, Object> getCategoryListAndCountPages(String id) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<CategoryVo> list = blogRepository.getCategoryList(id);
		map.put("list", list);
		
		List<Long> countPost = new ArrayList<Long>();
		// 추후 마이바티스 for each 문으로 IO 한번에 하여 성능 향상
		for (CategoryVo categoryVo : list) {
			countPost.add(blogRepository.getCountPost(categoryVo));
		}
		
		map.put("countPost", countPost);
		
		
		
		return map;
	}

}

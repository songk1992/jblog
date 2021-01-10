package com.bitacademy.jblog.service;

import java.util.List;

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

}

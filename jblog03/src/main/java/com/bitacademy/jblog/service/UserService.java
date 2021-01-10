package com.bitacademy.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitacademy.jblog.repository.BlogRepository;
import com.bitacademy.jblog.repository.CategoryRepository;
import com.bitacademy.jblog.repository.UserRepository;
import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.UserVo;

@Service
public class UserService {
	
	//private static final Log LOGGER = LogFactory.getLog(UserService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	
	/*
	 *  제목	 : join
	 *  설명	 : 유저 생성시 default 카테고리와 블로그가 같이 생성된다.
	 * 
	 */
	
	// TODO : TRANSACTION 중간에 끊기는 경우 관리 
	
	public boolean join(UserVo userVo) {
		
		boolean ResultChecker;
		CategoryVo categoryVo = new CategoryVo();
		BlogVo blogVo = new BlogVo();
		
		blogVo.setId(userVo.getId());
		blogVo.setTitle(userVo.getName() + "블로그");
		blogVo.setLogo("default-logo.jpg");
		categoryVo.setId(userVo.getId());
		categoryVo.setName("기본설정");
		categoryVo.setDesc("기본설정");
		


		ResultChecker =(userRepository.insertUser(userVo) == 1)
		&&(blogRepository.insertBlog(blogVo) == 1)
		&&(categoryRepository.insertCategory(categoryVo) == 1)
		
		;
		
		return ResultChecker;
	}

	/*
	 *  제목	 : getUser
	 *  설명	 : 유저 로그인시 id와 password 값으로 유저정보를 찾는다.
	 * 
	 */
	public UserVo getUser(UserVo userVo) {
		return userRepository.findByIdAndPassword(userVo);
	}
}

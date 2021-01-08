package com.bitacademy.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.BlogVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertBlog(BlogVo blogVo) {
		return sqlSession.insert("blog.insertBlog", blogVo);
	}

}

package com.bitacademy.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitacademy.jblog.vo.BlogVo;
import com.bitacademy.jblog.vo.CategoryVo;
import com.bitacademy.jblog.vo.PostVo;
import com.bitacademy.jblog.vo.UserVo;

@Repository
public class BlogRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertBlog(BlogVo blogVo) {
		return sqlSession.insert("blog.insertBlog", blogVo);
	}

	public int createNewPost(PostVo postVo) {
		return sqlSession.insert("post.createNewPost", postVo);
	}

	public List<CategoryVo> getCategoryList(String id) {
		return sqlSession.selectList("category.getCategoryList", id);
	}

	public Long getCountPost(CategoryVo categoryVo) {
		return sqlSession.selectOne("post.getCountPost", categoryVo);
	}

	public int writeCategory(CategoryVo categoryVo) {
		return sqlSession.insert("category.insertCategory", categoryVo);
	}

	public List<PostVo> getPostList(String id) {
		return sqlSession.selectList("post.getPostList", id);
	}

	public BlogVo getLogoPathAndTitle(String id) {
		return sqlSession.selectOne("blog.getLogoPathAndTitle", id);
	}

	public int updateBlogInfo(BlogVo blogVo) {
		return sqlSession.update("blog.updateBlogInfo", blogVo);
	}

	public List<BlogVo> searchBlog(String keyword) {
		return sqlSession.selectList("blog.searchBlog", keyword);
	}

	public List<CategoryVo> searchCategory(String keyword) {
		return sqlSession.selectList("category.searchCategory", keyword);
	}

	public List<UserVo> searchUser(String keyword) {
		return sqlSession.selectList("user.searchUser", keyword);
	}


}

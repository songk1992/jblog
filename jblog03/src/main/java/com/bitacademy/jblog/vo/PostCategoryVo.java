package com.bitacademy.jblog.vo;

public class PostCategoryVo {
	private Long no;
	private String title;
	private String contents;
	private String regDate;
	private Long categoryNo;
	private String id;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "PostCategoryVo [no=" + no + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", categoryNo=" + categoryNo + ", id=" + id + "]";
	}
	
	
	
}

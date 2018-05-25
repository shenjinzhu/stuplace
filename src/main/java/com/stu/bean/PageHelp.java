package com.stu.bean;

public class PageHelp {

	private int pageNo;
	private int pageSize = 10;
	private int count;
	private int from;
	private int allPage;
	private String sort;
	private String sortField;

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getAllPage() {
		return allPage;
	}

	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	@Override
	public String toString() {
		return "PageHelp [pageNo=" + pageNo + ", pageSize=" + pageSize + ", count=" + count + ", allPage=" + allPage
				+ ", sort=" + sort + ", sortField=" + sortField + "]";
	}

}

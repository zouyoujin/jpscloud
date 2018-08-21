package com.jpscloud.common.vo;

public class Pagination {

	public Pagination(){}
	
	public Pagination(int current, int pageSize, long total) {
		super();
		this.current = current;
		this.pageSize = pageSize;
		this.total = total;
	}

	private int current;

	private int pageSize;

	private long total;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}

package com.jpscloud.common.vo;

import java.util.List;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 
 * @ClassName: PageList
 * @Description: 前端页面分页数据封装
 * @author: Kitty
 * @date: 2018年8月22日 上午12:30:20
 *
 */
public class PageList<T> {

	public PageList(Page<T> page) {
		this.list = page.getRecords();
		this.pagination = new Pagination(page.getCurrent(), page.getSize(), page.getTotal());
	}

	private List<T> list;

	private Pagination pagination;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

}

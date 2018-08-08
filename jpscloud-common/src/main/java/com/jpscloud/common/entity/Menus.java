package com.jpscloud.common.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @ClassName: Menus
 * @Description: 菜单信息对象
 * @author: Kitty
 * @date: 2018年8月5日 下午11:20:50
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(value=Include.NON_EMPTY, content=Include.NON_NULL)
public class Menus implements Serializable{

	private static final long serialVersionUID = -1474358854655882919L;
	
	private Long id;
	private Long parentId;
	private String name;
	private String icon;
	private String path;
	private Boolean hideInMenu;
	private List<Menus> children;
	private String authority;

	public Menus(){
	}
	
	public Menus(Long id, Long parentId, String name, String icon, String path) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
		this.icon = icon;
		this.path = path;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Boolean getHideInMenu() {
		return hideInMenu;
	}

	public void setHideInMenu(Boolean hideInMenu) {
		this.hideInMenu = hideInMenu;
	}

	public List<Menus> getChildren() {
		return children;
	}

	public void setChildren(List<Menus> children) {
		this.children = children;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}

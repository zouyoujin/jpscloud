package com.jpscloud.common.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: TreeNode   
 * @Description:树形结构数据
 * @author: Kitty
 * @date: 2018年8月5日 下午11:18:47   
 *
 */
public class TreeNode {
	
    private Integer id;
    private Integer parentId;

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    List<TreeNode> children = new ArrayList<TreeNode>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void add(TreeNode node){
        children.add(node);
    }
}

package com.jpscloud.common.dataSource;

/**
 * 
 * @ClassName: DBTypeEnum   
 * @Description: 数据库枚举类型
 * @author: Kitty
 * @date: 2018年8月18日 上午12:37:38   
 *
 */
public enum DBTypeEnum {
	
	//必须配置默认使用数据库,数据库名称自己命名，默认master
	master("master"), save("save");
	private String value;

	DBTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

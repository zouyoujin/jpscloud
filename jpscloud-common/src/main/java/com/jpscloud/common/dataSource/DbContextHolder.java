package com.jpscloud.common.dataSource;

/**
 * 
 * @ClassName: DbContextHolder   
 * @Description: ThreadLocal存储当前线程使用哪个数据库，数据库切换功能
 * @author: Kitty
 * @date: 2018年8月18日 上午12:38:24   
 *
 */
public class DbContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	/**
	 * 设置数据源
	 * 
	 * @param dbTypeEnum
	 */
	public static void setDbType(DBTypeEnum dbTypeEnum) {
		contextHolder.set(dbTypeEnum.getValue());
	}

	/**
	 * 取得当前数据源
	 * 
	 * @return
	 */
	public static String getDbType() {
		return (String) contextHolder.get();
	}

	/**
	 * 清除上下文数据
	 */
	public static void clearDbType() {
		contextHolder.remove();
	}
}

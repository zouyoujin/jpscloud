package com.jpscloud.common.dataSource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(-10) // 保证该AOP在@Transactional之前执行
public class DataSourceSwitchAspect {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@SuppressWarnings("rawtypes")
	@Before("@annotation(DataSourceSwitch)")
	public void beforeSwitchDS(JoinPoint point) {
		// 获得当前访问的class
		Class<?> className = point.getTarget().getClass();
		// 获得访问的方法名
		String methodName = point.getSignature().getName();
		// 得到方法的参数的类型
		Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
		DBTypeEnum dbTypeEnum = DBTypeEnum.master;
		try {
			// 得到访问的方法对象
			Method method = className.getMethod(methodName, argClass);
			// 判断是否存在@DataSourceSwitch注解
			if (method.isAnnotationPresent(DataSourceSwitch.class)) {
				DataSourceSwitch annotation = method.getAnnotation(DataSourceSwitch.class);
				// 取出注解中的数据源名
				dbTypeEnum = annotation.value();
			}
		} catch (Exception e) {
			log.error("get DataSourceSwitch dbTypeEnum fail.", e);
		}
		// 切换数据源
		DbContextHolder.setDbType(dbTypeEnum);
		log.info("Switch DataSource ==> " + "dbType = " + DbContextHolder.getDbType() + " method = "
				+ point.getSignature());
	}

	/**
	 * 方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
	 * 
	 * @param point
	 */
	@After("@annotation(DataSourceSwitch)")
	public void afterSwitchDS(JoinPoint point) {
		DbContextHolder.clearDbType();
		log.info("Revert DataSource ==> " + "dbType = " + DbContextHolder.getDbType() + " method = "
				+ point.getSignature());
	}

}

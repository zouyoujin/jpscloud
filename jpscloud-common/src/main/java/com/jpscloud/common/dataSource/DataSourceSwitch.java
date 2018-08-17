package com.jpscloud.common.dataSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName: DataSourceSwitch   
 * @Description: 用法:直接在service层切换数据源动态切换，默认不配置数据源则为db1
 * @author: Kitty
 * @date: 2018年8月18日 上午12:22:23   
 *
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSourceSwitch {
    DBTypeEnum value() default DBTypeEnum.master;
}

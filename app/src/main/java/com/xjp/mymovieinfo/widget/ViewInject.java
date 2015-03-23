package com.xjp.mymovieinfo.widget;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 * User: xjp
 * Date: 2015/3/23
 * Time: 19:15
 */

@Target(ElementType.FIELD) //作用域 用于描述域
@Retention(RetentionPolicy.RUNTIME) //有效时段 运行时有效
public @interface ViewInject {
    public int id();
}

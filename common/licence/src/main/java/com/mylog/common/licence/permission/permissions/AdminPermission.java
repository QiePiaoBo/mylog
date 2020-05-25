package com.mylog.common.licence.permission.permissions;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Target;

@Documented
@Inherited
@Target(ElementType.METHOD)
public @interface AdminPermission {
    String userName() default "user";
    boolean required() default true;
}

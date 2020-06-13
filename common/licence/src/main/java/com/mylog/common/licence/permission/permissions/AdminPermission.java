package com.mylog.common.licence.permission.permissions;

import java.lang.annotation.*;

/**
 * @author Dylan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface AdminPermission {
    int userType() default 2;
}

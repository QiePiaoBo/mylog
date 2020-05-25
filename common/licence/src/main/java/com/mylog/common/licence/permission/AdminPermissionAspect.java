package com.mylog.common.licence.permission;

import com.alibaba.fastjson.JSONObject;
import com.mylog.common.licence.EnumCenter.AdminEnum;
import com.mylog.common.licence.EnumCenter.PermissionEnum;
import com.mylog.common.licence.permission.permissions.AdminPermission;
import javafx.beans.property.ReadOnlyBooleanProperty;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@Aspect
public class AdminPermissionAspect implements Ordered {

    @Pointcut("execution(* com.*.controller..*(..))")
    public void permissionTest(){

    }
    @Around("permissionTest()")
    public Object doPermission(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
        if (adminPermission == null){
            return joinPoint.proceed();
        }

        // 判断是否需要数据权限
        boolean required = adminPermission.required();
        if (!required){
            return joinPoint.proceed();
        }

        Object[] args = joinPoint.getArgs();
        if (null == args || args.length == 0){
            return "参数为空";
        }
        JSONObject json = JSONObject.parseObject(String.valueOf(args[0]));
        Integer userType = json.getInteger(adminPermission.userName());
        if (!AdminEnum.ADMIN.getUserName().equals("admin")){
            return "权限不匹配";
        }
        return joinPoint.proceed();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

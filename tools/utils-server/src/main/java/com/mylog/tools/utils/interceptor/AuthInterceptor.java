package com.mylog.tools.utils.interceptor;

import com.mylog.tools.model.annos.AdminPermission;
import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.result.DataResult;
import com.mylog.tools.utils.session.UserContext;
import com.mylog.tools.utils.utils.PermissionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 创建拦截器，拦截所有请求
 *
 * @author Dylan
 */
@Component
public class AuthInterceptor implements HandlerInterceptor{

    @Autowired
    private PermissionChecker permissionChecker;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        if (!(handler instanceof HandlerMethod)) {
            return true;
        } else {
            // 获取方法中的注解
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            AdminPermission authToken = method.getAnnotation(AdminPermission.class);

            if (authToken == null) {
                return true;
            }
            // 需要验证的Method
            if (null == UserContext.getCurrentUser()) {
                response.setContentType("application/json; charset=UTF-8");
                response.getWriter().write(DataResult
                        .fail(Status.NOT_LOGIN.getStatus(), Message.NOT_LOGIN.getMsg())
                        .build().toString());
                return false;
            }
            // todo 修改权限校验方式
            int userType = authToken.userType();
            // 通过
            if (permissionChecker.hasPermission(userType, request.getRequestURI())){
                return true;
            }else {
                return false;
            }
            // 数字越小权限越大
            //            result = userType >= UserContext.getCurrentUser().getUserGroup();
            //            if (!result) {
            //                response.setContentType("application/json; charset=UTF-8");
            //                if (UserContext.getCurrentUser() == null) {
            //                    response.getWriter().write(DataResult
            //                            .fail(Status.NOT_LOGIN.getStatus(), Message.NOT_LOGIN.getMsg())
            //                            .build().toString());
            //                } else {
            //                    response.getWriter().write(DataResult
            //                            .fail(Status.PERMISSION_ERROR.getStatus(), Message.PERMISSION_ERROR.getMsg())
            //                            .build().toString());
            //                }
            //            }
            //            return result;
        }
    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}

package com.mylog.tools.utils.exceptionhandler;

import com.mylog.entitys.entitys.result.HttpResult;
import com.mylog.entitys.entitys.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Dylan
 * @Date : Created in 10:04 2021/3/12
 * @Description : 异常处理类
 * @Function :
 */
@RestControllerAdvice
@ResponseBody
public class MyExceptionHandler {

    /**
     * 统一异常处理方法
     * @param exception
     * @param request
     * @param response
     * @return
     */
    @ExceptionHandler({MyException.class})
    public HttpResult commonExceptionHandler(Exception exception, HttpServletRequest request,
                                             HttpServletResponse response){
        HttpResult dataResult = confirmAccurateResult(exception, request.getRequestURI());
        if(null == dataResult){
            return HttpResult.failed();
        }
        response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        return dataResult;
    }

    /**
     * 对不同的自定义异常进行处理
     * @param e
     * @param uri
     * @return
     */
    private HttpResult confirmAccurateResult(Exception e, String uri){
        HttpResult dataResult = null;
        if (e instanceof MyException){
            dataResult = new HttpResult(((MyException) e).getErrorCode(), ((MyException) e).getErrorMsg(), uri);
        }
        return dataResult;
    }

}

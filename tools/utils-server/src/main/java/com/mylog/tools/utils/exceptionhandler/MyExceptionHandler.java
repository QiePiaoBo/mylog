package com.mylog.tools.utils.exceptionhandler;

import com.mylog.tools.model.model.info.Message;
import com.mylog.tools.model.model.info.Status;
import com.mylog.tools.model.model.result.HttpResult;
import com.mylog.tools.model.model.exception.MyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

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
    @ExceptionHandler({Exception.class})
    public HttpResult commonExceptionHandler(Exception exception, HttpServletRequest request,
                                             HttpServletResponse response){
        HttpResult dataResult = confirmAccurateResult(exception, request.getRequestURI());
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return dataResult;
    }

    /**
     * 对不同的自定义异常进行处理
     * @param e
     * @param uri
     * @return
     */
    private HttpResult confirmAccurateResult(Exception e, String uri){
        if (Objects.nonNull(e) && e instanceof MyException){
            return new HttpResult(((MyException) e).getErrorCode(), ((MyException) e).getErrorMsg(), uri);
        }
        if (e != null){
            e.printStackTrace();
            return new HttpResult(Status.ERROR_BASE.getStatus(), e.getMessage(), uri);
        }
        return new HttpResult(Status.ERROR_BASE.getStatus(), Message.ERROR.getMsg(), uri);
    }

}

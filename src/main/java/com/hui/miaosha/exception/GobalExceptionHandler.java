package com.hui.miaosha.exception;

import com.hui.miaosha.result.CodeMsg;
import com.hui.miaosha.result.ResultMsg;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author: CarlChen
 * @Despriction: 全局异常处理器
 * @Date: Create in 22:42 2019\4\13 0013
 */
@ControllerAdvice
@ResponseBody
public class GobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResultMsg<String> exceptionHandler(HttpServletResponse response, Exception e){
        e.printStackTrace();
        if (e instanceof GobalException){
            GobalException gobalException = (GobalException) e;
            CodeMsg codeMsg = gobalException.getCodeMsg();
            return ResultMsg.ERROR(codeMsg);
        } else if (e instanceof BindException){
            BindException exception = (BindException) e;
            List<ObjectError> allErrors = exception.getAllErrors();
            ObjectError objectError = allErrors.get(0);
            String defaultMessage = objectError.getDefaultMessage();
            return ResultMsg.ERROR(CodeMsg.BIND_ERROR.fillArgs(defaultMessage));
        } else {
            return ResultMsg.ERROR(CodeMsg.SERVER_ERROR);
        }
    }



}

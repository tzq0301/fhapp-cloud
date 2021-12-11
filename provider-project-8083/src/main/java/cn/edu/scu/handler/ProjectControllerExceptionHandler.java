package cn.edu.scu.handler;

import cn.edu.scu.exception.ProjectNotFoundException;
import cn.tzq0301.result.CommonResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cn.tzq0301.result.ResponseCode.ERROR;

/**
 * @author TZQ
 * @Description TODO
 */
@RestControllerAdvice
public class ProjectControllerExceptionHandler {
    @ExceptionHandler({ProjectNotFoundException.class})
    public CommonResult<?> projectNotFountExceptionHandle(Exception ex) {
        return CommonResult.error(0, ERROR.getCode(), ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public CommonResult<?> methodArgumentNotValidExceptionHandle(
            MethodArgumentNotValidException ex) {
        return CommonResult.error(0, ex.getMessage());
    }
}

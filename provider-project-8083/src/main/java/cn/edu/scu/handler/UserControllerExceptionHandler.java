package cn.edu.scu.handler;

import cn.edu.scu.controller.UserController;
import cn.tzq0301.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cn.tzq0301.result.ResponseCode.ERROR;

/**
 * @author TZQ
 * @Description UserController的异常处理器
 */
@RestControllerAdvice(assignableTypes = {UserController.class})
@Slf4j
public class UserControllerExceptionHandler {
    @ExceptionHandler({
            UsernameNotFoundException.class,
            DuplicateKeyException.class
    })
    public CommonResult<?> usernameNotFountExceptionHandle(Exception ex) {
        log.info(ex.getMessage());
        return CommonResult.error(0, ERROR.getCode(), ex.getMessage());
    }
}

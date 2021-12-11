package cn.edu.scu.aop;

import cn.tzq0301.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * @author TZQ
 * @Description TODO
 */
@Aspect
@Component
@Slf4j
public class UserControllerAop {
    @Pointcut("execution(public * cn.edu.scu.controller.UserController.login(..))")
    public void loginMethod() {};

    @AfterReturning(pointcut = "loginMethod()", returning = "retVal")
    public void afterLoginReturning(Object retVal) {
        CommonResult<?> result = null;
        if (retVal instanceof CommonResult) {
            result = (CommonResult<?>) retVal;
        }
        Assert.notNull(result, "result is not null");
        if (result.getCode() != 1) {
            log.error("Login failed!");
        }
    }
}

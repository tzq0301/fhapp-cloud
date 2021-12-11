package cn.edu.scu.controller;

import cn.edu.scu.entity.LoginFormVO;
import cn.edu.scu.entity.RegisterFormVO;
import cn.edu.scu.entity.UserBasicInfoDTO;
import cn.edu.scu.service.UserService;
import cn.tzq0301.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author TZQ
 * @Description UserController
 */
@RestController
@RequestMapping("/user")
@CrossOrigin({"http://localhost:3000", "http://localhost:3000"})
@Slf4j
@RefreshScope
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody RegisterFormVO form) {
        final UserBasicInfoDTO userBasicInfo = new UserBasicInfoDTO(
                form.getUsername(),
                form.getPassword(),
                form.getSecretSecurityQuestion(),
                form.getSecretSecurityAnswer());
        final int saveUserResult = userService.register(userBasicInfo);
        if (saveUserResult == 0) {
            return CommonResult.error(saveUserResult);
        }
        return CommonResult.success(saveUserResult);
    }

    @PostMapping("/login")
    public CommonResult<?> login(@RequestBody LoginFormVO form, Model model) {
        final UserBasicInfoDTO userBasicInfo = new UserBasicInfoDTO(form.getUsername(), form.getPassword());
        final int loginResult = userService.login(userBasicInfo);
        if (loginResult == 0) {
            return CommonResult.error(0);
        }
        model.addAttribute("username", form.getUsername());
        model.addAttribute("password", form.getPassword());
        return CommonResult.success(1);
    }

    @GetMapping("/secretSecurity/username/{username}")
    public CommonResult<String> getSecretSecurityQuestion(@PathVariable("username") String username) {
        return CommonResult.success(userService.getSecretSecurityQuestion(username));
    }

    @PostMapping("/secretSecurity/username/{username}")
    public CommonResult<?> getSecretSecurityAnswerAndVerify(
            @PathVariable("username") String username,
            @RequestBody Map<String, String> requestBody) {
        final int result = userService.getSecretSecurityAnswerAndVerify(username,
                requestBody.get("secretSecurityAnswer"), requestBody.get("newPassword"));
        if (result == 0) {
            return CommonResult.error(result);
        }
        return CommonResult.success(result);
    }
}

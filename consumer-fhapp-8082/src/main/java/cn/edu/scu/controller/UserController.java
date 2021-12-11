package cn.edu.scu.controller;

import cn.edu.scu.entity.LoginFormVO;
import cn.edu.scu.entity.RegisterFormVO;
import cn.edu.scu.result.CommonResult;
import cn.edu.scu.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author TZQ
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final ProjectService projectService;

    @Autowired
    public UserController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/register")
    public CommonResult<?> register(@RequestBody RegisterFormVO form) {
        return projectService.register(form);
    }

    @GetMapping("/secretSecurity/username/{username}")
    public CommonResult<String> getSecretSecurityQuestion(@PathVariable("username") String username) {
        return projectService.getSecretSecurityQuestion(username);
    }

    @PostMapping("/secretSecurity/username/{username}")
    public CommonResult<?> getSecretSecurityAnswerAndVerify(
            @PathVariable("username") String username,
            @RequestBody Map<String, String> requestBody) {
        return projectService.getSecretSecurityAnswerAndVerify(username, requestBody);
    }
}

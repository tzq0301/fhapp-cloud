package cn.edu.scu.service;

import cn.edu.scu.entity.*;
import cn.edu.scu.result.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@FeignClient(name = "provider-project-8083")
public interface ProjectService {
    @PostMapping("/project/personal/username/{username}")
    CommonResult<?> savePersonalProject(
            @PathVariable("username") String username,
            @Valid @RequestBody PersonalProjectDetail personalProjectDetail);

    @PutMapping("/project/personal/projectId/{projectId}")
    CommonResult<?> updatePersonalProject(
            @PathVariable("projectId") String projectId,
            @RequestBody PersonalProjectDetail personalProjectDetail);

    @PostMapping("/project/team/username/{username}")
    CommonResult<?> saveTeamProject(
            @PathVariable("username") String username,
            @Valid @RequestBody TeamProjectDetail teamProjectDetail);

    @PutMapping("/project/team/projectId/{projectId}")
    CommonResult<?> updateTeamProject(
            @PathVariable("projectId") String projectId,
            @RequestBody TeamProjectDetail teamProjectDetail);

    @GetMapping("/project/projects/{username}")
    CommonResult<List<ProjectBriefInfo>> getAllProjectsByUsername(
            @PathVariable("username") String username);

    @GetMapping("/project/{isPersonal}/projectId/{projectId}")
    CommonResult<? extends ProjectDetail> getProjectDetail(
            @PathVariable("isPersonal") Integer isPersonal,
            @PathVariable("projectId") String projectId);

    @DeleteMapping("/project/projectId/{projectId}")
    CommonResult<?> deleteApply(
            @PathVariable("projectId") String projectId);

    @PutMapping("/project/projectId/{projectId}")
    CommonResult<?> changeProjectSubmittedState(
            @PathVariable("projectId") String projectId);

    @PostMapping("/user/register")
    CommonResult<?> register(@RequestBody RegisterFormVO form);

    @GetMapping("/user/secretSecurity/username/{username}")
    CommonResult<String> getSecretSecurityQuestion(@PathVariable("username") String username);

    @PostMapping("/user/secretSecurity/username/{username}")
    CommonResult<?> getSecretSecurityAnswerAndVerify(
            @PathVariable("username") String username,
            @RequestBody Map<String, String> requestBody);
}

package cn.edu.scu.controller;

import cn.edu.scu.entity.PersonalProjectDetail;
import cn.edu.scu.entity.ProjectBriefInfo;
import cn.edu.scu.entity.ProjectDetail;
import cn.edu.scu.entity.TeamProjectDetail;
import cn.edu.scu.service.ProjectService;
import cn.tzq0301.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * @author TZQ
 * @Description ProjectController
 */
@RestController
@RequestMapping("/project")
@CrossOrigin({"http://localhost:3000", "http://localhost:3000"})
@Slf4j
@RefreshScope
// @PreAuthorize("hasRole('ADMIN')")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/personal/username/{username}")
    public CommonResult<?> savePersonalProject(
            @PathVariable("username") String username,
            @Valid @RequestBody PersonalProjectDetail personalProjectDetail) {
        personalProjectDetail.setApplyId(UUID.randomUUID().toString());
        final int result = projectService.savePersonalProjectDetail(username, personalProjectDetail);
        if (result == 0) {
            return CommonResult.error(result);
        }
        return CommonResult.success(result);
    }

    @PutMapping("/personal/projectId/{projectId}")
    public CommonResult<?> updatePersonalProject(
            @PathVariable("projectId") String projectId,
            @RequestBody PersonalProjectDetail personalProjectDetail) {
        final int result = projectService.updatePersonalProjectDetail(projectId, personalProjectDetail);
        if (result == 0) {
            return CommonResult.error(result);
        }
        return CommonResult.success(result);
    }

    @PostMapping("/team/username/{username}")
    public CommonResult<?> saveTeamProject(
            @PathVariable("username") String username,
            @Valid @RequestBody TeamProjectDetail teamProjectDetail) {
        teamProjectDetail.setApplyId(UUID.randomUUID().toString());
        final int result = projectService.saveTeamProjectDetail(username, teamProjectDetail);
        if (result == 0) {
            return CommonResult.error(result);
        }
        return CommonResult.success(result);
    }

    @PutMapping("/team/projectId/{projectId}")
    public CommonResult<?> updateTeamProject(
            @PathVariable("projectId") String projectId,
            @RequestBody TeamProjectDetail teamProjectDetail) {
        final int result = projectService.updateTeamProjectDetail(projectId, teamProjectDetail);
        if (result == 0) {
            return CommonResult.error(result);
        }
        return CommonResult.success(result);
    }

    @GetMapping("/projects/{username}")
    public CommonResult<List<ProjectBriefInfo>> getAllProjectsByUsername(
            @PathVariable("username") String username) {
        return CommonResult.success(projectService.getAllProjectsByUsername(username));
    }

    @GetMapping("/{isPersonal}/projectId/{projectId}")
    public CommonResult<? extends ProjectDetail> getProjectDetail(
            @PathVariable("isPersonal") Integer isPersonal,
            @PathVariable("projectId") String projectId) {
        ProjectDetail projectDetail = isPersonal == 1
                ? projectService.getPersonalProjectDetail(projectId)
                : projectService.getTeamProjectDetail(projectId);
        return CommonResult.success(projectDetail);
    }

    @DeleteMapping("/projectId/{projectId}")
    public CommonResult<?> deleteApply(
            @PathVariable("projectId") String projectId) {
        final int result = projectService.deleteApply(projectId);
        if (result == 0) {
            return CommonResult.error(result);
        }
        return CommonResult.success(result);
    }

    @PutMapping("/projectId/{projectId}")
    public CommonResult<?> changeProjectSubmittedState(
            @PathVariable("projectId") String projectId) {
        final int result = projectService.changeProjectSubmittedState(projectId);
        if (result == 0) {
            return CommonResult.error(result);
        }
        return CommonResult.success(result);
    }
}

package cn.edu.scu.controller;

import cn.edu.scu.entity.PersonalProjectDetail;
import cn.edu.scu.entity.ProjectBriefInfo;
import cn.edu.scu.entity.ProjectDetail;
import cn.edu.scu.entity.TeamProjectDetail;
import cn.edu.scu.result.CommonResult;
import cn.edu.scu.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author TZQ
 */
@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/personal/username/{username}")
    public CommonResult<?> savePersonalProject(
            @PathVariable("username") String username,
            @Valid @RequestBody PersonalProjectDetail personalProjectDetail) {
        return projectService.savePersonalProject(username, personalProjectDetail);
    }

    @PutMapping("/personal/projectId/{projectId}")
    public CommonResult<?> updatePersonalProject(
            @PathVariable("projectId") String projectId,
            @RequestBody PersonalProjectDetail personalProjectDetail) {
        return projectService.updatePersonalProject(projectId, personalProjectDetail);
    }

    @PostMapping("/team/username/{username}")
    public CommonResult<?> saveTeamProject(
            @PathVariable("username") String username,
            @Valid @RequestBody TeamProjectDetail teamProjectDetail) {
        return projectService.saveTeamProject(username, teamProjectDetail);
    }

    @PutMapping("/team/projectId/{projectId}")
    public CommonResult<?> updateTeamProject(
            @PathVariable("projectId") String projectId,
            @RequestBody TeamProjectDetail teamProjectDetail) {
        return projectService.updateTeamProject(projectId, teamProjectDetail);
    }

    @GetMapping("/projects/{username}")
    public CommonResult<List<ProjectBriefInfo>> getAllProjectsByUsername(
            @PathVariable("username") String username) {
        return projectService.getAllProjectsByUsername(username);
    }

    @GetMapping("/{isPersonal}/projectId/{projectId}")
    public CommonResult<? extends ProjectDetail> getProjectDetail(
            @PathVariable("isPersonal") Integer isPersonal,
            @PathVariable("projectId") String projectId) {
        return projectService.getProjectDetail(isPersonal, projectId);
    }

    @DeleteMapping("/projectId/{projectId}")
    public CommonResult<?> deleteApply(
            @PathVariable("projectId") String projectId) {
        return projectService.deleteApply(projectId);
    }

    @PutMapping("/projectId/{projectId}")
    public CommonResult<?> changeProjectSubmittedState(
            @PathVariable("projectId") String projectId) {
        return projectService.changeProjectSubmittedState(projectId);
    }
}

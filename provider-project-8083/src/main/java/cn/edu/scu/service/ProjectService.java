package cn.edu.scu.service;

import cn.edu.scu.entity.PersonalProjectDetail;
import cn.edu.scu.entity.ProjectBriefInfo;
import cn.edu.scu.entity.ProjectDetail;
import cn.edu.scu.entity.TeamProjectDetail;

import java.util.List;

public interface ProjectService {
    List<ProjectBriefInfo> getAllProjectsByUsername(String username);

    int deleteApply(String projectId);

    int changeProjectSubmittedState(String projectId);

    ProjectDetail getPersonalProjectDetail(String projectId);

    ProjectDetail getTeamProjectDetail(String projectId);

    int savePersonalProjectDetail(String username, PersonalProjectDetail personalProjectDetail);

    int saveTeamProjectDetail(String username, TeamProjectDetail teamProjectDetail);

    int updatePersonalProjectDetail(String projectId, PersonalProjectDetail personalProjectDetail);

    int updateTeamProjectDetail(String projectId, TeamProjectDetail teamProjectDetail);
}

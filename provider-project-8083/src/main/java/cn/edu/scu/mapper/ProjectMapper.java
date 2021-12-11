package cn.edu.scu.mapper;

import cn.edu.scu.entity.PersonalProjectDetail;
import cn.edu.scu.entity.ProjectBriefInfo;
import cn.edu.scu.entity.ProjectDetail;
import cn.edu.scu.entity.TeamProjectDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProjectMapper {
    List<ProjectBriefInfo> getAllProjectsByUsername(String username);

    int deleteApply(String projectId);

    int changeProjectSubmittedState(String projectId);

    ProjectDetail getPersonalProjectDetail(String projectId);

    ProjectDetail getTeamProjectDetail(String projectId);

    int savePersonalApply(@Param("detail") PersonalProjectDetail detail);

    int saveApplyIdTbl(@Param("applyId") String applyId);

    int saveUsersApply(@Param("username") String username, @Param("applyId") String applyId);

    int saveTeamApply(@Param("detail") TeamProjectDetail detail);

    int saveApplyIdTblTeam(@Param("applyId") String applyId);

    int updatePersonalProjectDetail(
            @Param("applyId") String projectId, @Param("detail") PersonalProjectDetail personalProjectDetail);

    int updateTeamProjectDetail(
            @Param("applyId") String projectId, @Param("detail") TeamProjectDetail teamProjectDetail);
}

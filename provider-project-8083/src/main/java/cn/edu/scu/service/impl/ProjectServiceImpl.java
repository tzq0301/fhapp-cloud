package cn.edu.scu.service.impl;

import cn.edu.scu.entity.PersonalProjectDetail;
import cn.edu.scu.entity.ProjectBriefInfo;
import cn.edu.scu.entity.ProjectDetail;
import cn.edu.scu.entity.TeamProjectDetail;
import cn.edu.scu.exception.ProjectNotFoundException;
import cn.edu.scu.mapper.ProjectMapper;
import cn.edu.scu.service.ProjectService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;

/**
 * @author TZQ
 * @Description ProjectServiceImpl
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper, RedisTemplate<String, String> redisTemplate) {
        this.projectMapper = projectMapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    @Cacheable(value = "projects")
    public List<ProjectBriefInfo> getAllProjectsByUsername(String username) {
        final List<ProjectBriefInfo> allProjectsByUsername = projectMapper.getAllProjectsByUsername(username);
        if (allProjectsByUsername == null) {
            return Lists.newArrayList();
        }
        return allProjectsByUsername;
    }

    @Override
    @CacheEvict(value = "projects", allEntries = true)
    public int deleteApply(String projectId) {
        return projectMapper.deleteApply(projectId);
    }

    @Override
    @CacheEvict(value = "projects", allEntries = true)
    public int changeProjectSubmittedState(String projectId) {
        return projectMapper.changeProjectSubmittedState(projectId);
    }

    @Override
    public ProjectDetail getPersonalProjectDetail(String projectId) {
        final ProjectDetail personalProjectDetail = projectMapper.getPersonalProjectDetail(projectId);
        if (personalProjectDetail == null) {
            throw new ProjectNotFoundException();
        }
        return personalProjectDetail;
    }

    @Override
    public ProjectDetail getTeamProjectDetail(String projectId) {
        final ProjectDetail teamProjectDetail = projectMapper.getTeamProjectDetail(projectId);
        if (teamProjectDetail == null) {
            throw new ProjectNotFoundException();
        }
        return teamProjectDetail;
    }

    @Override
    @CacheEvict(value = "projects", allEntries = true)
    public int savePersonalProjectDetail(final String username, final PersonalProjectDetail personalProjectDetail) {
        final int hashCode = personalProjectDetail.hashCode(false);
        if (redisTemplate.opsForValue().get(hashCode) != null) {
            return 0;
        }
        redisTemplate.opsForValue().set(String.valueOf(hashCode), "1");
        redisTemplate.expire(String.valueOf(hashCode), Duration.ofSeconds(3));
        final int savePersonalApplyResult = projectMapper.savePersonalApply(personalProjectDetail);
        final int saveApplyIdTblResult = projectMapper.saveApplyIdTbl(personalProjectDetail.getApplyId());
        final int saveUsersApplyResult = projectMapper.saveUsersApply(username, personalProjectDetail.getApplyId());
        return savePersonalApplyResult | saveApplyIdTblResult | saveUsersApplyResult;
    }

    @Override
    @CacheEvict(value = "projects", allEntries = true)
    public int saveTeamProjectDetail(String username, TeamProjectDetail teamProjectDetail) {
        final int hashCode = teamProjectDetail.hashCode(false);
        if (redisTemplate.opsForValue().get(hashCode) != null) {
            return 0;
        }
        redisTemplate.opsForValue().set(String.valueOf(hashCode), "1");
        redisTemplate.expire(String.valueOf(hashCode), Duration.ofSeconds(3));
        final int saveTeamApplyResult = projectMapper.saveTeamApply(teamProjectDetail);
        final int saveApplyIdTblResult = projectMapper.saveApplyIdTblTeam(teamProjectDetail.getApplyId());
        final int saveUsersApplyResult = projectMapper.saveUsersApply(username, teamProjectDetail.getApplyId());
        return saveTeamApplyResult | saveApplyIdTblResult | saveUsersApplyResult;
    }

    @Override
    @CacheEvict(value = "projects", allEntries = true)
    public int updatePersonalProjectDetail(String projectId, PersonalProjectDetail personalProjectDetail) {
        return projectMapper.updatePersonalProjectDetail(projectId, personalProjectDetail);
    }

    @Override
    @CacheEvict(value = "projects", allEntries = true)
    public int updateTeamProjectDetail(String projectId, TeamProjectDetail teamProjectDetail) {
        return projectMapper.updateTeamProjectDetail(projectId, teamProjectDetail);
    }
}

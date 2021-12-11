package cn.edu.scu.service.impl;

import cn.edu.scu.entity.AuthoritiesDO;
import cn.edu.scu.entity.UserBasicInfoDTO;
import cn.edu.scu.entity.UsersDO;
import cn.edu.scu.mapper.UserMapper;
import cn.edu.scu.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * @author TZQ
 * @Description UserServiceImpl
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * 保存用户信息（注册）
     *
     * @param userBasicInfo 用户基本信息
     * @return 是否成功
     */
    @Override
    public int register(UserBasicInfoDTO userBasicInfo) {
        final UsersDO usersDO = new UsersDO(
                userBasicInfo.getUsername(),
                passwordEncoder.encode(userBasicInfo.getPassword()),
                userBasicInfo.getSecretSecurityQuestion(),
                userBasicInfo.getSecretSecurityAnswer());
        final AuthoritiesDO authoritiesDO = new AuthoritiesDO(
                userBasicInfo.getUsername(),
                userBasicInfo.getAuths());
        final int saveUserResult = userMapper.saveUser(usersDO);
        final int saveAuthoritiesResult = userMapper.saveUserAuthorities(authoritiesDO);
        return saveUserResult | saveAuthoritiesResult;
    }

    @Override
    @Cacheable(value = "username")
    public int login(UserBasicInfoDTO userBasicInfo) {
        final String passwordFromDatabase = getPassword(userBasicInfo.getUsername());
        Assert.hasText(passwordFromDatabase, "Password is not null or \"\".");
        if (!verify(userBasicInfo.getPassword(), passwordFromDatabase)) {
            return 0;
        }
        return 1;
    }

    private String getPassword(String username) {
        final String password = userMapper.getPassword(username);
        if (!StringUtils.hasText(password)) {
            throw new UsernameNotFoundException(
                    String.format("Username of %s not fount\n", username));
        }
        return password;
    }

    private boolean verify(String passwordFromInput, String passwordFromDatabase) {
        return passwordEncoder.matches(passwordFromInput, passwordFromDatabase);
    }

    @Override
    public String getSecretSecurityQuestion(String username) {
        final String secretSecurityQuestion = userMapper.getSecretSecurityQuestion(username);
        if (!StringUtils.hasText(secretSecurityQuestion)) {
            throw new UsernameNotFoundException(
                    String.format("Username of %s not fount\n", username));
        }
        return secretSecurityQuestion;
    }

    @Override
    @CacheEvict(value = "username", allEntries = true)
    public int getSecretSecurityAnswerAndVerify(String username, String secretSecurityAnswer, String newPassword) {
        if (!getSecretSecurityAnswer(username).equals(secretSecurityAnswer)) {
            return 0;
        }
        return userMapper.updatePassword(username, passwordEncoder.encode(newPassword));
    }

    private String getSecretSecurityAnswer(String username) {
        final String secretSecurityAnswer = userMapper.getSecretSecurityAnswer(username);
        if (!StringUtils.hasText(secretSecurityAnswer)) {
            throw new UsernameNotFoundException(
                    String.format("Username of %s not fount\n", username));
        }
        return secretSecurityAnswer;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

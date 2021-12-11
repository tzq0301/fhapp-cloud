package cn.edu.scu.service;

import cn.edu.scu.entity.UserBasicInfoDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    /**
     * 保存用户信息（注册）
     * @param userBasicInfo 用户基本信息
     * @return 是否成功
     */
    int register(UserBasicInfoDTO userBasicInfo);

    /**
     * 登录
     * @param userBasicInfo 用户基本信息
     * @return 是否成功
     */
    int login(UserBasicInfoDTO userBasicInfo);

    /**
     * 获得密保问题
     * @param username 用户名
     * @return 密保问题
     */
    String getSecretSecurityQuestion(String username);

    /**
     * 获得密保答案
     * @param username 用户名
     * @return 是否成功
     */
    int getSecretSecurityAnswerAndVerify(String username, String secretSecurityAnswer, String newPassword);


}

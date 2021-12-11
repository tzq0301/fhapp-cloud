package cn.edu.scu.mapper;

import cn.edu.scu.entity.AuthoritiesDO;
import cn.edu.scu.entity.UsersDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper
public interface UserMapper {
    /**
     * 保存信息到users表
     * @param usersDO users表对象
     * @return 是否保存成功
     */
    int saveUser(UsersDO usersDO);

    /**
     * 保存信息到authorities表
     * @param authoritiesDO authorities表对象
     * @return 是否保存成功
     */
    int saveUserAuthorities(AuthoritiesDO authoritiesDO);

    /**
     * 根据用户名获取密码
     * @param username 用户名
     * @return 密码
     */
    String getPassword(String username);

    /**
     * 根据用户名获得密保问题
     * @param username 用户名
     * @return 密保问题
     */
    String getSecretSecurityQuestion(String username);

    /**
     * 根据用户名获得密保答案
     * @param username 用户名
     * @return 密保答案
     */
    String getSecretSecurityAnswer(String username);

    /**
     * 根据用户名更新密码
     * @param username 用户名
     * @param newPassword 新密码
     * @return 是否成功
     */
    int updatePassword(String username, String newPassword);

    UserDetails getUserByUsername(String username);
}

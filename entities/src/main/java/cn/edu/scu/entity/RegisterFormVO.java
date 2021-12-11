package cn.edu.scu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TZQ
 * @Description RegisterFormVO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterFormVO {
    private String username;
    private String password;
    private String secretSecurityQuestion;
    private String secretSecurityAnswer;
}

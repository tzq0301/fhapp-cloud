package cn.edu.scu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author TZQ
 * @Description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDO {
    private String username;
    private String password;
    private String secretSecurityQuestion;
    private String secretSecurityAnswer;
    private boolean enabled;

    public UsersDO(String username, String password, String secretSecurityQuestion, String secretSecurityAnswer) {
        this.username = username;
        this.password = password;
        this.secretSecurityQuestion = secretSecurityQuestion;
        this.secretSecurityAnswer = secretSecurityAnswer;
        this.enabled = true;
    }
}

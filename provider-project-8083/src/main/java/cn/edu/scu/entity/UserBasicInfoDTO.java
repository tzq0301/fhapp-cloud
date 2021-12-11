package cn.edu.scu.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author TZQ
 * @Description UserBasicInfoDTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicInfoDTO implements UserDetails {
    private static final long serialVersionUID = -8973918843474816153L;

    private String username;
    private String password;
    private String secretSecurityQuestion;
    private String secretSecurityAnswer;
    private boolean enabled;
    private List<String> auths;

    public UserBasicInfoDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserBasicInfoDTO(String username, String password, String secretSecurityQuestion, String secretSecurityAnswer) {
        this.username = username;
        this.password = password;
        this.secretSecurityQuestion = secretSecurityQuestion;
        this.secretSecurityAnswer = secretSecurityAnswer;
        this.enabled = true;
        this.auths = Lists.newArrayList("ROLE_ADMIN");
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Sets.newHashSet(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}

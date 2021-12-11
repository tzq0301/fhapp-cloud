package cn.edu.scu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * @author TZQ
 * @Description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetail {
    private String applyId;
    @NotBlank
    private String type;
    @NotBlank
    private String personName;
    @NotBlank
    private String licenseNum;
    @NotBlank
    private String phoneNum;
    @Email(message = "Email format Error!")
    @NotBlank
    private String email;
    @NotBlank
    private String address;

    @NotBlank
    private String workTitle;
    @NotBlank
    private String workNetworkLink;
    @NotBlank
    private String workIntroduction;
    @NotBlank
    private String workFileAddress;
}

package cn.edu.scu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author TZQ
 * @Description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TeamProjectDetail extends ProjectDetail {
    @NotBlank
    private String teamName;
    @NotBlank
    private String teamType;
    @NotBlank
    private String teamAddress;
    @NotBlank
    private String teamPhone;

    @NotBlank
    private String stampFileAddress;

    public int hashCode(boolean needApplyId) {
        if (needApplyId) {
            return hashCode();
        }
        return (getType() + getPersonName() + getLicenseNum() + getPhoneNum()
                + getEmail() + getAddress() + getWorkTitle() + getWorkNetworkLink()
                + getWorkIntroduction() + getWorkFileAddress() + getTeamName()
                + getTeamType() + getTeamAddress() + getTeamPhone()
                + getStampFileAddress()).hashCode();
    }
}

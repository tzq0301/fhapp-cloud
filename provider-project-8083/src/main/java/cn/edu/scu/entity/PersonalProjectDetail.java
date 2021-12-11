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
public class PersonalProjectDetail extends ProjectDetail {
    @NotBlank
    private String companyType;
    @NotBlank
    private String companyAddress;
    @NotBlank
    private String companyName;

    @NotBlank
    private String idCardPicFrontAddress;
    @NotBlank
    private String idCardPicEndAddress;

    public int hashCode(boolean needApplyId) {
        if (needApplyId) {
            return hashCode();
        }
        return (getType() + getPersonName() + getLicenseNum() + getPhoneNum()
                + getEmail() + getAddress() + getWorkTitle() + getWorkNetworkLink()
                + getWorkIntroduction() + getWorkFileAddress() + getCompanyType()
                + getCompanyAddress() + getCompanyName()
                + getIdCardPicFrontAddress() + getIdCardPicEndAddress()).hashCode();
    }
}

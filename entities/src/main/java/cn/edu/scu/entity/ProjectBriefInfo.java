package cn.edu.scu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author TZQ
 * @Description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectBriefInfo implements Serializable {
    private static final long serialVersionUID = 2145430038388950370L;

    private String isSubmitted;
    private String projectId;
    private String projectName;
    private String projectType;
    private String isPersonal;
}

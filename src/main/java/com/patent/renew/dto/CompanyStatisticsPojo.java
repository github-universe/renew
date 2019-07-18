package com.patent.renew.dto;

import com.patent.renew.annotation.InstanceField;

import lombok.Data;

import java.util.Date;

/**
 * Company statistics data model.
 *
 * @author: Gang Zhang
 * @date: 2019/7/16
 */
@Data
public class CompanyStatisticsPojo {

    private String companyId;

    private String companyName;

    @InstanceField
    private Integer loginNum;

    @InstanceField
    private Integer searchNum;

    @InstanceField
    private Integer exportNum;

    @InstanceField
    private Integer exportPdfNum;

    @InstanceField
    private Integer exportXlsNum;

    @InstanceField
    private Integer analysisNum;

    @InstanceField
    private Integer landscapeNum;

    @InstanceField
    private Integer viewNum;

    @InstanceField
    private Integer chemicalNum;

    @InstanceField
    private Integer alertCreatedNum;

    @InstanceField
    private Integer workSpaceCreatedNum;

    @InstanceField
    private String renew;

    private Date beginAt;

    private Date endAt;
}

package com.patent.renew.dto;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

/**
 * Company mixed data model.
 *
 * @author: Gang Zhang
 * @date: 2019/7/16
 */
@Data
public class CompanyMixedPojo {

    /**
     * base data
     */
    private String companyId;

    private String companyName;

    /**
     * config data
     */
    private Integer accountNum;

    private Integer ipLogin;

    private String timeZoneId;

    //private Integer accountLimited;

    private Integer seats;

    private Integer loginNum;

    private Integer ruleIndependentNum;

    private Integer ays;

    private Integer dbAys;

    private Integer dbSearch;

    private Integer dbSuper;

    private Integer dbTrial;

    private Integer pro;

    private Integer landscape;

    private Integer npl;

    private Integer smeBasic;

    private Integer workspace;

    private Integer chemicalMoc;

    private Integer ipreportPro;

    private Integer insights;

    /**
     * statistics data
     */
    private Integer searchNum;

    private Integer exportNum;

    private Integer exportPdfNum;

    private Integer exportXlsNum;

    private Integer analysisNum;

    private Integer landscapeNum;

    private Integer viewNum;

    private Integer chemicalNum;

    private Integer alertCreatedNum;

    private Integer workspaceCreatedNum;

    private Integer renew;

    private Date beginAt;

    private Date endAt;
}

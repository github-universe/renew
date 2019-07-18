package com.patent.renew.entity;

import lombok.Data;

import org.springframework.data.annotation.Id;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Company mixed data model.
 *
 * @author: Gang Zhang
 * @date: 2019/7/16
 */
@Data
@Entity
@Table(name = "company_mixed_model")
public class CompanyMixed {

    @Id
    @Column(name = "id")
    private String id;

    /**
     * base data
     */
    @Column(name = "company_id", nullable = false)
    private String companyId;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    /**
     * config data
     */
    @Column(name = "account_num")
    private Integer accountNum;

    @Column(name = "ip_login")
    private Integer ipLogin;

    @Column(name = "timezone_id")
    private String timeZoneId;

    @Column(name = "account_limited")
    private Integer accountLimited;

    @Column(name = "seats")
    private Integer seats;

    @Column(name = "loginnum")
    private Integer loginNum;

    @Column(name = "rule_independent_num")
    private Integer ruleIndependentNum;

    @Column(name = "ays")
    private Integer ays;

    @Column(name = "db_ays")
    private Integer dbAys;

    @Column(name = "db_search")
    private Integer dbSearch;

    @Column(name = "db_super")
    private Integer dbSuper;

    @Column(name = "db_trial")
    private Integer dbTrial;

    @Column(name = "pro")
    private Integer pro;

    @Column(name = "landscape")
    private Integer landscape;

    @Column(name = "npl")
    private Integer npl;

    @Column(name = "sme_basic")
    private Integer smeBasic;

    @Column(name = "workspace")
    private Integer workspace;

    @Column(name = "chemical_moc")
    private Integer chemicalMoc;

    @Column(name = "ipreport_pro")
    private Integer ipreportPro;

    @Column(name = "insights")
    private Integer insights;

    /**
     * statistics data
     */
    @Column(name = "search_num")
    private Integer searchNum;

    @Column(name = "export_num")
    private Integer exportNum;

    @Column(name = "export_pdf_num")
    private Integer exportPdfNum;

    @Column(name = "export_xls_num")
    private Integer exportXlsNum;

    @Column(name = "analysis_num")
    private Integer analysisNum;

    @Column(name = "landscape_num")
    private Integer landscapeNum;

    @Column(name = "view_num")
    private Integer viewNum;

    @Column(name = "chemical_num")
    private Integer chemicalNum;

    @Column(name = "alert_created_num")
    private Integer alertCreatedNum;

    @Column(name = "workSpace_created_num")
    private Integer workSpaceCreatedNum;

    @Column(name = "begin_at")
    private Date beginAt;

    @Column(name = "end_at")
    private Date endAt;

    @Column(name = "renew")
    private String renew;
}

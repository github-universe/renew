package com.patent.renew.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Company statistics data model.
 *
 * @author: Gang Zhang
 * @date: 2019/7/16
 */
@Data
@Entity
@Table(schema = "public", name = "company_statistics_training_model")
public class CompanyStatisticsTraining {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "search_num")
    private Integer searchNum;

    @Column(name = "export_num")
    private Integer exportNum;

    //@Column(name = "export_pdf_num")
    //private Integer exportPdfNum;

    //@Column(name = "export_xls_num")
    //private Integer exportXlsNum;

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

    @Column(name = "workspace_created_num")
    private Integer workSpaceCreatedNum;

    @Column(name = "login_num")
    private Integer loginNum;

    @Column(name = "renew")
    private String renew;
}

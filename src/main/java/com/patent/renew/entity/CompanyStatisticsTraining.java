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

    @Column(name = "searchnum")
    private Integer searchNum;

    @Column(name = "exportnum")
    private Integer exportNum;

    @Column(name = "exportpdfnum")
    private Integer exportPdfNum;

    @Column(name = "exportxlsnum")
    private Integer exportXlsNum;

    @Column(name = "analysisnum")
    private Integer analysisNum;

    @Column(name = "landscapenum")
    private Integer landscapeNum;

    @Column(name = "viewnum")
    private Integer viewNum;

    @Column(name = "chemicalnum")
    private Integer chemicalNum;

    @Column(name = "alertcreatednum")
    private Integer alertCreatedNum;

    @Column(name = "workspacecreatednum")
    private Integer workSpaceCreatedNum;

    @Column(name = "renew")
    private String renew;
}

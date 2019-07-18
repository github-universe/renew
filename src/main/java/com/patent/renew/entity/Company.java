/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent.renew.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author toryzhou
 * @since 2019-07-11
 */
@Data
@Entity
@Table(schema = "public", name = "company_model")
public class Company implements Serializable {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

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

    @Column(name = "renew")
    private Integer renew;

}

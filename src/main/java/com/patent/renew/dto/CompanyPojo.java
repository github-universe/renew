/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent.renew.dto;

import com.patent.renew.annotation.InstanceField;

import lombok.Data;

/**
 * @author toryzhou
 * @since 2019-07-14
 */
@Data
public class CompanyPojo {

    private String id;

    private String companyName;

    @InstanceField
    private Integer accountNum;

    @InstanceField
    private Integer ipLogin;

    private String timeZoneId;

    @InstanceField
    private Integer accountLimited;

    @InstanceField
    private Integer seats;

    @InstanceField
    private Integer ruleIndependentNum;

    @InstanceField
    private Integer ays;

    @InstanceField
    private Integer dbAys;

    @InstanceField
    private Integer dbSearch;

    @InstanceField
    private Integer dbSuper;

    @InstanceField
    private Integer dbTrial;

    @InstanceField
    private Integer pro;

    @InstanceField
    private Integer landscape;

    @InstanceField
    private Integer npl;

    @InstanceField
    private Integer smeBasic;

    @InstanceField
    private Integer workspace;

    @InstanceField
    private Integer chemicalMoc;

    @InstanceField
    private Integer ipreportPro;

    @InstanceField
    private Integer insights;

    @InstanceField
    private String renew;
}

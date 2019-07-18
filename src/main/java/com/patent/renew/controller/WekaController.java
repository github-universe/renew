/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent.renew.controller;

import com.patent.renew.dto.CompanyMixedPojo;
import com.patent.renew.dto.CompanyPojo;
import com.patent.renew.dto.CompanyStatisticsPojo;
import com.patent.renew.weka.WekaClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author toryzhou
 * @since 2019-07-14
 */
@RestController
@RequestMapping("/weka")
public class WekaController extends BaseController {

    @Autowired
    private WekaClient wekaClient;

    @PostMapping("/base/prediction")
    public ResponseEntity<?> predictByBase(@RequestBody CompanyPojo companyPojo) throws Exception {
        double predictiveValue = wekaClient.predictByCompanyBase(companyPojo);
        return ResponseEntity.ok().body(predictiveValue);
    }

    @PostMapping("/statistics/prediction")
    public ResponseEntity<?> predictByStatistics(@RequestBody CompanyStatisticsPojo statisticsPojo) throws Exception {
        double predictiveValue = wekaClient.predictByCompanyStatistics(statisticsPojo);
        return ResponseEntity.ok().body(predictiveValue);
    }

    @PostMapping("/mixed/prediction")
    public ResponseEntity<?> predictByMixedData(@RequestBody CompanyMixedPojo mixedPojo) throws Exception {
        double predictiveValue = wekaClient.predictByMixedStatistics(mixedPojo);
        return ResponseEntity.ok().body(predictiveValue);
    }


}

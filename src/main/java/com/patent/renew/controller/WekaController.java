/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent.renew.controller;

import com.patent.renew.dto.CompanyModel;
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
public class WekaController {

    @Autowired
    private WekaClient wekaClient;

    @PostMapping("/prediction")
    public ResponseEntity<?> predict(@RequestBody CompanyModel companyModel) throws Exception {
        double predictiveValue = wekaClient.predict(companyModel);
        return ResponseEntity.ok().body(predictiveValue);
    }

}

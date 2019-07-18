/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent.renew.controller;

import com.patent.renew.service.DataNormalizeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * normalization controller
 *
 * @author Gang Zhang
 * @since 2019-07-17
 */
@RestController
@RequestMapping("/normalization")
public class DataNormalizeController {

    @Autowired
    private DataNormalizeService normalizeService;

    @GetMapping("/base")
    public ResponseEntity<?> normalizeBase() throws Exception {
        normalizeService.normalizeCompanyData2Db();
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/statistics")
    public ResponseEntity<?> normalizeStatistics() throws Exception {
        normalizeService.normalizeStatisticsData2Db();
        return ResponseEntity.ok().body("success");
    }

    @GetMapping("/mixed")
    public ResponseEntity<?> normalizeMixedData() throws Exception {
        normalizeService.normalizeCompanyMixedData2Db();
        return ResponseEntity.ok().body("success");
    }
}

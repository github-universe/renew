
package com.patent.renew.controller;

import com.patent.renew.dto.CompanyMappings;
import com.patent.renew.dto.CompanyMixedPojo;
import com.patent.renew.dto.CompanyPojo;
import com.patent.renew.dto.CompanyStatisticsPojo;
import com.patent.renew.entity.Company;
import com.patent.renew.service.CompanyBaseService;
import com.patent.renew.service.CompanyMixedService;
import com.patent.renew.service.CompanyStatisticsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyBaseService companyService;

    @Autowired
    private CompanyStatisticsService statisticsService;

    @Autowired
    private CompanyMixedService mixedService;

    @PostMapping
    public void save(@RequestBody @Valid CompanyPojo company) {
        companyService.save(company);
    }

    @GetMapping("/base/{id}")
    public ResponseEntity<CompanyPojo> getBaseById(@PathVariable String id) {
        return ResponseEntity.ok().body(beanMapper.map(companyService.findById(id), CompanyPojo.class));
    }

    @GetMapping("/statistics/{id}")
    public ResponseEntity<List<CompanyStatisticsPojo>> findStatisticsById(@PathVariable String id) {
        return ResponseEntity.ok().body(beanMapper.mapAsList(statisticsService.findByCompanyId(id), CompanyStatisticsPojo.class));
    }

    @GetMapping("/mixed/{id}")
    public ResponseEntity<List<CompanyMixedPojo>> findMixedById(@PathVariable String id) {
        return ResponseEntity.ok().body(beanMapper.mapAsList(mixedService.findByCompanyId(id), CompanyMixedPojo.class));
    }

    @GetMapping("/mappings")
    public ResponseEntity<List<CompanyMappings>> getAllCompanyMappings() {
        return ResponseEntity.ok().body(companyService.getCompanyMappings());
    }

}

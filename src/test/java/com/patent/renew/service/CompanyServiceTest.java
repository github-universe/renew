/*
 * Copyright (C) 2019 PatSnap Pte Ltd, All Rights Reserved.
 */

package com.patent.renew.service;

import com.patent.renew.entity.Company;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author toryzhou
 * @since 2019-07-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyServiceTest {

    @Autowired
    private CompanyService companyService;

    @Test
    public void test() {

        Company company = companyService.findById("fe1bb88fe69a42f1a5e3c39b5ee62f72");
        Assert.assertEquals("上海", company.getCompanyName().substring(0, 2));

    }
}

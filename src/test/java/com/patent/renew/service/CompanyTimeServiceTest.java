package com.patent.renew.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author minmin
 * @Date 2019/7/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyTimeServiceTest {

    @Autowired
    private CompanyTimeService companyTimeService;

    @Test
    public void testCompanyTime(){
        companyTimeService.selectAll();
    }
}

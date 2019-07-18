package com.patent.renew.service;

import com.patent.renew.dao.CompanyStatisticsRepository;
import com.patent.renew.entity.CompanyStatistics;
import com.patent.renew.entity.CompanyTime;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author minmin
 * @Date 2019/7/16
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyUbTest {

    @Autowired
    private CompanyStatisticsRepository companyStatisticsRepository;

    @Autowired
    private CompanyTimeService companyTimeService;

    @Test
    public void testUb() {
        List<CompanyStatistics> statistics = companyStatisticsRepository.selectCompanyStatistics("dbce4b86cab34a0b879e129a39efd5ef", "2017-01-01 00:00:00", "2018-07-31 23:59:59", "aaaa");
        System.out.println("aa");
    }

    @Test
    public void saveCompanyStatistic() {
        List<CompanyTime> companyTimes = companyTimeService.selectAll();
        DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

        System.out.println("start to tag!");
        CompanyTime companyTime111 = companyTimes.get(5);
        companyTimes = new ArrayList<>();
        companyTimes.add(companyTime111);

        int companyTimeCount = 0;
        int tagCount = 0;
        for (CompanyTime companyTime : companyTimes) {
            System.out.println("companyTimeCount : " + ++companyTimeCount);
            String companyId = companyTime.getCompanyId();
            String companyName = companyTime.getCompanyName();

            Date minBeginAt = companyTime.getAccountMinBeginAt();
            Date maxEndAt = companyTime.getAccountMaxEndAt();

            DateTime minBeginAtDateTime = new DateTime(minBeginAt);
            DateTime maxEndAtDateTime = new DateTime(maxEndAt);

            if (maxEndAtDateTime.minusYears(1).getMillis() > minBeginAtDateTime.getMillis()) {
                if (maxEndAtDateTime.isAfterNow()) {
                    String beginAtStr = maxEndAtDateTime.minusYears(1).toString(format);
                    String endAtStr = maxEndAtDateTime.toString(format);
                    List<CompanyStatistics> statistics = companyStatisticsRepository.selectCompanyStatistics(companyId, beginAtStr, endAtStr, companyName);
                    if (CollectionUtils.isNotEmpty(statistics)) {
                        CompanyStatistics statistic = statistics.get(0);
                        statistic.setRenew("2");
                        statistic.setId(UUID.randomUUID().toString().replace("-", ""));
                        companyStatisticsRepository.save(statistic);
                        System.out.println("tagCount : " + ++tagCount);
                    }

                    String beginAtStr1 = minBeginAtDateTime.toString(format);
                    String endAtStr1 = maxEndAtDateTime.minusYears(1).toString(format);
                    List<CompanyStatistics> statistics1 = companyStatisticsRepository.selectCompanyStatistics(companyId, beginAtStr1, endAtStr1, companyName);
                    if (CollectionUtils.isNotEmpty(statistics1)) {
                        CompanyStatistics statistic1 = statistics1.get(0);
                        statistic1.setRenew("1");
                        statistic1.setId(UUID.randomUUID().toString().replace("-", ""));
                        companyStatisticsRepository.save(statistic1);

                        System.out.println("tagCount : " + ++tagCount);
                    }

                } else {

                    String beginAtStr = maxEndAtDateTime.minusYears(1).toString(format);
                    String endAtStr = maxEndAtDateTime.toString(format);
                    List<CompanyStatistics> statistics2 = companyStatisticsRepository.selectCompanyStatistics(companyId, beginAtStr, endAtStr, companyName);
                    if (CollectionUtils.isNotEmpty(statistics2)) {
                        CompanyStatistics statistic2 = statistics2.get(0);
                        statistic2.setRenew("0");
                        statistic2.setId(UUID.randomUUID().toString().replace("-", ""));
                        companyStatisticsRepository.save(statistic2);
                        System.out.println("tagCount : " + ++tagCount);
                    }

                    String beginAtStr1 = minBeginAtDateTime.toString(format);
                    String endAtStr1 = maxEndAtDateTime.minusYears(1).toString(format);
                    List<CompanyStatistics> statistics3 = companyStatisticsRepository.selectCompanyStatistics(companyId, beginAtStr1, endAtStr1, companyName);
                    if (CollectionUtils.isNotEmpty(statistics3)) {
                        CompanyStatistics statistic3 = statistics3.get(0);
                        statistic3.setRenew("1");
                        statistic3.setId(UUID.randomUUID().toString().replace("-", ""));
                        companyStatisticsRepository.save(statistic3);
                        System.out.println("tagCount : " + ++tagCount);
                    }

                }
            } else {
                if (maxEndAtDateTime.isAfterNow()) {
                    String beginAtStr = maxEndAtDateTime.toString(format);
                    String endAtStr = maxEndAtDateTime.toString(format);
                    List<CompanyStatistics> statistics = companyStatisticsRepository.selectCompanyStatistics(companyId, beginAtStr, endAtStr, companyName);
                    if (CollectionUtils.isNotEmpty(statistics)) {
                        CompanyStatistics statistic = statistics.get(0);
                        statistic.setRenew("2");
                        statistic.setId(UUID.randomUUID().toString().replace("-", ""));
                        companyStatisticsRepository.save(statistic);
                        System.out.println("tagCount : " + ++tagCount);
                    }
                }

            }
        }

    }

}

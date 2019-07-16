
package com.patent.renew.service;

import com.patent.renew.dao.CompanyStatisticsRepository;
import com.patent.renew.entity.CompanyStatistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Company statistics service.
 *
 * @author: Gang Zhang
 * @date: 2019/7/16
 */
@Service
public class CompanyStatisticsService {


    private static final Integer UN_RENEWED = 0;
    private static final Integer RENEWED = 1;
    private static final Integer TO_RENEWED = 2;


    @Autowired
    private CompanyStatisticsRepository companyStatisticsRepository;

    public CompanyStatistics findByCompanyId(String id) {
        return companyStatisticsRepository.findByCompanyId(id);
    }

    public List<CompanyStatistics> getRawCompanyStaticstics(Integer renew) {
        List<CompanyStatistics> rawStatistics = new ArrayList<>(1);
        List<CompanyStatistics> renewedStatistics = companyStatisticsRepository.findByRenew(RENEWED);
        List<CompanyStatistics> unRenewedStatistics = companyStatisticsRepository.findByRenew(UN_RENEWED);
        rawStatistics.addAll(renewedStatistics);
        rawStatistics.addAll(unRenewedStatistics);
        return rawStatistics;
    }


}

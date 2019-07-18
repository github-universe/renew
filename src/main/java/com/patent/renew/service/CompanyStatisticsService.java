
package com.patent.renew.service;

import com.patent.renew.dao.CompanyStatisticsRepository;
import com.patent.renew.dao.CompanyStatisticsTrainingRepository;
import com.patent.renew.entity.CompanyStatistics;
import com.patent.renew.entity.CompanyStatisticsTraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Company statistics service.
 *
 * @author: Gang Zhang
 * @date: 2019 /7/16
 */
@Service
public class CompanyStatisticsService {

    private static final String UN_RENEWED = "0";
    private static final String RENEWED = "1";
    private static final String TO_RENEWED = "2";


    @Autowired
    private CompanyStatisticsRepository companyStatisticsRepository;

    @Autowired
    private CompanyStatisticsTrainingRepository trainingRepository;

    /**
     * Find by company id list.
     *
     * @param id the id
     * @return the list
     */
    public  List<CompanyStatistics> findByCompanyId(String id) {
        return companyStatisticsRepository.findByCompanyId(id);
    }

    /**
     * Gets raw prediction company staticstics.
     *
     * @return the raw prediction company staticstics
     */
    public List<CompanyStatistics> getRawPredictionCompanyStaticstics() {
        return companyStatisticsRepository.findByRenew(TO_RENEWED);
    }


    /**
     * Gets raw training company staticstics.
     *
     * @return the raw training company staticstics
     */
    public List<CompanyStatistics> getRawTrainingCompanyStaticstics() {
        List<CompanyStatistics> rawStatistics = new ArrayList<>(1);
        List<CompanyStatistics> renewedStatistics = companyStatisticsRepository.findByRenew(RENEWED);
        List<CompanyStatistics> unRenewedStatistics = companyStatisticsRepository.findByRenew(UN_RENEWED);
        rawStatistics.addAll(renewedStatistics);
        rawStatistics.addAll(unRenewedStatistics);
        return rawStatistics;
    }

    /**
     * Save all training data.
     *
     * @param statisticsTrainings the statistics trainings
     */
    public void saveAllTrainingData(List<CompanyStatisticsTraining> statisticsTrainings) {
        trainingRepository.saveAll(statisticsTrainings);
    }

}


package com.patent.renew.service;

import com.patent.renew.dao.CompanyMixedRepository;
import com.patent.renew.dao.CompanyMixedTrainingRepository;
import com.patent.renew.entity.CompanyMixed;
import com.patent.renew.entity.CompanyMixedTraining;
import com.patent.renew.entity.CompanyStatistics;
import com.patent.renew.entity.CompanyStatisticsTraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Company mixed data service.
 *
 * @author: Gang Zhang
 * @date: 2019 /7/16
 */
@Service
public class CompanyMixedService {

    private static final String UN_RENEWED = "0";
    private static final String RENEWED = "1";
    private static final String TO_RENEWED = "2";


    @Autowired
    private CompanyMixedRepository mixedRepository;

    @Autowired
    private CompanyMixedTrainingRepository trainingRepository;

    /**
     * Find by company id list.
     *
     * @param id the id
     * @return the list
     */
    public  List<CompanyMixed> findByCompanyId(String id) {
        return mixedRepository.findByCompanyId(id);
    }

    /**
     * Gets raw prediction company staticstics.
     *
     * @return the raw prediction company staticstics
     */
    public List<CompanyMixed> getRawPredictionCompanyMixedData() {
        return mixedRepository.findByRenew(TO_RENEWED);
    }


    /**
     * Gets raw training company staticstics.
     *
     * @return the raw training company staticstics
     */
    public List<CompanyMixed> getRawTrainingCompanyMixedData() {
        List<CompanyMixed> rawStatistics = new ArrayList<>(1);
        List<CompanyMixed> renewedStatistics = mixedRepository.findByRenew(RENEWED);
        List<CompanyMixed> unRenewedStatistics = mixedRepository.findByRenew(UN_RENEWED);
        rawStatistics.addAll(renewedStatistics);
        rawStatistics.addAll(unRenewedStatistics);
        return rawStatistics;
    }

    /**
     * Save all training data.
     *
     * @param mixedTrainings the statistics trainings
     */
    public void saveAllTrainingData(List<CompanyMixedTraining> mixedTrainings) {
        trainingRepository.saveAll(mixedTrainings);
    }

}

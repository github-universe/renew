package com.patent.renew.service;

import com.patent.renew.dto.CompanyMixedPojo;
import com.patent.renew.dto.CompanyPojo;
import com.patent.renew.dto.CompanyStatisticsPojo;
import com.patent.renew.entity.CompanyMixed;
import com.patent.renew.entity.CompanyMixedTraining;
import com.patent.renew.entity.CompanyStatistics;
import com.patent.renew.entity.CompanyStatisticsTraining;
import com.patent.renew.entity.CompanyTraining;

import ma.glasnost.orika.MapperFacade;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data normalize service.
 *
 * @author: Gang Zhang
 * @date: 2019 /7/17
 */
@Service
public class DataNormalizeService {


    @Autowired
    private MapperFacade beanMapper;

    @Autowired
    private CompanyBaseService companyBaseService;

    @Autowired
    private CompanyStatisticsService statisticsService;

    @Autowired
    private CompanyMixedService mixedService;

    /**
     * Normalize company data 2 db.
     */
    public void normalizeCompanyData2Db() {
        List<CompanyTraining> companyTrainings = beanMapper.mapAsList(companyBaseService.getRawTrainingCompanyData(), CompanyTraining.class);
        companyBaseService.saveAllTrainingData(companyTrainings);
    }

    /**
     * Normalize company data company training.
     *
     * @param companyPojo the company pojo
     * @return the company training
     */
    public CompanyTraining normalizeCompanyData(CompanyPojo companyPojo) {
        return beanMapper.map(companyPojo, CompanyTraining.class);
    }


    /**
     * Normalize statistics data 2 db.
     */
    public void normalizeStatisticsData2Db() {
        List<CompanyStatistics> rawStatistics = statisticsService.getRawTrainingCompanyStaticstics();
        List<CompanyStatisticsTraining> statisticsTrainings = rawStatistics.stream().map(this::normalizeStatistics2Year).collect(Collectors.toList());
        statisticsService.saveAllTrainingData(statisticsTrainings);
    }

    /**
     * Normalize company statistics data company statistics training.
     *
     * @param statisticsPojo the statistics pojo
     * @return the company statistics training
     */
    public CompanyStatisticsTraining normalizeCompanyStatisticsData(CompanyStatisticsPojo statisticsPojo) {
        CompanyStatistics statistics = beanMapper.map(statisticsPojo, CompanyStatistics.class);
        return normalizeStatistics2Year(statistics);
    }


    public void normalizeCompanyMixedData2Db() {
        List<CompanyMixed> rawMixedDatas = mixedService.getRawTrainingCompanyMixedData();
        List<CompanyMixedTraining> mixedTrainings = rawMixedDatas.stream().map(this::normalizeMixed2Year).collect(Collectors.toList());
        mixedService.saveAllTrainingData(mixedTrainings);
    }

    /**
     * Normalize company mixed data company mixed training.
     *
     * @param mixedPojo the mixed pojo
     * @return the company mixed training
     */
    public CompanyMixedTraining normalizeCompanyMixedData(CompanyMixedPojo mixedPojo) {
        CompanyMixed mixed = beanMapper.map(mixedPojo, CompanyMixed.class);
        return normalizeMixed2Year(mixed);
    }

    /**
     * Normalize company statistics training.
     *
     * @return the company statistics training
     */
    private CompanyStatisticsTraining normalizeStatistics2Year(CompanyStatistics companyStatistics) {
        Date endAt = companyStatistics.getEndAt();
        Date beginAt = companyStatistics.getBeginAt();
        long delta = endAt.getTime() - beginAt.getTime();
        long yearMills = DateUtils.MILLIS_PER_DAY * 365;
        double factor = (double) delta / yearMills;
        CompanyStatisticsTraining normalizeData = new CompanyStatisticsTraining();
        normalizeData.setSearchNum((int) Math.ceil(companyStatistics.getSearchNum() * factor));
        normalizeData.setExportNum((int) Math.ceil(companyStatistics.getExportNum() * factor));
        normalizeData.setExportPdfNum((int) Math.ceil(companyStatistics.getExportPdfNum() * factor));
        normalizeData.setExportXlsNum((int) Math.ceil(companyStatistics.getExportXlsNum() * factor));
        normalizeData.setAnalysisNum((int) Math.ceil(companyStatistics.getAnalysisNum() * factor));
        normalizeData.setLandscapeNum((int) Math.ceil(companyStatistics.getLandscapeNum() * factor));
        normalizeData.setViewNum((int) Math.ceil(companyStatistics.getViewNum() * factor));
        normalizeData.setChemicalNum((int) Math.ceil(companyStatistics.getChemicalNum() * factor));
        normalizeData.setAlertCreatedNum((int) Math.ceil(companyStatistics.getAlertCreatedNum() * factor));
        normalizeData.setWorkSpaceCreatedNum((int) Math.ceil(companyStatistics.getWorkSpaceCreatedNum() * factor));
        return normalizeData;
    }


    private CompanyMixedTraining normalizeMixed2Year(CompanyMixed companyMixed) {
        Date endAt = companyMixed.getEndAt();
        Date beginAt = companyMixed.getBeginAt();
        long delta = endAt.getTime() - beginAt.getTime();
        long yearMills = DateUtils.MILLIS_PER_DAY * 365;
        double factor = (double) delta / yearMills;
        CompanyMixedTraining normalizeData = beanMapper.map(companyMixed, CompanyMixedTraining.class);
        normalizeData.setSearchNum((int) Math.ceil(companyMixed.getSearchNum() * factor));
        normalizeData.setExportNum((int) Math.ceil(companyMixed.getExportNum() * factor));
        normalizeData.setExportPdfNum((int) Math.ceil(companyMixed.getExportPdfNum() * factor));
        normalizeData.setExportXlsNum((int) Math.ceil(companyMixed.getExportXlsNum() * factor));
        normalizeData.setAnalysisNum((int) Math.ceil(companyMixed.getAnalysisNum() * factor));
        normalizeData.setLandscapeNum((int) Math.ceil(companyMixed.getLandscapeNum() * factor));
        normalizeData.setViewNum((int) Math.ceil(companyMixed.getViewNum() * factor));
        normalizeData.setChemicalNum((int) Math.ceil(companyMixed.getChemicalNum() * factor));
        normalizeData.setAlertCreatedNum((int) Math.ceil(companyMixed.getAlertCreatedNum() * factor));
        normalizeData.setWorkSpaceCreatedNum((int) Math.ceil(companyMixed.getWorkSpaceCreatedNum() * factor));
        return normalizeData;
    }
}

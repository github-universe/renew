
package com.patent.renew.service;

import com.patent.renew.dao.CompanyRepository;
import com.patent.renew.dao.CompanyTrainingRepository;
import com.patent.renew.dto.CompanyMappings;
import com.patent.renew.dto.CompanyPojo;
import com.patent.renew.entity.Company;
import com.patent.renew.entity.CompanyTraining;

import ma.glasnost.orika.MapperFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Company service.
 *
 * @author: Gang Zhang
 * @date: 2019 /7/16
 */
@Service
public class CompanyBaseService {

    private static final String UN_RENEWED = "0";
    private static final String RENEWED = "1";
    private static final String TO_RENEWED = "2";


    @Autowired
    protected MapperFacade beanMapper;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyTrainingRepository companyTrainingRepository;

    /**
     * Save.
     *
     * @param companyPojo the company pojo
     */
    public void save(CompanyPojo companyPojo) {
        Company company = beanMapper.map(companyPojo, Company.class);
        companyRepository.save(company);
    }

    /**
     * Save all training data.
     *
     * @param companyTrainings the company trainings
     */
    public void saveAllTrainingData(List<CompanyTraining> companyTrainings) {
        companyTrainingRepository.saveAll(companyTrainings);
    }

    /**
     * Find by id company.
     *
     * @param id the id
     * @return the company
     */
    public Company findById(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    /**
     * Find by company name company.
     *
     * @param companyName the company name
     * @return the company
     */
    public Company findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }

    /**
     * Gets raw prediction company data.
     *
     * @return the raw prediction company data
     */
    public List<Company> getRawPredictionCompanyData() {
        return companyRepository.findByRenew(TO_RENEWED);
    }


    /**
     * Gets raw training company data.
     *
     * @return the raw training company data
     */
    public List<Company> getRawTrainingCompanyData() {
        List<Company> rawData = new ArrayList<>();
        List<Company> renewedCompanies = companyRepository.findByRenew(RENEWED);
        List<Company> unRenewedCompanies = companyRepository.findByRenew(UN_RENEWED);
        rawData.addAll(renewedCompanies);
        rawData.addAll(unRenewedCompanies);
        return rawData;
    }

    /**
     * Gets company mappings.
     *
     * @return the company mappings
     */
    @Cacheable(cacheNames = "companyMappings", key = "'Company_Id_Name_Mapping'")
    public List<CompanyMappings> getCompanyMappings() {
        Iterable<Company> companies = companyRepository.findAll();
        return beanMapper.mapAsList(companies, CompanyMappings.class);
    }

}


package com.patent.renew.service;

import com.patent.renew.dao.CompanyRepository;
import com.patent.renew.entity.Company;
import com.sun.org.apache.regexp.internal.RE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Company service.
 *
 * @author: Gang Zhang
 * @date: 2019/7/16
 */
@Service
public class CompanyService {

    private static final Integer UN_RENEWED = 0;
    private static final Integer RENEWED = 1;
    private static final Integer TO_RENEWED = 2;


    @Autowired
    private CompanyRepository companyRepository;

    public void save(Company company) {
        companyRepository.save(company);
    }

    public Company findById(String id) {
        return companyRepository.findById(id).orElse(null);
    }

    public Company findByCompanyName(String companyName) {
        return companyRepository.findByCompanyName(companyName);
    }

    public List<Company> getRawCompanyData() {
        List<Company> rawData = new ArrayList<>();
        List<Company> renewedCompanies = companyRepository.findByRenew(RENEWED);
        List<Company> unRenewedCompanies = companyRepository.findByRenew(UN_RENEWED);
        rawData.addAll(renewedCompanies);
        rawData.addAll(unRenewedCompanies);
        return rawData;
    }

}

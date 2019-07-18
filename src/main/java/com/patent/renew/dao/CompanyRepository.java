package com.patent.renew.dao;

import com.patent.renew.entity.Company;

import java.util.List;

public interface CompanyRepository extends BaseRespository<Company, String> {

    Company findByCompanyName(String name);

    List<Company> findByRenew(String renew);
}

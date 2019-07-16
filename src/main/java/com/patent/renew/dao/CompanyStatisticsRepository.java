package com.patent.renew.dao;

import com.patent.renew.entity.CompanyStatistics;

import java.util.List;


/**
 * Company statistics repository.
 *
 * @author: Gang Zhang
 * @date: 2019/7/16
 */
public interface CompanyStatisticsRepository extends BaseRespository<CompanyStatistics, String> {

    CompanyStatistics findByCompanyId(String companyId);

    List<CompanyStatistics> findByRenew(Integer renew);

}

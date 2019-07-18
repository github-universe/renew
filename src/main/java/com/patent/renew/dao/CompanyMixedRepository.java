package com.patent.renew.dao;

import com.patent.renew.entity.CompanyMixed;

import java.util.List;


/**
 * Company mixed repository.
 *
 * @author: Gang Zhang
 * @date: 2019/7/16
 */
public interface CompanyMixedRepository extends BaseRespository<CompanyMixed, String> {

    List<CompanyMixed> findByCompanyId(String companyId);

    List<CompanyMixed> findByRenew(Integer renew);

}

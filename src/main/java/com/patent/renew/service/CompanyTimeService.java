package com.patent.renew.service;

import com.patent.renew.dao.CompanyTimeRepository;
import com.patent.renew.entity.CompanyTime;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author minmin
 * @Date 2019/7/16
 */
@Service
public class CompanyTimeService {
    @Autowired
    private CompanyTimeRepository companyTimeRepository;

    public List<CompanyTime> selectAll() {
        return IteratorUtils.toList(companyTimeRepository.findAll().iterator());
    }
}

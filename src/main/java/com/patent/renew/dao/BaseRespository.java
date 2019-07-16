package com.patent.renew.dao;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseRespository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {

}

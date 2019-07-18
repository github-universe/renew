package com.patent.renew.controller;

import ma.glasnost.orika.MapperFacade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public class BaseController {

    @Autowired
    protected MapperFacade beanMapper;
}

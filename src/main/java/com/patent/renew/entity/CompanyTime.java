package com.patent.renew.entity;

import lombok.Data;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author minmin
 * @Date 2019/7/16
 */
@Data
@Entity
@Table(schema = "public", name = "company_time")
public class CompanyTime {

    @Id
    @Column(name = "id")
    private String id;

    private String companyName;

    private String companyId;

    private Date accountMinBeginAt;

    private Date accountMaxEndAt;
}

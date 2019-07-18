package com.patent.renew.dao;

import com.patent.renew.entity.CompanyStatistics;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


/**
 * Company statistics repository.
 *
 * @author: Gang Zhang
 * @date: 2019/7/16
 */
public interface CompanyStatisticsRepository extends BaseRespository<CompanyStatistics, String> {

    List<CompanyStatistics> findByCompanyId(String companyId);

    List<CompanyStatistics> findByRenew(String renew);


    @Query(value = "select \n" +
            "?2 as begin_at," +
            "?3 as end_at," +
            "?4 as company_name," +
            "ub.company_id,\n" +
            "1 as id ,\n" +
            "4 as renew,\n" +
            "sum(case when  ub.action  like 'Logged%' then 1 else 0 end) as login_num,\n" +
            "sum(case ub.action when 'Searched Patents' then 1 else 0 end) as search_num,\n" +
            "sum(case ub.action when 'Exported Patent Results' then 1 else 0 end) as export_num,\n" +
            "sum(case er.export_format when 'pdf' then er.export_num  else 0 end) as export_pdf_num,\n" +
            "sum(case er.export_format when 'xls' then er.export_num else 0 end) as export_xls_num,\n" +
            "sum(case ub.action when 'Viewed Analysis Overview' then 1 when 'Viewed Analysis Chart' then 1 else 0 end) as analysis_num,\n" +
            "sum(case ub.action when 'Generated Landscape' then 1  when 'pageEnter LANDSCAPE' then 1 else 0 end) as landscape_num,\n" +
            "sum(case ub.action when 'Viewed Patent' then 1 when 'Viewed Patent More Like This' then 1 when 'Viewed Patent Citation List' then 1  when 'Viewed Patent Family' then 1  when 'Viewed Patent Legal Info' then 1  when 'Viewed Patent Citation Map' then 1  else 0 end) as view_num,\n" +
            "sum(case ub.action when 'chemical' then 1 else 0 end) as chemical_num,\n" +
            "sum(case ub.action when 'Created Alert' then 1 else 0 end) as alert_created_num,\n" +
            "sum(case ub.action when 'Created Workspace' then 1 else 0 end) as workspace_created_num\n" +
            "FROM\n" +
            "\t(select * from tb_user_behavior where company_id = ?1 and  action_time BETWEEN ?2 and ?3) ub\n" +
            "LEFT JOIN tb_identity_account a\n" +
            "on ub.account_id = a.id\n" +
            "left join  tb_export_record er\n" +
            "on er.id = ub.id\n" +
            "GROUP BY ub.company_id",
            nativeQuery = true)
    List<CompanyStatistics> selectCompanyStatistics(String companyId, String startTime, String endTime, String companyNane);

}

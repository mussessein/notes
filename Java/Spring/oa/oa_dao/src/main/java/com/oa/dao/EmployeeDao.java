package com.oa.dao;

import com.oa.entity.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 此注解是让Spring找到这个接口的实现类,以便后续需要的时候注入
 其实不写这个注解,也可以找到实现类
 因为MapperScannerConfigurer这个bean,已经扫描了dao包
 */
@Repository("employeeDao")
public interface EmployeeDao {
    void insert(Employee employee);
    void update(Employee employee);
    void delete(String sn);
    Employee select(String sn);
    List<Employee> selectAll();
    //xml的sql语句有if判断条件，需要传入@Param("dsn")
    List<Employee> selectByDepartmentAndPost(@Param("dsn") String dsn, @Param("post") String post);
}

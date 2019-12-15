package com.oa.dao;

import com.oa.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 此注解是让Spring找到这个接口的实现类,以便后续需要的时候注入
 其实不写这个注解,也可以找到实现类
 因为MapperScannerConfigurer这个bean,已经扫描了dao包
 */
@Repository("departmentDao")
public interface DepartmentDao {
    void insert(Department department);
    void update(Department department);
    void delete(String sn);
    Department select(String sn);
    List<Department> selectAll();
}

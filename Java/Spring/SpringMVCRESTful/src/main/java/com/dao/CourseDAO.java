package com.dao;

import com.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Repository 将此类作为Bean 注入到IOC容器
 */
@Repository
public class CourseDAO {
    private Map<Integer, Course> courses = new HashMap<Integer, Course>();

    /**
     * 新增课程
     */
    public void add(Course course){
        courses.put(course.getId(),course);
    }

    /**
     * 查询全部课程
     */
    public Collection<Course> getAll(){
        return courses.values();
    }

    /**
     * 通过id查询课程
     */
    public Course getById(int id){
        return courses.get(id);
    }

    /**
     * 修改课程
     */
    public void update(Course course){
        courses.put(course.getId(),course);
    }

    /**
     * 删除课程
     */
    public void deleteById(int id){
        //id即为key值
        courses.remove(id);
    }
}

package com.controller;

import com.dao.CourseDAO;
import com.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 增 ：Post
 * 删 ：delete
 * 改 ：put
 * 查 : get
 */
@Controller
public class CourseController {
    //自动装载，IOC容器完成依赖注入，如果没有此注释，jsp传过来的参数，无法自动完成set方法，
    @Autowired
    private CourseDAO courseDAO;

    /**
     * 添加课程 @PostMapping限制只有post请求可以进入此方法
     * add.jsp页面的请求，会将id/name/price，作为一个对象传入到此方法中，
     * 然后进行添加，重定向到getAll方法，显示所有课程
     */
    @PostMapping(value = "/add")
    public String add(Course course){
        courseDAO.add(course);
        //一个重定向请求，添加完课程之后，跳转到getAll方法，显示所有课程
        return "redirect:/getAll";
    }

    /**
     * 查询全部课程
     * @GetMapping  限制只有Get请求可以进入此方法
     * @return
     */
    @GetMapping(value = "/getAll")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("courses",courseDAO.getAll());
        return modelAndView;
    }

    /**
     * 通过id查询课程
     * @PathVariable(value = "id")将id的值赋给形参 id
     */
    @GetMapping(value = "/getById/{id}")
    public ModelAndView getById(@PathVariable(value = "id") int id){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("course",courseDAO.getById(id));
        return modelAndView;
    }

    /**修改课程
     *
     */
    @PutMapping(value = "/update")
    public String update(Course course){
        courseDAO.update(course);
        return "redirect:/getAll";
    }

    /**
     * 删除课程
     */
    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id")  int id){
        courseDAO.deleteById(id);
        //删除完成，跳转到getAll，回到index界面
        return "redirect:/getAll";
    }
}

package com.userauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Slf4j:免实例化logger对象，直接使用log
 */
@RestController
@RequestMapping("page")
@Slf4j
public class PageController {
    // private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    @RequestMapping("info")
    public String info(ModelMap modelMap){
        modelMap.put("code","modelMap test");
        return "page";
    }
}

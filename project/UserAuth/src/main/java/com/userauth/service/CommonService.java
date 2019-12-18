package com.userauth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userauth.entity.Log;
import com.userauth.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 工具类的service，一些通用的方法
 **/
@Service
@EnableAsync
public class CommonService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LogMapper logMapper;

    /**
     * 定义响应返回给前端
     * 封装响应，给前端
     * message是自定义的BaseResponse
     */
    public void print(HttpServletResponse response, Object message){
        try {
            response.setStatus(HttpStatus.OK.value());  // 设置状态码：200响应码
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);// 设置响应头
            response.setHeader("Cache-Control", "no-cache, must-revalidate");// 设置header
            PrintWriter writer = response.getWriter();
            // 将message写入
            writer.write(objectMapper.writeValueAsString(message));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步调用此方法，将log记录写入数据库中
     * @param log
     */
    @Async
    public void insertLog(Log log){
        try {
            logMapper.insertSelective(log);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //将自定义的响应信息返回给到前端
    public void writeJsonResponse(Object message, HttpServletRequest request, HttpServletResponse response){
        PrintWriter out=null;
        try {
            //跨域设置
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            out=response.getWriter();
            out.write(objectMapper.writeValueAsString(message));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (out!=null){
                out.close();
            }
        }
    }

}





















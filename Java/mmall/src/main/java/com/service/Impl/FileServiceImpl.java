package com.service.Impl;

import com.google.common.collect.Lists;
import com.service.IFileService;
import com.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file,String path){
        /**
         * 1.生成新的不可重复文件
         *   拿到文件名,分割扩展名,用UUId生成不可重复文件名,重组完整文件名
         * 2.生成文件夹路径
         *   通过path参数,创建文件夹,path如果是多层路径,也可以用mkdirs一次创建
         * 3.将前两步得到的文件路径和文件名,组装成最终文件对象
         *   成功在本地创建了上传来的文件
         *   最终通过ftpUtil方法传到ftp服务器中,删除本地文件
         */

        // 第一步
        String fileName = file.getOriginalFilename();
        /*
        从文件名中得到扩展名,为什么+1，比如a.jpg
        不加1，得到.jpg,加1，得到jpg
        */
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 修改上传的文件名，防止文件名重复，通过UUID，得到不可重复的值，然后生成完整的文件名
        String uploadFileName = UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，上传文件名{}，上传路径{}，新文件名{}",fileName,path,uploadFileName);

        // 第二步
        File fileDir = new File(path);
        if (!fileDir.exists()){
            // 设置此文件夹可写
            fileDir.setWritable(true);
            // 创建文件夹，不存在的路径，自动创建，相当于linux指令 -r
            fileDir.mkdirs();
        }
        // 第三步
        File targetFile = new File(path,uploadFileName);
        try{
            file.transferTo(targetFile);
            // 文件上传到本地成功
            //todo 将targetFile上传到ftp服务器
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            // 然后删除本地文件
            targetFile.delete();
        }catch (IOException e){
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }
}

package com.lx.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UploadService {

    // ip（资源路径）
    @Value("${file.staticPath}")
    private String staticPath;
    // 代理路径
    @Value("${file.staticPatternPath}")
    private String staticPatternPath;
    // 存储路径
    @Value("${file.uploadFolder}")
    private String uploadFolder;
    /*
    * MultipartFile 这个对象是springMVC提供的文件上传接受类
    * 底层封装了 HttpServletRequest request中的request.getInputStream()，与它进行融合。
    * */
    public String upload(MultipartFile multipartFile,String dir) {
        try {
            // 1.获取文件真实名称
            String originalFilename = multipartFile.getOriginalFilename();
            // 2.切割文件名后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 3.生成唯一的文件名，防止同名文件导致覆盖
            String newFilename = UUID.randomUUID().toString() + suffix;
            // 4.跟据日期生成目录
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = simpleDateFormat.format(new Date());
            // 5.拼接完整的上传文件路径
            File file = new File(uploadFolder + dir, datePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 6.指定文件上传之后在服务器上的完整文件名
            File targetFile = new File(file, newFilename);
            // 7.开始上传
            multipartFile.transferTo(targetFile);
            // 8.返回完整的存储路径（存储到数据库）
            String fullPath =staticPath + staticPatternPath.substring(0,staticPatternPath.indexOf("*")) + dir + "/" + datePath + "/" + newFilename;
            return fullPath;
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    // 返回完整资源
    public Map<String,Object> uploadMap(MultipartFile multipartFile, String dir) {

        HashMap<String, Object> map = new HashMap<>();

        try {
            // 1.获取文件真实名称
            String originalFilename = multipartFile.getOriginalFilename();
            // 2.切割文件名后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 3.生成唯一的文件名，防止同名文件导致覆盖
            String newFilename = UUID.randomUUID().toString() + suffix;
            // 4.跟据日期生成目录
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
            String datePath = simpleDateFormat.format(new Date());
            // 5.拼接完整的上传文件路径
            File file = new File(uploadFolder + dir, datePath);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 6.指定文件上传之后在服务器上的完整文件名
            File targetFile = new File(file, newFilename);
            // 7.开始上传
            multipartFile.transferTo(targetFile);
            // 8.返回完整的存储路径（存储到数据库）
            String url =staticPath + staticPatternPath.substring(0,staticPatternPath.indexOf("*")) + dir + "/" + datePath + "/" + newFilename;

            map.put("url",url);
            map.put("size",multipartFile.getSize());
            map.put("suffix",suffix);
            map.put("filename",newFilename);
            map.put("relativePath",dir + "/" + datePath + "/" +newFilename);
            map.put("absolutePath",uploadFolder + dir + "/" + datePath + "/" +newFilename);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error","error");
            return map;
        }
    }
}

package com.lx.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class UploadService {

    /*
    * MultipartFile 这个对象是springMVC提供的文件上传接受类
    * 底层封装了 HttpServletRequest request中的request.getInputStream()，与它进行融合。
    * */
    public String upload(MultipartFile multipartFile,String dir) {
        // 1.指定文件上传的目录
        File file = new File("G:\\JavaSpace\\Java进阶\\fileuploaddownload\\src\\main\\resources\\static\\file\\" + dir);
        try {
            if (!file.exists()) {
                file.mkdirs();
            }
            String originalFilename = multipartFile.getOriginalFilename();
            System.out.println(originalFilename);
            File targetFile = new File(file, originalFilename);
            multipartFile.transferTo(targetFile);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }
}

package com.itzyf.controller;

import com.itzyf.bean.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author 依风听雨
 * @version 创建时间：2017/10/18 11:00
 */
@Controller
public class IndexController {

    private Logger logger = LoggerFactory.getLogger(IndexController.class.getName());

    @RequestMapping("")
    public String goIndex() {
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {

        Result<String> result = new Result<>();
        if (file.isEmpty()) {
            result.setMsg("上传的文件为空");
            result.setCode(102);
            return result;
        }
        logger.info("上传文件:" + file.getOriginalFilename());
        //临时上传的目录
        String home = "/home/zou/下载/";

        File uploadPath = new File(home);
        //如果目录不存在
        if (!uploadPath.exists()) {
            //创建目录
            uploadPath.mkdirs();
        }

        String suffix = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            String path = saveBit(file.getInputStream(), home, suffix);
            result.setMsg("上传成功");
            result.setResult(path);
            result.setCode(0);
        } catch (IOException e) {
            e.printStackTrace();
            result.setMsg("上传失败");
            result.setCode(102);
        }
        return result;
    }

    private String saveBit(InputStream inStream, String path, String suffix) throws IOException {

        String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "" + (
                new Random().nextInt(89) + 10);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存

        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = outStream.toByteArray();
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File(path + name + suffix);
        //创建输出流
        FileOutputStream fileOutStream = new FileOutputStream(imageFile);
        //写入数据
        fileOutStream.write(data);
        return name + suffix;
    }

}

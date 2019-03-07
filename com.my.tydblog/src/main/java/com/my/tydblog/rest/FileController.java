package com.my.tydblog.rest;

import com.my.tydblog.response.RestResponseVO;
import com.my.tydblog.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller("file")
@RequestMapping(value = "/file")
public class FileController {
    @Autowired
    private FileService fileService;
    Logger logger = LoggerFactory.getLogger(this.getClass());


    /**
     * 　MD上传图片
     *
     * @param multipartFile
     * @return success  message url
     */
    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Map uploadImageByMD(@RequestParam(value = "file", required = false) MultipartFile multipartFile) {
        logger.info("上传图片接口");
        Map<String, Object> map = new HashMap<>();
        String guid = new Date().getTime() + "";
        RestResponseVO restResponseVO = fileService.uploadImageByMD(multipartFile, guid,"ming");

        if (restResponseVO.isSuccess()) {
            map.put("success", 1);
            map.put("message", "上传成功");
            map.put("url", restResponseVO.getData());
        } else {
            map.put("success", 0);
            map.put("message", restResponseVO.getMsg());
        }
        logger.info(map.toString());
        return map;
    }

    @RequestMapping("/get")
    public RestResponseVO<byte[]> get(String path) {

        return fileService.get(path);
    }
}

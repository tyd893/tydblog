package com.my.tydblog.service.impl;


import com.my.tydblog.response.RestResponseVO;
import com.my.tydblog.service.FileService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author m969130721@163.com
 * @date 19-1-17 下午1:31
 */
@Service
public class FileServiceImpl implements FileService {

    @Value("${file.server.http.prefix}")
    private String fileServerHttpPrefix;

    @Value("${file.server.dir}")
    private String fileServerDir;

    @Value("${file.server.image.dir}")
    private String fileServerImageDir;

    @Value("${file.server.testcase.dir}")
    private String fileServerTestcaseDir;

    @Value("${file.server.type.image}")
    private String fileServerTypeImage;

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public RestResponseVO uploadImageByMD(MultipartFile multipartFile, String guid, String username) {
//        if (!StringUtils.isNoneBlank(username, guid) || multipartFile == null || multipartFile.isEmpty()) {
////            return RestResponseVO.createByErrorEnum(RestResponseEnum.INVALID_REQUEST);
//            return null;
//        }
        String originalFilename = multipartFile.getOriginalFilename();
        try {
            String type = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            List<String> typeArray = Arrays.asList(fileServerTypeImage.split(","));
            if (!typeArray.contains(type)) {
                logger.info("不支持此图片文件格式,{}", type);
               // return RestResponseVO.createByErrorEnum(RestResponseEnum.FILE_TYPE_ERROR);
                return null;
            }
            String newUri = username + "/blog/" + guid + "." + type;
            String savePath = fileServerImageDir + "/" + newUri;
            String url = fileServerHttpPrefix + "image/" + newUri;

            File saveFile = new File(savePath);
            File parentFile = saveFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
                if (!parentFile.canWrite()) {
                    logger.info("文件{},没有操作权限", savePath);
//                    return RestResponseVO.createByErrorEnum(RestResponseEnum.FILE_PERMISSION_ERROR);
                    return null;
                }
            }

            FileUtils.copyInputStreamToFile(multipartFile.getInputStream(), saveFile);

            return RestResponseVO.createBySuccess(url);
        } catch (Exception e) {
            logger.info("文件IO异常", e.getMessage());
            return RestResponseVO.createByErrorMessage(e.getMessage());
        }
    }

    @Override
    public RestResponseVO<byte[]> get(String path) {

        String absolutePath = fileServerDir + "/" + path;
        File file = new File(absolutePath);
        try {
            byte[] fileByteArray = FileUtils.readFileToByteArray(file);
            return RestResponseVO.createBySuccess(fileByteArray);
        } catch (IOException e) {
            logger.info("获取文件异常,{}", e.getMessage());
            return RestResponseVO.createByErrorMessage(e.getMessage());
        }
    }
}

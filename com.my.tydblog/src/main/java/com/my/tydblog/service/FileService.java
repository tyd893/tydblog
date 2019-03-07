package com.my.tydblog.service;

import com.my.tydblog.response.RestResponseVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author m969130721@163.com
 * @date 19-1-17 下午1:30
 */
public interface FileService {

    RestResponseVO uploadImageByMD(MultipartFile multipartFile, String guid, String username);


    RestResponseVO<byte[]> get(String path);
}

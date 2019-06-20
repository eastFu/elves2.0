package cn.gyyx.elves.console.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

public interface FtpService {

    boolean uploadFile( MultipartFile file,Map<String,Object> map)throws Exception;

}

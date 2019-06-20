package cn.gyyx.elves.console.service.impl;

import cn.gyyx.elves.console.config.FtpConfig;
import cn.gyyx.elves.console.dao.AppDao;
import cn.gyyx.elves.console.domain.User;
import cn.gyyx.elves.console.exception.AppException;
import cn.gyyx.elves.console.service.FtpService;
import cn.gyyx.elves.console.service.ThreadInter;
import cn.gyyx.elves.console.utils.FtpUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class FtpServiceImpl implements FtpService {


    @Autowired
    private FtpConfig ftpConfig;

    @Autowired
    private AppDao appDao;

    @Autowired
    private ThreadInter threadInter;


    @Override
    public boolean uploadFile(MultipartFile file,Map<String,Object> map) throws Exception {
        String fileName = file.getOriginalFilename();
        InputStream input = file.getInputStream();

        String ftpHost = ftpConfig.getFtpHost();
        String ftpUserName = ftpConfig.getFtpUserName();
        String ftpPassword = ftpConfig.getFtpPassword();
        int ftpPort = ftpConfig.getFtpPort();
        String ftpPath = ftpConfig.getFtpPath();

        FTPClient ftpClient =  FtpUtil.getFTPClient( ftpHost,  ftpUserName,  ftpPassword,  ftpPort);

        dbOperat( ftpClient, ftpPath, fileName, map);

        return FtpUtil.uploadFile( ftpClient,  ftpPath,  fileName, input);
    }

    private void dbOperat(FTPClient ftpClient,String ftpPath,String fileName,Map<String,Object> map)throws Exception{
        boolean isExit = FtpUtil.isExit(ftpClient,ftpPath,fileName);

        Map<String,Object> versionMap = new HashMap<>(  );
        Map<String,Object> userMap = threadInter.get();
        User user = (User) userMap.get("currentUser");


        int sp = fileName.lastIndexOf(".");//获取文件路径中.的位置
        String fileNameTrue = fileName.substring(0,sp);//获取文件名

        if(!fileNameTrue.contains( "_" )){
            throw new AppException("文件名 命名不符合标准，未包含'_'，比如：(指令为:backup, 对应文件名 backup_0.0.11.zip)");
        }

        String fileNameTemp[] = fileNameTrue.split( "_" );

        fileNameTrue = fileNameTemp[0];

        String instruct = map.get("instruct") != null || StringUtils.isNotEmpty(map.get("instruct").toString()) ? map.get("instruct").toString() : "";

        if(StringUtils.isEmpty( instruct ) || StringUtils.isEmpty( fileNameTrue )){
            throw new NullPointerException(  );
        }

        if(!fileNameTrue.equals(instruct)){
            throw new AppException("文件名和指令名不一致，比如：(指令为:backup, 对应文件名 backup_0.0.11.zip)");
        }

        String version = fileNameTemp[1];
        versionMap.put( "version",version  );
        versionMap.put( "operator",user.getFounder() );
        versionMap.put( "app_id",map.get("app_id")  );



        if(isExit){
            appDao.updateAppVersion( versionMap );
        }else {
            appDao.insertAppVersion( versionMap );
        }
    }



}

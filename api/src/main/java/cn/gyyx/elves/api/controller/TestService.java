package cn.gyyx.elves.api.controller;

import cn.gyyx.elves.core.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;

@RestController
public class TestService {

    private final static Logger LOG = LoggerFactory.getLogger(TestService.class);

    @Value("${foo}")
    String foo;

    @RequestMapping("/test")
    public String sayHello(HttpServletRequest request){
        String say = DateUtils.currentTimestamp2String("yyyy-MM-dd HH:mm:ss SSS")+": hello "+request.getRemoteAddr()+", i am elves controller rt!"+foo;
//        System.out.println(say);
        return say;
    }

    public static String getStringMd5(File file) throws FileNotFoundException {
        String value = null;
        FileInputStream in = new FileInputStream(file);
        try {
            MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(byteBuffer);
            BigInteger bi = new BigInteger(1, md5.digest());
            value = bi.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    public static void main(String[] args) throws Exception{
        System.out.println(getStringMd5(new File("e://test.gif")));
    }

}

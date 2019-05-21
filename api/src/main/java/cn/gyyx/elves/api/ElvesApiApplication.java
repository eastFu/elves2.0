package cn.gyyx.elves.api;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootApplication
@ServletComponentScan
public class ElvesApiApplication {

    private static final Logger LOG = Logger.getLogger(ElvesApiApplication.class);

    @Bean
    public void loadLogConfig(){
        try {
            // 自定义配置
            InputStream in=new FileInputStream("/log4j.properties");
            PropertyConfigurator.configure(in);
            LOG.info("load LogConfig success!");
        }catch (FileNotFoundException e){
            LOG.info("load LogConfig success!");
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(ElvesApiApplication.class, args);
    }

}

package cn.gyyx.elves;

import cn.gyyx.elves.core.config.ElvesConfig;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@SpringBootApplication
@ServletComponentScan
@MapperScan("cn.gyyx.elves.*.dao")
public class Elves {

    private static final Logger LOG = Logger.getLogger(Elves.class);

//    @Bean
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

    @Bean
    public ElvesConfig loadConfig(){
        return  new ElvesConfig();
    }

    /**
     *
     * @return TomcatServletWebServerFactory
     */
    @Bean
    public TomcatServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers(
                (TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "[]{}|")
        );
        return factory ;
    }

//    @Bean
//    public SpringContextUtil initSpringContextUtil(){
//        return SpringContextUtil;
//    }

    public static void main(String[] args) {
        SpringApplication.run(Elves.class, args);
    }

}

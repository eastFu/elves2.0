package cn.gyyx.elves.cron.job;

import cn.gyyx.elves.cron.service.SchedulerInter;
import cn.gyyx.elves.cron.service.impl.SchedulerInterImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix="spring.datasource")
public class SchedulerConfig {
    private static final Logger logger= Logger.getLogger(SchedulerConfig.class);


    private String url;
    private String username;
    private String password;


    @Autowired
    private MyJobFactory myJobFactory;



    @Bean("schedulerFactoryBean")
    public SchedulerFactoryBean schedulerFactoryBean() throws IOException {
        //获取配置属性


        Resource resource = new ClassPathResource("quartz.properties");

        Properties pro = null;

        if(resource.exists()){
            // 存在配置文件  quartz.properties
            PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
            propertiesFactoryBean.setLocation(resource);
            propertiesFactoryBean.afterPropertiesSet();
            pro = propertiesFactoryBean.getObject();
        }else{

            // 不存在配置文件 quartz.properties
            pro = new Properties();
        }

        defaultProperties(pro);

        //创建SchedulerFactoryBean
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        factory.setOverwriteExistingJobs(true);
        factory.setAutoStartup(true);
        factory.setJobFactory(myJobFactory);
        factory.setQuartzProperties(pro);

        return factory;
    }



    @Bean
    public SchedulerInter schedulerInterImpl()throws IOException{
        return new SchedulerInterImpl();
    }


    private  void defaultProperties(Properties pro){
        isNullOverride(pro,"org.quartz.scheduler.instanceName","DefaultQuartzScheduler");
        // 如果使用集群，instanceId必须唯一，设置成AUTO
        isNullOverride(pro,"org.quartz.scheduler.instanceId","AUTO");
        isNullOverride(pro,"org.quartz.scheduler.rmi.export","false");
        isNullOverride(pro,"org.quartz.scheduler.rmi.proxy","false");
        isNullOverride(pro,"org.quartz.scheduler.wrapJobExecutionInUserTransaction","false");
        isNullOverride(pro,"org.quartz.threadPool.class","org.quartz.simpl.SimpleThreadPool");
        isNullOverride(pro,"org.quartz.threadPool.threadCount","10");
        isNullOverride(pro,"org.quartz.threadPool.threadPriority","5");
        isNullOverride(pro,"org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread","true");
        isNullOverride(pro,"org.quartz.jobStore.misfireThreshold","60000");


        //Configure JobStore
        //存储方式使用JobStoreTX，也就是数据库
        isNullOverride(pro,"org.quartz.jobStore.class","org.quartz.impl.jdbcjobstore.JobStoreTX");
        isNullOverride(pro,"org.quartz.jobStore.driverDelegateClass","org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        isNullOverride(pro,"org.quartz.jobStore.useProperties","false");
        isNullOverride(pro,"org.quartz.jobStore.tablePrefix","QRTZ_");
        isNullOverride(pro,"org.quartz.jobStore.dataSource","qzDS");
        isNullOverride(pro,"org.quartz.jobStore.isClustered","false");

        isNullOverride(pro,"org.quartz.dataSource.qzDS.validationQuery","select 0 from dual");
        isNullOverride(pro,"org.quartz.dataSource.qzDS.driver","com.mysql.jdbc.Driver");

        isNullOverride(pro,"org.quartz.dataSource.qzDS.URL",this.url);
        isNullOverride(pro,"org.quartz.dataSource.qzDS.user",this.username);
        isNullOverride(pro,"org.quartz.dataSource.qzDS.password",this.password);
    }

    private static void isNullOverride(Properties pro,String key,String value){
        if(pro.get( key ) == null) {
            pro.setProperty( key,value );
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
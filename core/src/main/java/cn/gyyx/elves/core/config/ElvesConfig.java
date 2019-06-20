package cn.gyyx.elves.core.config;

/**
 * @ClassName: ElvesConfig
 * @Description: *.property 配置文件加载类
 * @author East.F
 * @date 2016年5月5日 上午11:51:27
 */
/*@Component
@ConfigurationProperties(prefix = "elves", ignoreUnknownFields = true)*/
public class ElvesConfig {

    /**
     * thrift connect timeout : 10000ms = 10s
     */
    private int schedulerConnectTimeout = 10000;

    /**
     *  thrift read timeout : 90s = 90000ms
     */
    private int schedulerSocketTimeout = 90;

    private int schedulerPort = 10101;

    private int clientPort = 11101;

    private boolean supervisorEnable = false;

    private boolean hertbeatEnable = false;

    private int heartbeatPort = 11102;

    public int getSchedulerConnectTimeout() {
        return schedulerConnectTimeout;
    }

    public void setSchedulerConnectTimeout(int schedulerConnectTimeout) {
        this.schedulerConnectTimeout = schedulerConnectTimeout;
    }

    public int getSchedulerSocketTimeout() {
        return schedulerSocketTimeout;
    }

    public void setSchedulerSocketTimeout(int schedulerSocketTimeout) {
        this.schedulerSocketTimeout = schedulerSocketTimeout;
    }

    public int getSchedulerPort() {
        return schedulerPort;
    }

    public void setSchedulerPort(int schedulerPort) {
        this.schedulerPort = schedulerPort;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }

    public boolean isSupervisorEnable() {
        return supervisorEnable;
    }

    public void setSupervisorEnable(boolean supervisorEnable) {
        this.supervisorEnable = supervisorEnable;
    }

    public boolean isHertbeatEnable() {
        return hertbeatEnable;
    }

    public void setHertbeatEnable(boolean hertbeatEnable) {
        this.hertbeatEnable = hertbeatEnable;
    }

    public int getHeartbeatPort() {
        return heartbeatPort;
    }

    public void setHeartbeatPort(int heartbeatPort) {
        this.heartbeatPort = heartbeatPort;
    }
}

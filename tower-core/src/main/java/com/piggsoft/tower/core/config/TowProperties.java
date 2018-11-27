package com.piggsoft.tower.core.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.tower")
public class TowProperties {
    /**
     * Broker唯一标识
     */
    private String id;

    /**
     * SSL端口号, 默认8883端口
     */
    private int sslPort = 8885;

    /**
     * WebSocket SSL端口号, 默认9993端口
     */
    private int websocketSslPort = 9995;

    /**
     * WebSocket Path值, 默认值 /mqtt
     */
    private String websocketPath = "/mqtt";

    /**
     * SSL密钥文件密码
     */
    private String sslPassword;

    /**
     * 心跳时间(秒), 默认60秒, 该值可被客户端连接时相应配置覆盖
     */
    private int keepAlive = 60;

    /**
     * 是否开启Epoll模式, 默认关闭
     */
    private boolean useEpoll = false;

    /**
     * Sokcet参数, 存放已完成三次握手请求的队列最大长度, 默认511长度
     */
    private int soBacklog = 511;

    /**
     * Socket参数, 是否开启心跳保活机制, 默认开启
     */
    private boolean soKeepAlive = true;

    /**
     * 集群配置, 是否基于组播发现, 默认开启
     */
    private boolean enableMulticastGroup = true;

    /**
     * 集群配置, 基于组播发现
     */
    private String multicastGroup;

    /**
     * 集群配置, 当组播模式禁用时, 使用静态IP开启配置集群
     */
    private String staticIpAddresses;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSslPort() {
        return sslPort;
    }

    public void setSslPort(int sslPort) {
        this.sslPort = sslPort;
    }

    public int getWebsocketSslPort() {
        return websocketSslPort;
    }

    public void setWebsocketSslPort(int websocketSslPort) {
        this.websocketSslPort = websocketSslPort;
    }

    public String getWebsocketPath() {
        return websocketPath;
    }

    public void setWebsocketPath(String websocketPath) {
        this.websocketPath = websocketPath;
    }

    public String getSslPassword() {
        return sslPassword;
    }

    public void setSslPassword(String sslPassword) {
        this.sslPassword = sslPassword;
    }

    public int getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(int keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean isUseEpoll() {
        return useEpoll;
    }

    public void setUseEpoll(boolean useEpoll) {
        this.useEpoll = useEpoll;
    }

    public int getSoBacklog() {
        return soBacklog;
    }

    public void setSoBacklog(int soBacklog) {
        this.soBacklog = soBacklog;
    }

    public boolean isSoKeepAlive() {
        return soKeepAlive;
    }

    public void setSoKeepAlive(boolean soKeepAlive) {
        this.soKeepAlive = soKeepAlive;
    }

    public boolean isEnableMulticastGroup() {
        return enableMulticastGroup;
    }

    public void setEnableMulticastGroup(boolean enableMulticastGroup) {
        this.enableMulticastGroup = enableMulticastGroup;
    }

    public String getMulticastGroup() {
        return multicastGroup;
    }

    public void setMulticastGroup(String multicastGroup) {
        this.multicastGroup = multicastGroup;
    }

    public String getStaticIpAddresses() {
        return staticIpAddresses;
    }

    public void setStaticIpAddresses(String staticIpAddresses) {
        this.staticIpAddresses = staticIpAddresses;
    }
}

package com.syj.demo.thread.balking;

public final class Router {

    private final String ip;
    private final Integer port;
    private final String iface;

    public Router(String ip, Integer port, String iface) {
        this.ip = ip;
        this.port = port;
        this.iface = iface;
    }

    // 重写euqals方法
    public boolean equals(Object obj) {
        if (obj instanceof Router) {
            Router r = (Router) obj;
            return iface.equals(r.iface) &&
                    ip.equals(r.ip) &&
                    port.equals(r.port);
        }
        return false;
    }

    public String getIp() {
        return ip;
    }

    public Integer getPort() {
        return port;
    }

    public String getIface() {
        return iface;
    }
}

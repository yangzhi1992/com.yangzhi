package com.utils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangz
 * @date 20200902
 */
public class HostConvertIpUtils {

    /**
     * 通过域名解析出IP
     *
     * @param hostname 域名
     * @return IP列表
     * @throws Exception 异常
     */
    private List<String> getIpByHost(String hostname) throws Exception {
        List<String> ips = new ArrayList();

        InetAddress[] addrs = InetAddress.getAllByName(hostname);
        for (int i = 0; i < addrs.length; i++) {
            ips.add(addrs[i].getHostAddress());
        }

        return ips;
    }
}

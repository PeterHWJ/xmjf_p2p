package com.hwj.xm.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtils {

    public static ThreadLocal<String> ipHolder=new ThreadLocal<String>();

    public static String getRemortIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(ip != null && ip.length() > 0){
            String[] ipArray = ip.split(",");
            if (ipArray != null && ipArray.length > 1) {
                return ipArray[0];
            }
            return ip;
        }

        return "";
    }

    public static String get() {
        return ipHolder.get();
    }

    public static void clear(){
        ipHolder.remove();
    }

    public static void set(HttpServletRequest request) {
        String ip=getRemortIP(request);
        ipHolder.set(ip);
    }

}
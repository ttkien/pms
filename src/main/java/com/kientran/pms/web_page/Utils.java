package com.kientran.pms.web_page;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Utils {
    public  static String getHost() {
        HttpServletRequest request = getHttpServletRequest();
        return request.getHeader("host");
    }

    public  static HttpServletRequest getHttpServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        return attributes.getRequest();

    }

    public static HttpSession getSession() {
        return getHttpServletRequest().getSession();
    }
}

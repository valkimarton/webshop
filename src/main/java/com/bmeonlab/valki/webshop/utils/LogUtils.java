package com.bmeonlab.valki.webshop.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LogUtils {

    public String generateLogMsg(HttpServletRequest request) {
        String template = "%s%s   %s    %s" ;
        return String.format(template,request.getRemoteAddr(), request.getRemotePort(), request.getMethod(), request.getRequestURI());
    }
}

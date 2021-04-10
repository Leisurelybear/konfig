/**
 * FileName: RequestHolder
 * Author:   jason
 * Date:     2021/3/17 14:03
 * Description:
 */
package org.zhangxujie.konfig.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestHolder {
    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static String getHeaderAuthorization() {

        return getHeader("Authorization");
    }

    public static String getHeader(String key) {
        return getHttpServletRequest().getHeader(key);
    }
}

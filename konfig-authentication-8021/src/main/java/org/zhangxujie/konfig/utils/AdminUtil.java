/**
 * FileName: AdminUtil
 * Author:   jason
 * Date:     2021/4/5 19:30
 * Description:
 */
package org.zhangxujie.konfig.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
//@Component
public class AdminUtil {

    @Resource
    private static JwtTokenUtil jwtTokenUtil;

    public static boolean checkUser(String authToken, String username) {
        if (authToken != null && username != null) {
            log.info("checking username:{}", username);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtTokenUtil.validateTokenByUsername(authToken, username)) {
                    //没有过期，而且用户名等于
                    return true;
                }
            }
        }
        return false;
    }

}

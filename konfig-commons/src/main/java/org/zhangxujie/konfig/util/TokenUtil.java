/**
 * FileName: JwtTokenUtil
 * Author:   jason
 * Date:     2021/3/10 16:53
 * Description:
 */
package org.zhangxujie.konfig.util;

import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//JwtToken生成的工具类
@Slf4j
public class TokenUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static String secret = "lite-config-secret";
    private static long expiration = 604800;

    /**
     * 根据负责生成JWT的token
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT中的负载
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token中获取登录用户名
     */
    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否还有效
     *
     * @param token    客户端传入的token
     */
    public static boolean validateToken(String token) {
        String usernameFromToken = getUsernameFromToken(token);
        return !isTokenExpired(token) && hasUser(token, usernameFromToken);
    }

    /**
     * @Author: Jason
     * @Description: 通过8021的接口查询该用户是否在数据库，且有效
     * @Date: 2021/4/16 22:54
     * @Param username: 用户名
     **/
    private static boolean hasUser(String token, String username) {
        String res = HttpRequest
                .get("http://localhost:8021/admin/info")
                .header("Authorization", "Bearer " + token)
                .timeout(20000)
                .execute().body();

        JSONObject jsonObj = XML.toJSONObject(res);
        if (jsonObj != null){
            res = jsonObj.getByPath("CommonResult.data.username").toString();
        }else {
            res = "";
            return false;
        }
        Console.log(res);

        return username.equals(res);
    }

    public boolean validateTokenByUsername(String token, String username) {
        String usernameFromToken = getUsernameFromToken(token);
        return username.equals(usernameFromToken) && !isTokenExpired(token);
    }

    /**
     * 判断token是否已经失效
     */
    private static boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    private static Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        if (claims == null){
            return new Date(System.currentTimeMillis() - 100);
        }
        return claims.getExpiration();
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

}

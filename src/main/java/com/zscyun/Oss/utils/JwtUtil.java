package com.zscyun.Oss.utils;

import com.zscyun.Oss.constant.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;

/**
 * token工具
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/16
 */
public class JwtUtil {

  /**
   * 创建token
   *
   * @param openId 用户openId
   * @return token
   */
  public static String createToken(String openId) {
    HashMap<String, Object> mapClaim = new HashMap<>();
    mapClaim.put("openId", openId);
    JwtBuilder jwtBuilder = Jwts.builder()
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, Constants.JWT_TOKEN_KEY)
            .setExpiration(new Date(Constants.SET_EXPIRE_TIME))
            .addClaims(mapClaim);
    String token = jwtBuilder.compact();
    return token;

  }

  /**
   * 解析token
   *
   * @param token
   * @return openId
   */
  public static String parseToken(String token) {
    Claims claims = Jwts.parser()
            .setSigningKey(Constants.JWT_TOKEN_KEY)
            .parseClaimsJws(token)
            .getBody();
    return (String) claims.get("openId");
  }
}

package com.zscyun.Oss.interceptor;

import com.zscyun.Oss.exception.GlobalExceptionHandler;
import com.zscyun.Oss.mapper.UserMapper;
import com.zscyun.Oss.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户拦截
 *
 * @author 蛋炒饭不加蛋
 */
@Component
public class UserInterceptor implements HandlerInterceptor {
  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
  @Autowired
  private UserMapper userMapper;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//    需要token
    String authorizationJwt = request.getHeader("Authorization");
    if (StringUtils.isEmpty(authorizationJwt)) {
      return false;
    }
//    校验JWT
//    String token = authorizationJwt.substring(6);
//    String openid = JwtUtil.parseToken(token);
//    User user = userMapper.selectUserByOpenId(openid);
//    if (StringUtils.isNull(user)) {
//
//      return false;
//    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

}

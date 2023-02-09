package com.zscyun.Oss.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 拦截器配置
 *
 * @author 蛋炒饭不加蛋
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
  @Autowired
  private UserInterceptor userInterceptor;


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    String[] addPathPatternsByToken = {
            "/user/updateUserInfo",
    };
    registry.addInterceptor(userInterceptor).addPathPatterns(addPathPatternsByToken);
  }
}

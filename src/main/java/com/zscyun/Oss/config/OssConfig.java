package com.zscyun.Oss.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 阿里Oss服务配置
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/16
 */
@Configuration
public class OssConfig {
  @Value("${aliyun.oss.endpoint}")
  private String aliyunOssEndpoint;
  @Value("${aliyun.oss.accessKeyId}")
  private String aliyunOssAccessKeyId;
  @Value("${aliyun.oss.accessKeySecret}")
  private String aliyunOssAccessKeySecret;

  @Bean
  public OSS ossClient() {
    return new OSSClientBuilder().build(aliyunOssEndpoint, aliyunOssAccessKeyId, aliyunOssAccessKeySecret);
  }

}
package com.zscyun.Oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.zscyun.Oss.config.OssConfig;
import com.zscyun.Oss.entity.User;
import com.zscyun.Oss.exception.ServiceException;
import com.zscyun.Oss.mapper.UserMapper;
import com.zscyun.Oss.service.UserService;
import com.zscyun.Oss.utils.StringUtils;
import com.zscyun.Oss.utils.UuidSnowflake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户 业务层处理
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/1
 */
@Service
public class UserServiceImpl implements UserService {
  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private OssConfig ossConfig;

  @Value("${aliyun.oss.endpoint}")
  private String aliyunOssEndpoint;
  @Value("${aliyun.oss.accessKeyId}")
  private String aliyunOssAccessKeyId;
  @Value("${aliyun.oss.accessKeySecret}")
  private String aliyunOssAccessKeySecret;

  @Value("${aliyun.oss.policy.expire}")
  private int aliyunOssPolicyExpire;

  @Value("${aliyun.oss.maxSize}")
  private int aliyunOssMaxSize;

  @Value("${aliyun.oss.bucketName}")
  private String aliyunOssBucketName;

  @Value("${aliyun.oss.host}")
  private String aliyunOssHost;

  /**
   * 新增用户
   *
   * @param user 用户信息
   * @return row
   */
  @Override
  public int insertUser(User user) {
    User userInfo = userMapper.selectUserByOpenId(user.getOpenId());
    if (!StringUtils.isNull(userInfo)) {
      throw new ServiceException("您已授权", 500);
    }
    UuidSnowflake.SnowflakeDistributeId idWorker = new UuidSnowflake.SnowflakeDistributeId(0, 0);
    long userId = idWorker.nextId();
    user.setUserId(userId);

    String catalogueName = userId + "/";
    OSS ossClient = ossConfig.ossClient();
    try {
      String content = "";
      PutObjectRequest putObjectRequest = new PutObjectRequest(aliyunOssBucketName, catalogueName, new ByteArrayInputStream(content.getBytes()));
      ossClient.putObject(putObjectRequest);
    } catch (OSSException oe) {
      log.error("目录创建失败1：" + oe.getErrorMessage());
      throw new ServiceException("目录创建失败", 500);
    } catch (ClientException ce) {
      log.error("目录创建失败1：" + ce.getErrorMessage());
      throw new ServiceException("目录创建失败", 500);
    }
    int row = userMapper.insertUser(user);
    return row;
  }

  /**
   * 查询用户
   *
   * @param openId 用户的openId
   * @return user 用户对象信息
   */
  @Override
  public User selectUserByOpenId(String openId) {
    User user = userMapper.selectUserByOpenId(openId);
    return user;
  }

  /**
   * 修改用户信息
   *
   * @param user 用户信息
   * @return row
   */
  @Override
  public int updateUser(User user) {
    int row = userMapper.updateUser(user);
    return row;
  }

  /**
   * 查询用户
   *
   * @return user
   */
  @Override
  public User selectUserByUserAndPsw(String username, String password) {
    Map<String, String> map = new HashMap<>();
    map.put("username", username);
    map.put("password", password);
    User user = userMapper.selectUserByUserAndPsw(map);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户不存在", 401);
    }
    return user;
  }
}

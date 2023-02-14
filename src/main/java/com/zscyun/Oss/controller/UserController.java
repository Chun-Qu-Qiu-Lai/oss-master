package com.zscyun.Oss.controller;


import com.zscyun.Oss.constant.Constants;
import com.zscyun.Oss.constant.HttpStatus;
import com.zscyun.Oss.entity.LoginUser;
import com.zscyun.Oss.entity.Result;
import com.zscyun.Oss.entity.ResultStatus;
import com.zscyun.Oss.entity.User;
import com.zscyun.Oss.exception.ServiceException;
import com.zscyun.Oss.mapper.UserMapper;
import com.zscyun.Oss.service.impl.UserServiceImpl;
import com.zscyun.Oss.utils.CodeUtil;
import com.zscyun.Oss.utils.JwtUtil;
import com.zscyun.Oss.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/16
 */
@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserServiceImpl userService;
  @Autowired
  private UserMapper userMapper;

  /**
   * 授权，注册用户，获取头像，名称
   *
   * @param loginUser 登录表单
   * @return Result
   */
  @PostMapping("/applet/authorization")
  private Result authorization(@RequestBody LoginUser loginUser) {
    if (StringUtils.isEmpty(loginUser.getCode())) {
      throw new ServiceException("code码为空", HttpStatus.BAD_REQUEST);
    }
    if (Constants.DEFAULT_HEAD.equals(loginUser.getAvatarUrl())) {
      throw new ServiceException("头像为空", HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isEmpty(loginUser.getNickName())) {
      throw new ServiceException("昵称禁止为空", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getNickName().length() > Constants.APPLET_USERNAME_LENGTH) {
      throw new ServiceException("昵称最长" + Constants.APPLET_USERNAME_LENGTH + "位", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getNickName().length() != loginUser.getNickName().replace(" ", "").length()) {
      throw new ServiceException("昵称不合法", HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isEmpty(loginUser.getUsername())) {
      throw new ServiceException("用户名禁止为空", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getUsername().length() > Constants.USERNAME_LENGTH) {
      throw new ServiceException("用户名最长" + Constants.USERNAME_LENGTH + "位", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getUsername().length() != loginUser.getUsername().replace(" ", "").length()) {
      throw new ServiceException("用户名不合法", HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isEmpty(loginUser.getPassword())) {
      throw new ServiceException("密码禁止为空", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getPassword().length() > Constants.PASSWORD_LENGTH) {
      throw new ServiceException("密码最长" + Constants.PASSWORD_LENGTH + "位", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getPassword().length() != loginUser.getPassword().replace(" ", "").length()) {
      throw new ServiceException("密码不合法", HttpStatus.BAD_REQUEST);
    }

    String openId = CodeUtil.getRes(loginUser.getCode());
    User user = new User();
    user.setOpenId(openId);
    user.setNickName(loginUser.getNickName());
    user.setAvatarUrl(loginUser.getAvatarUrl());
    user.setUsername(loginUser.getUsername());
    user.setPassword(loginUser.getPassword());
    if (userService.insertUser(user) != 1) {
      throw new ServiceException("授权失败，请稍候重试", HttpStatus.ERROR);
    }
    {
      return Result.success();
    }
  }

  /**
   * 登录
   *
   * @param code 微信code码
   * @return Result
   */
  @PostMapping("/applet/login")
  private Result login(@RequestParam("code") String code) {
    if (StringUtils.isEmpty(code)) {
      throw new ServiceException("code参数为空", HttpStatus.BAD_REQUEST);
    }
    String openId = CodeUtil.getRes(code);
    String token = JwtUtil.createToken(openId);
    Map<String, Object> r = new HashMap<>();
    r.put("token", token);
    return Result.success(ResultStatus.HTTP_STATUS_200, r);
  }

  /**
   * 查询用户信息
   *
   * @param authorizationJwt token
   * @return Result
   */
  @GetMapping("/getUserInfo")
  private Result getUserInfo(@RequestHeader("Authorization") String authorizationJwt) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userService.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("未授权", HttpStatus.UNAUTHORIZED);
    }

    Map<String, Object> userInfo = new HashMap<>();
    userInfo.put("nickName", user.getNickName());
    userInfo.put("avatarUrl", user.getAvatarUrl());
    userInfo.put("downloadNumber", user.getDownloadNumber());
    userInfo.put("uploadNumber", user.getUploadNumber());
    Date endTime = new Date(System.currentTimeMillis());
    long s = endTime.getTime() - user.getCreateTime().getTime();
    TimeUnit days = TimeUnit.DAYS;
    userInfo.put("days", days.convert(s, TimeUnit.MILLISECONDS));
    Map<String, Object> r = new HashMap<>();
    r.put("userInfo", userInfo);
    return Result.success(ResultStatus.HTTP_STATUS_200, r);
  }

  /**
   * 修改个人信息
   *
   * @param authorizationJwt token
   * @param loginUser        用户表单
   * @return Result
   */
  @PostMapping("/applet/editUserInfo")
  private Result editUserInfo(@RequestHeader("Authorization") String authorizationJwt,
                              @RequestBody LoginUser loginUser) {
    String token = authorizationJwt.substring(6);
    String openId = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openId);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("未授权", HttpStatus.UNAUTHORIZED);
    }
    if (Constants.DEFAULT_HEAD.equals(loginUser.getAvatarUrl())) {
      throw new ServiceException("头像为空", HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isEmpty(loginUser.getNickName())) {
      throw new ServiceException("昵称禁止为空", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getNickName().length() > Constants.APPLET_USERNAME_LENGTH) {
      throw new ServiceException("昵称最长" + Constants.APPLET_USERNAME_LENGTH + "位", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getNickName().length() != loginUser.getNickName().replace(" ", "").length()) {
      throw new ServiceException("昵称不合法", HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isEmpty(loginUser.getUsername())) {
      throw new ServiceException("用户名禁止为空", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getUsername().length() > Constants.USERNAME_LENGTH) {
      throw new ServiceException("用户名最长" + Constants.USERNAME_LENGTH + "位", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getUsername().length() != loginUser.getUsername().replace(" ", "").length()) {
      throw new ServiceException("用户名不合法", HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isEmpty(loginUser.getPassword())) {
      throw new ServiceException("密码禁止为空", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getPassword().length() > Constants.PASSWORD_LENGTH) {
      throw new ServiceException("密码最长" + Constants.PASSWORD_LENGTH + "位", HttpStatus.BAD_REQUEST);
    }
    if (loginUser.getPassword().length() != loginUser.getPassword().replace(" ", "").length()) {
      throw new ServiceException("密码不合法", HttpStatus.BAD_REQUEST);
    }

    User userInfo = new User();
    userInfo.setAvatarUrl(loginUser.getAvatarUrl());
    userInfo.setNickName(loginUser.getNickName());
    user.setUsername(loginUser.getUsername());
    user.setPassword(loginUser.getPassword());
    userInfo.setOpenId(openId);
    if (userService.updateUser(userInfo) > 0) {
      return Result.success();
    } else {
      return Result.success();
    }
  }

  /**
   * 用户登录
   *
   * @param username
   * @param password
   * @return
   */

  @PostMapping("/admin/login")
  public Result adminLogin(@RequestParam("username") String username,
                           @RequestParam("password") String password) {
    if (StringUtils.isEmpty(username)) {
      throw new ServiceException("用户名为空", HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isEmpty(password)) {
      throw new ServiceException("密码为空", HttpStatus.BAD_REQUEST);
    }
    User user = userService.selectUserByUserAndPsw(username, password);
    String openId = user.getOpenId();
    String token = JwtUtil.createToken(openId);
    return Result.success(ResultStatus.HTTP_STATUS_200, token);
  }

  /**
   * 修改用户密码
   *
   * @param authorizationJwt token
   * @param password         密码
   * @param checkPassword    二次密码
   * @return Result
   */
  @PostMapping("/admin/editPsw")
  public Result editPsw(@RequestHeader("Authorization") String authorizationJwt,
                        @RequestParam("password") String password,
                        @RequestParam("checkPassword") String checkPassword) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userService.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("未登录", HttpStatus.UNAUTHORIZED);
    }

    if (StringUtils.isEmpty(password)) {
      throw new ServiceException("密码不为空", HttpStatus.BAD_REQUEST);
    }
    if (password.length() > Constants.PASSWORD_LENGTH) {
      throw new ServiceException("密码长度最大" + Constants.PASSWORD_LENGTH + "位", HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isEmpty(checkPassword)) {
      throw new ServiceException("第二次输入密码不合法", HttpStatus.BAD_REQUEST);
    }
    if (!password.equals(checkPassword)) {
      throw new ServiceException("两次输入密码不相同", HttpStatus.BAD_REQUEST);
    }

    User newUserInfo = new User();
    newUserInfo.setUserId(user.getUserId());
    newUserInfo.setPassword(password);

    int row = userService.updateUser(newUserInfo);
    if (row > 0) {
      return Result.success(ResultStatus.HTTP_STATUS_200);
    } else {
      return Result.fail();
    }

  }
}

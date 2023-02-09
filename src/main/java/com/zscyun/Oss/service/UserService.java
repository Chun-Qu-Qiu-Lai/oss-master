package com.zscyun.Oss.service;

import com.zscyun.Oss.entity.User;

/**
 * @author 铔嬬倰楗笉鍔犺泲
 * @date 2022/12/1
 */
public interface UserService {

  /**
   * 新增用户
   *
   * @param user 用户信息
   * @return row
   */
  public int insertUser(User user);

  /**
   * 查询用户信息
   *
   * @param openId 用户的openId
   * @return user 用户对象信息
   */
  public User selectUserByOpenId(String openId);

  /**
   * 修改用户信息
   *
   * @param user 用户信息
   * @return row
   */
  public int updateUser(User user);

  /**
   * 查询用户
   *
   * @param username 找好
   * @param password 密码
   * @return user
   */
  public User selectUserByUserAndPsw(String username, String password);

}

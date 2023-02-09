package com.zscyun.Oss.mapper;

import com.zscyun.Oss.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author 铔嬬倰楗笉鍔犺泲
 * @date 2022/11/18
 */
@Mapper
public interface UserMapper {

  /**
   * 新增用户
   *
   * @param user 用户信息
   * @return row
   */
  public int insertUser(User user);

  /**
   * 查询用户
   *
   * @param openId 用户的openId
   * @return user 用户对象信息
   */
  public User selectUserByOpenId(String openId);

  /**
   * 查询用户
   *
   * @param userId 用户的userId
   * @return user 用户对象信息
   */
  public User selectUserByUserId(Long userId);

  /**
   * 修改用户
   *
   * @param user 用户信息
   * @return row
   */
  public int updateUser(User user);

  /**
   * 查询用户
   *
   * @param map username,password
   * @return user
   */
  public User selectUserByUserAndPsw(Map<String, String> map);
}

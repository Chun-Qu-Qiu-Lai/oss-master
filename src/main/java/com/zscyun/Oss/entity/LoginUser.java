package com.zscyun.Oss.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户登录表单
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/1
 */
public class LoginUser implements Serializable {
  private String code;
  private String nickName;
  private String avatarUrl;
  private String username;
  private String password;

  public LoginUser() {
  }

  public LoginUser(String code, String nickName, String avatarUrl, String username, String password) {
    this.code = code;
    this.nickName = nickName;
    this.avatarUrl = avatarUrl;
    this.username = username;
    this.password = password;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getAvatarUrl() {
    return avatarUrl;
  }

  public void setAvatarUrl(String avatarUrl) {
    this.avatarUrl = avatarUrl;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginUser loginUser = (LoginUser) o;
    return Objects.equals(code, loginUser.code) && Objects.equals(nickName, loginUser.nickName) && Objects.equals(avatarUrl, loginUser.avatarUrl) && Objects.equals(username, loginUser.username) && Objects.equals(password, loginUser.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, nickName, avatarUrl, username, password);
  }

  @Override
  public String toString() {
    return "LoginUser{" +
            "code='" + code + '\'' +
            ", nickName='" + nickName + '\'' +
            ", avatarUrl='" + avatarUrl + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
  }
}

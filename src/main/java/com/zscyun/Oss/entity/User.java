package com.zscyun.Oss.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 用户信息
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/11/25
 */
public class User implements Serializable {

  private Long userId;
  private String openId;
  private String nickName;
  private String avatarUrl;
  private String username;
  private String password;
  private Integer uploadNumber;
  private Integer downloadNumber;

  private Date createTime;

  public User() {
  }

  public User(Long userId, String openId, String nickName, String avatarUrl, String username, String password, Integer uploadNumber, Integer downloadNumber, Date createTime) {
    this.userId = userId;
    this.openId = openId;
    this.nickName = nickName;
    this.avatarUrl = avatarUrl;
    this.username = username;
    this.password = password;
    this.uploadNumber = uploadNumber;
    this.downloadNumber = downloadNumber;
    this.createTime = createTime;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
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

  public Integer getUploadNumber() {
    return uploadNumber;
  }

  public void setUploadNumber(Integer uploadNumber) {
    this.uploadNumber = uploadNumber;
  }

  public Integer getDownloadNumber() {
    return downloadNumber;
  }

  public void setDownloadNumber(Integer downloadNumber) {
    this.downloadNumber = downloadNumber;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(userId, user.userId) && Objects.equals(openId, user.openId) && Objects.equals(nickName, user.nickName) && Objects.equals(avatarUrl, user.avatarUrl) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(uploadNumber, user.uploadNumber) && Objects.equals(downloadNumber, user.downloadNumber) && Objects.equals(createTime, user.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, openId, nickName, avatarUrl, username, password, uploadNumber, downloadNumber, createTime);
  }

  @Override
  public String toString() {
    return "User{" +
            "userId=" + userId +
            ", openId='" + openId + '\'' +
            ", nickName='" + nickName + '\'' +
            ", avatarUrl='" + avatarUrl + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", uploadNumber=" + uploadNumber +
            ", downloadNumber=" + downloadNumber +
            ", createTime=" + createTime +
            '}';
  }
}

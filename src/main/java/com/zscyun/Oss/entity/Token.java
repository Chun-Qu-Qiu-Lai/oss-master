package com.zscyun.Oss.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class Token {
  private Integer tokenId;
  private String tokenName;
  private String tokenValue;
  private Long userId;
  private Integer status;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  private Date deleteTime;

  public Token() {
  }

  public Token(Integer tokenId, String tokenName, String tokenValue, Long userId, Integer status, Date createTime, Date deleteTime) {
    this.tokenId = tokenId;
    this.tokenName = tokenName;
    this.tokenValue = tokenValue;
    this.userId = userId;
    this.status = status;
    this.createTime = createTime;
    this.deleteTime = deleteTime;
  }

  public Integer getTokenId() {
    return tokenId;
  }

  public void setTokenId(Integer tokenId) {
    this.tokenId = tokenId;
  }

  public String getTokenName() {
    return tokenName;
  }

  public void setTokenName(String tokenName) {
    this.tokenName = tokenName;
  }

  public String getTokenValue() {
    return tokenValue;
  }

  public void setTokenValue(String tokenValue) {
    this.tokenValue = tokenValue;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getDeleteTime() {
    return deleteTime;
  }

  public void setDeleteTime(Date deleteTime) {
    this.deleteTime = deleteTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Token token = (Token) o;
    return Objects.equals(tokenId, token.tokenId) && Objects.equals(tokenName, token.tokenName) && Objects.equals(tokenValue, token.tokenValue) && Objects.equals(userId, token.userId) && Objects.equals(status, token.status) && Objects.equals(createTime, token.createTime) && Objects.equals(deleteTime, token.deleteTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tokenId, tokenName, tokenValue, userId, status, createTime, deleteTime);
  }

  @Override
  public String toString() {
    return "Token{" +
            "tokenId=" + tokenId +
            ", tokenName='" + tokenName + '\'' +
            ", tokenValue='" + tokenValue + '\'' +
            ", userId=" + userId +
            ", status=" + status +
            ", createTime=" + createTime +
            ", deleteTime=" + deleteTime +
            '}';
  }
}

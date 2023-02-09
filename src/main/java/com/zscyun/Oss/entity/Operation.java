package com.zscyun.Oss.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 操作日志
 *
 * @author 铔嬬倰楗笉鍔犺泲
 * @date 2023/1/28
 */
public class Operation {
  private Integer operationId;
  private Long userId;
  //  0上传；1下载；2删除
  private Integer type;
  private Long fileId;
  private Date createTime;

  public Operation() {
  }

  public Operation(Integer operationId, Long userId, Integer type, Long fileId, Date createTime) {
    this.operationId = operationId;
    this.userId = userId;
    this.type = type;
    this.fileId = fileId;
    this.createTime = createTime;
  }

  public Integer getOperationId() {
    return operationId;
  }

  public void setOperationId(Integer operationId) {
    this.operationId = operationId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Long getFileId() {
    return fileId;
  }

  public void setFileId(Long fileId) {
    this.fileId = fileId;
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
    Operation operation = (Operation) o;
    return Objects.equals(operationId, operation.operationId) && Objects.equals(userId, operation.userId) && Objects.equals(type, operation.type) && Objects.equals(fileId, operation.fileId) && Objects.equals(createTime, operation.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operationId, userId, type, fileId, createTime);
  }

  @Override
  public String toString() {
    return "Operation{" +
            "operationId=" + operationId +
            ", userId=" + userId +
            ", type=" + type +
            ", fileId=" + fileId +
            ", createTime=" + createTime +
            '}';
  }
}

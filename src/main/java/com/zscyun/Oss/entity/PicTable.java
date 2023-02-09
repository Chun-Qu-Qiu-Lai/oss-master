package com.zscyun.Oss.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

/**
 * @author 铔嬬倰楗笉鍔犺泲
 * @date 2023/1/18
 */
public class PicTable {
  private Integer key;
  private Long fileId;
  private Long catalogueId;
  private String filePath;
  private String fileUrl;
  private Integer status;
  private String type;
  private Integer size;
  private Integer width;
  private Integer height;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;

  public PicTable() {
  }

  public PicTable(Integer key, Long fileId, String filePath, String fileUrl, Integer status, String type, Integer size, Integer width, Integer height, Date createTime) {
    this.key = key;
    this.fileId = fileId;
    this.filePath = filePath;
    this.fileUrl = fileUrl;
    this.status = status;
    this.type = type;
    this.size = size;
    this.width = width;
    this.height = height;
    this.createTime = createTime;
  }

  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  public Long getFileId() {
    return fileId;
  }

  public void setFileId(Long fileId) {
    this.fileId = fileId;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Integer getWidth() {
    return width;
  }

  public void setWidth(Integer width) {
    this.width = width;
  }

  public Integer getHeight() {
    return height;
  }

  public void setHeight(Integer height) {
    this.height = height;
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
    PicTable picTable = (PicTable) o;
    return Objects.equals(key, picTable.key) && Objects.equals(fileId, picTable.fileId) && Objects.equals(filePath, picTable.filePath) && Objects.equals(fileUrl, picTable.fileUrl) && Objects.equals(status, picTable.status) && Objects.equals(type, picTable.type) && Objects.equals(size, picTable.size) && Objects.equals(width, picTable.width) && Objects.equals(height, picTable.height) && Objects.equals(createTime, picTable.createTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, fileId, filePath, fileUrl, status, type, size, width, height, createTime);
  }

  @Override
  public String toString() {
    return "PicTable{" +
            "key=" + key +
            ", fileId=" + fileId +
            ", filePath='" + filePath + '\'' +
            ", fileUrl='" + fileUrl + '\'' +
            ", status=" + status +
            ", type='" + type + '\'' +
            ", size=" + size +
            ", width=" + width +
            ", height=" + height +
            ", createTime=" + createTime +
            '}';
  }
}

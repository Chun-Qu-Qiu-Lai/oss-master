package com.zscyun.Oss.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 文件
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/31
 */
public class Picture implements Serializable {
  private Integer id;
  private Long fileId;
  private Long userId;
  private Long catalogueId;
  private String fileName;
  private String filePath;
  private String fileUrl;
  private String type;
  private Integer size;
  private Integer width;
  private Integer height;
  /**
   * 1正常 0已删除
   */
  private Integer status;
  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date createTime;
  private Date deleteTime;

  public Picture() {
  }

  public Picture(Integer id, Long fileId, Long userId, Long catalogueId, String fileName, String filePath, String fileUrl, String type, Integer size, Integer width, Integer height, Integer status, Date createTime, Date deleteTime) {
    this.id = id;
    this.fileId = fileId;
    this.userId = userId;
    this.catalogueId = catalogueId;
    this.fileName = fileName;
    this.filePath = filePath;
    this.fileUrl = fileUrl;
    this.type = type;
    this.size = size;
    this.width = width;
    this.height = height;
    this.status = status;
    this.createTime = createTime;
    this.deleteTime = deleteTime;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Long getFileId() {
    return fileId;
  }

  public void setFileId(Long fileId) {
    this.fileId = fileId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getCatalogueId() {
    return catalogueId;
  }

  public void setCatalogueId(Long catalogueId) {
    this.catalogueId = catalogueId;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
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
    Picture picture = (Picture) o;
    return Objects.equals(id, picture.id) && Objects.equals(fileId, picture.fileId) && Objects.equals(userId, picture.userId) && Objects.equals(catalogueId, picture.catalogueId) && Objects.equals(fileName, picture.fileName) && Objects.equals(filePath, picture.filePath) && Objects.equals(fileUrl, picture.fileUrl) && Objects.equals(type, picture.type) && Objects.equals(size, picture.size) && Objects.equals(width, picture.width) && Objects.equals(height, picture.height) && Objects.equals(status, picture.status) && Objects.equals(createTime, picture.createTime) && Objects.equals(deleteTime, picture.deleteTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fileId, userId, catalogueId, fileName, filePath, fileUrl, type, size, width, height, status, createTime, deleteTime);
  }

  @Override
  public String toString() {
    return "File{" +
            "id=" + id +
            ", fileId=" + fileId +
            ", userId=" + userId +
            ", catalogueId=" + catalogueId +
            ", fileName='" + fileName + '\'' +
            ", filePath='" + filePath + '\'' +
            ", fileUrl='" + fileUrl + '\'' +
            ", type='" + type + '\'' +
            ", size=" + size +
            ", width=" + width +
            ", height=" + height +
            ", status=" + status +
            ", createTime=" + createTime +
            ", deleteTime=" + deleteTime +
            '}';
  }
}

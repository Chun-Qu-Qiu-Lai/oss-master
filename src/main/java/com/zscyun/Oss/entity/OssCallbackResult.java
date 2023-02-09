package com.zscyun.Oss.entity;

import java.util.Objects;

/**
 * 文件回调信息
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/16
 */
public class OssCallbackResult {

  private String fileUrl;
  private String size;
  private String mimeType;
  private String width;
  private String height;

  public OssCallbackResult() {
  }

  public OssCallbackResult(String fileUrl, String size, String mimeType, String width, String height) {
    this.fileUrl = fileUrl;
    this.size = size;
    this.mimeType = mimeType;
    this.width = width;
    this.height = height;
  }

  public String getFileUrl() {
    return fileUrl;
  }

  public void setFileUrl(String fileUrl) {
    this.fileUrl = fileUrl;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getWidth() {
    return width;
  }

  public void setWidth(String width) {
    this.width = width;
  }

  public String getHeight() {
    return height;
  }

  public void setHeight(String height) {
    this.height = height;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OssCallbackResult that = (OssCallbackResult) o;
    return Objects.equals(fileUrl, that.fileUrl) && Objects.equals(size, that.size) && Objects.equals(mimeType, that.mimeType) && Objects.equals(width, that.width) && Objects.equals(height, that.height);
  }

  @Override
  public int hashCode() {
    return Objects.hash(fileUrl, size, mimeType, width, height);
  }

  @Override
  public String toString() {
    return "OssCallbackResult{" +
            "fileUrl='" + fileUrl + '\'' +
            ", size='" + size + '\'' +
            ", mimeType='" + mimeType + '\'' +
            ", width='" + width + '\'' +
            ", height='" + height + '\'' +
            '}';
  }
}

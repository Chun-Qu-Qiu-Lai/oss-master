package com.zscyun.Oss.entity;

import java.util.Objects;

/**
 * 文件上传回调信息
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/16
 */
public class OssCallbackParam {

  private String callbackUrl;
  private String callbackBody;
  private String callbackBodyType;

  public OssCallbackParam() {
  }

  public OssCallbackParam(String callbackUrl, String callbackBody, String callbackBodyType) {
    this.callbackUrl = callbackUrl;
    this.callbackBody = callbackBody;
    this.callbackBodyType = callbackBodyType;
  }

  public String getCallbackUrl() {
    return callbackUrl;
  }

  public void setCallbackUrl(String callbackUrl) {
    this.callbackUrl = callbackUrl;
  }

  public String getCallbackBody() {
    return callbackBody;
  }

  public void setCallbackBody(String callbackBody) {
    this.callbackBody = callbackBody;
  }

  public String getCallbackBodyType() {
    return callbackBodyType;
  }

  public void setCallbackBodyType(String callbackBodyType) {
    this.callbackBodyType = callbackBodyType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OssCallbackParam that = (OssCallbackParam) o;
    return Objects.equals(callbackUrl, that.callbackUrl) && Objects.equals(callbackBody, that.callbackBody) && Objects.equals(callbackBodyType, that.callbackBodyType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(callbackUrl, callbackBody, callbackBodyType);
  }

  @Override
  public String toString() {
    return "OssCallbackParam{" +
            "callbackUrl='" + callbackUrl + '\'' +
            ", callbackBody='" + callbackBody + '\'' +
            ", callbackBodyType='" + callbackBodyType + '\'' +
            '}';
  }
}

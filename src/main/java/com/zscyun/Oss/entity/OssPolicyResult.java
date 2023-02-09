package com.zscyun.Oss.entity;

import java.util.Objects;

/**
 * 文件四川策略信息
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/16
 */
public class OssPolicyResult {

  private String accessKeyId;
  private String policy;
  private String signature;
  private String dir;
  private String host;
  private String callback;

  public OssPolicyResult() {
  }

  public OssPolicyResult(String accessKeyId, String policy, String signature, String dir, String host, String callback) {
    this.accessKeyId = accessKeyId;
    this.policy = policy;
    this.signature = signature;
    this.dir = dir;
    this.host = host;
    this.callback = callback;
  }

  public String getAccessKeyId() {
    return accessKeyId;
  }

  public void setAccessKeyId(String accessKeyId) {
    this.accessKeyId = accessKeyId;
  }

  public String getPolicy() {
    return policy;
  }

  public void setPolicy(String policy) {
    this.policy = policy;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getDir() {
    return dir;
  }

  public void setDir(String dir) {
    this.dir = dir;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getCallback() {
    return callback;
  }

  public void setCallback(String callback) {
    this.callback = callback;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OssPolicyResult that = (OssPolicyResult) o;
    return Objects.equals(accessKeyId, that.accessKeyId) && Objects.equals(policy, that.policy) && Objects.equals(signature, that.signature) && Objects.equals(dir, that.dir) && Objects.equals(host, that.host) && Objects.equals(callback, that.callback);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessKeyId, policy, signature, dir, host, callback);
  }

  @Override
  public String toString() {
    return "OssPolicyResult{" +
            "accessKeyId='" + accessKeyId + '\'' +
            ", policy='" + policy + '\'' +
            ", signature='" + signature + '\'' +
            ", dir='" + dir + '\'' +
            ", host='" + host + '\'' +
            ", callback='" + callback + '\'' +
            '}';
  }
}

package com.zscyun.Oss.entity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 返回结果状态码
 */
public enum ResultStatus {
  HTTP_STATUS_200(200, "success"),
  HTTP_STATUS_400(400, "request error"),
  HTTP_STATUS_401(401, "未授权"),
  HTTP_STATUS_403(403, "no authorities"),
  HTTP_STATUS_409(409, "密码为空"),
  HTTP_STATUS_410(410, "账号为空"),
  HTTP_STATUS_411(411, "查无此人");


  public static final List<ResultStatus> HTTP_STATUS_ALL = Collections.unmodifiableList(
          Arrays.asList(
                  HTTP_STATUS_200,
                  HTTP_STATUS_400,
                  HTTP_STATUS_401,
                  HTTP_STATUS_403,
                  HTTP_STATUS_409,
                  HTTP_STATUS_410,
                  HTTP_STATUS_411
          ));

  /**
   * response code
   */
  private final Integer resultCode;

  /**
   * description.
   */
  private final String resultDescription;

  ResultStatus(Integer resultCode, String resultDescription) {
    this.resultCode = resultCode;
    this.resultDescription = resultDescription;
  }

  public int getResultCode() {
    return resultCode;
  }

  public String getResultDescription() {
    return resultDescription;
  }
}


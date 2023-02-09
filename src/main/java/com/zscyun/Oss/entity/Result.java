package com.zscyun.Oss.entity;


import java.io.Serializable;

/**
 * 返回结果封装
 *
 * @param <T>
 */
public class Result<T> implements Serializable {

  private Integer code;
  private String msg;
  private T data;


  public static <T> Result<T> success() {
    return success(ResultStatus.HTTP_STATUS_200);
  }

  public static <T> Result<T> fail() {
    return success(ResultStatus.HTTP_STATUS_401);
  }

  public static <T> Result<T> success(ResultStatus resultStatus, T data) {
    return new Result<T>(resultStatus.getResultCode(), resultStatus.getResultDescription(), data);
  }

  public static <T> Result<T> success(ResultStatus resultStatus) {
    return new Result<T>(resultStatus.getResultCode(), resultStatus.getResultDescription());
  }

  public static <T> Result<T> success(Integer code, String msg) {
    return new Result<T>(code, msg);
  }

  public static <T> Result<T> success(Integer code, String msg, T data) {
    return new Result<T>(code, msg, data);
  }


  public static <T extends Serializable> Result<T> fail(ResultStatus resultStatus) {
    return new Result<T>(resultStatus.getResultCode(), resultStatus.getResultDescription());
  }

  public static <T extends Serializable> Result<T> fail(Integer code, String msg) {
    return new Result<T>(code, msg);
  }

  public Result() {
  }

  public Result(Integer code, String msg, T data) {
    this.code = code;
    this.msg = msg;
    this.data = data;
  }

  public Result(Integer code, String msg) {
    this.code = code;
    this.msg = msg;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}

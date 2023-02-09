package com.zscyun.Oss.constant;

/**
 * 响应吗
 *
 * @author 蛋炒饭不鸡蛋
 * @date 2022/12/7
 */
public class HttpStatus {
  /**
   * 操作成功
   */
  public static final int SUCCESS = 200;

  /**
   * 资源已被移除
   */
  public static final int MOVED_PERM = 301;

  /**
   * 资源没有被修改
   */
  public static final int NOT_MODIFIED = 304;

  /**
   * 参数列表错误（缺少，格式不匹配）
   */
  public static final int BAD_REQUEST = 400;

  /**
   * 未授权
   */
  public static final int UNAUTHORIZED = 401;

  /**
   * 访问受限，授权过期
   */
  public static final int FORBIDDEN = 403;

  /**
   * 系统内部错误
   */
  public static final int ERROR = 500;
}

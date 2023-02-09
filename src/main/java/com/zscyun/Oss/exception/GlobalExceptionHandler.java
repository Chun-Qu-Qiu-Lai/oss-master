package com.zscyun.Oss.exception;

import com.zscyun.Oss.entity.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/4
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  /**
   * 业务异常
   */
  @ExceptionHandler(ServiceException.class)
  public Result handleServiceException(ServiceException e, HttpServletRequest request) {
    log.error(e.getMessage(), e);
    Integer code = e.getCode();
    return Result.fail(code, e.getMessage());
  }

  /**
   * 拦截未知的运行时异常
   */
  @ExceptionHandler(RuntimeException.class)
  public Result handleException(RuntimeException e, HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    log.error("请求地址'{}',发生未知异常.", requestURI, e);
    return Result.fail(500, e.getMessage());
  }

  /**
   * 系统异常
   */
  @ExceptionHandler(Exception.class)
  public Result handleException(Exception e, HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    log.error("请求地址'{}',发生系统异常.", requestURI, e);
    return Result.fail(500, e.getMessage());
  }
}

package com.zscyun.Oss.constant;

/**
 * 常量信息
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/11/27
 */
public class Constants {

  /**
   * 返回信息结果码
   */
  public static final String UNIQUE = "1";

  /**
   * token密钥
   */
  public static final String JWT_TOKEN_KEY = "CHANG_QIAN_MING_YUE_GUANG";

  /**
   * 24小时
   */
  public static final long SET_EXPIRE_TIME = System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000L;

  /**
   * 微信服务器请求地址
   */
  public static final String BASE_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

  /**
   * 小程序AppID
   */
  public static final String WX_OPEN_APP_ID = "wx0bd75fb85763ac16";

  /**
   * 小程序私钥
   */
  public static final String WX_OPEN_APP_SECRET = "9da1a41b654b441d16a313d2e890ed26";

  /**
   * 存储图片的后缀
   */
  public static final String FILE_SUFFIX_PNG = ".png";

  /**
   * 目录（相册）名称最大长度
   */
  public static final int CATALOGUE_NAME_LENGTH = 7;

  /**
   * 微信登录表单默认头像显示
   */
  public static final String DEFAULT_HEAD = "https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0";

  /**
   * 微信用户名最大长度
   */
  public static final int APPLET_USERNAME_LENGTH = 7;

  /**
   * 绑定用户名最大长度
   */
  public static final int USERNAME_LENGTH = 15;

  /**
   * 绑定密码最大长度
   */
  public static final int PASSWORD_LENGTH = 15;
  /**
   * 水印默认字体编码
   */
  public static final String DEFAULT_WATERMARK_TYPE = "d3F5LXplbmhlaQ";
}

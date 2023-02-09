package com.zscyun.Oss.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 字符串工具
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/1
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
  /**
   * 空串
   */
  public static final String NULL_STRING = "";

  /**
   * * 判断一个字符串是否为非空串
   *
   * @param str String
   * @return true：空串 false：非空串
   */
  public static boolean isEmpty(String str) {
    return isNull(str) || NULL_STRING.equals(str.trim());
  }

  /**
   * * 判断一个对象是否为空
   *
   * @param object Object
   * @return true：为空 false：非空
   */
  public static boolean isNull(Object object) {
    return object == null;
  }

  /**
   * 将一个字符串转化为base64编码
   *
   * @param str
   * @return str
   */
  public static String convertBase64(String str) {
    String resultStr = Base64.getUrlEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
    resultStr = resultStr.replace("+", "-");
    resultStr = resultStr.replace("/", "_");
    String element = "=";
    boolean beginIndexFlag = true;
    boolean endIndexFlag = true;
    do {
      int beginIndex = resultStr.indexOf(element) == 0 ? 1 : 0;
      int endIndex = resultStr.lastIndexOf(element) + 1 == resultStr.length() ? resultStr.lastIndexOf(element) : resultStr.length();
      resultStr = resultStr.substring(beginIndex, endIndex);
      beginIndexFlag = (resultStr.indexOf(element) == 0);
      endIndexFlag = (resultStr.lastIndexOf(element) + 1 == resultStr.length());
    } while (beginIndexFlag || endIndexFlag);
    return resultStr;
  }

  /**
   * 大写转化为小写
   *
   * @param str
   * @return str
   */
  public static String convertLower(String str) {
    return str.toLowerCase();
  }

  /**
   * 判断两个字符串是否相等
   *
   * @param str1
   * @param str2
   * @return true 相等 false 不相等
   */
  public static boolean strEquals(String str1, String str2) {
    return str1.equals(str2);
  }

}

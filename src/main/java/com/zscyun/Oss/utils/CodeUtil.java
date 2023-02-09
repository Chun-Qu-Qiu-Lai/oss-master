package com.zscyun.Oss.utils;

import com.google.gson.Gson;
import com.zscyun.Oss.constant.Constants;
import com.zscyun.Oss.exception.ServiceException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 微信请求码处理
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/16
 */
public class CodeUtil {
  private static final Logger log = LoggerFactory.getLogger(CodeUtil.class);

  /**
   * 向微信服务器请求用户信息
   *
   * @param code 用户code码
   * @return openId 用户openId
   */
  public static String getRes(String code) {
    String getUrlCode = String.format(Constants.BASE_URL, Constants.WX_OPEN_APP_ID, Constants.WX_OPEN_APP_SECRET, code);
    CloseableHttpClient httpclient = HttpClients.createDefault();
    String resultString = "";
    CloseableHttpResponse response = null;
    try {
      URIBuilder builder = new URIBuilder(getUrlCode);
      URI uri = builder.build();
      HttpGet httpGet = new HttpGet(uri);
      response = httpclient.execute(httpGet);
      if (response.getStatusLine().getStatusCode() == 200) {
        resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
      }
    } catch (Exception e) {
      log.error(e.getMessage());
    } finally {
      try {
        if (response != null) {
          response.close();
        }
        httpclient.close();
      } catch (IOException e) {
        log.error(e.getMessage());
      }
    }
    Gson gson = new Gson();
    Map map = gson.fromJson(resultString, HashMap.class);
    if (!StringUtils.isNull(map.get("errcode"))) {
      throw new ServiceException("code出错，请稍候重试");
    }
    return (String) map.get("openid");
  }
}

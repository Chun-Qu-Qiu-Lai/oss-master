package com.zscyun.Oss.service.impl;

import cn.hutool.json.JSONUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.*;
import com.google.gson.Gson;
import com.zscyun.Oss.config.OssConfig;
import com.zscyun.Oss.constant.Constants;
import com.zscyun.Oss.constant.HttpStatus;
import com.zscyun.Oss.entity.*;
import com.zscyun.Oss.exception.ServiceException;
import com.zscyun.Oss.mapper.CatalogueMapper;
import com.zscyun.Oss.mapper.OperationMapper;
import com.zscyun.Oss.mapper.OssMapper;
import com.zscyun.Oss.mapper.UserMapper;
import com.zscyun.Oss.service.OssService;
import com.zscyun.Oss.utils.UuidSnowflake;
import com.zscyun.Oss.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;


import java.io.File;

/**
 * 文件 业务层处理
 *
 * @author 蛋炒饭不鸡蛋
 * @date 2022/12/16
 */
@Service
public class OssServiceImpl implements OssService {
  private static final Logger logger = LoggerFactory.getLogger(OssServiceImpl.class);

  @Autowired
  private OssConfig ossConfig;

  @Autowired
  private OssMapper ossMapper;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private OperationMapper operationMapper;

  @Autowired
  private CatalogueMapper catalogueMapper;

  @Value("${aliyun.oss.accessKeyId}")
  private String aliyunOssAccessKeyId;

  @Value("${aliyun.oss.policy.expire}")
  private int aliyunOssPolicyExpire;

  @Value("${aliyun.oss.maxSize}")
  private int aliyunOssMaxSize;

  @Value("${aliyun.oss.host}")
  private String aliyunOssHost;

  @Value("${aliyun.oss.bucketName}")
  private String aliyunOssBucketName;

  @Value("${aliyun.oss.temporary}")
  private String aliyunOssTemporary;

  /**
   * @return
   */
  @Override
  public String updateFile(PictureHandleForm pictureHandleForm) {
    String style = "image/auto-orient,1/quality,q_90";
    String filePath = pictureHandleForm.getFilePath();
    Integer bright = pictureHandleForm.getBright();
    Integer contrast = pictureHandleForm.getContrast();
    boolean sharpenVisible = pictureHandleForm.isSharpenVisible();
    Integer sharpen = pictureHandleForm.getSharpen();
    boolean blurVisible = pictureHandleForm.isBlurVisible();
    Integer blurRadius = pictureHandleForm.getBlurRadius();
    Integer blurStandard = pictureHandleForm.getBlurStandard();
    Integer rotate = pictureHandleForm.getRotate();
    boolean watermarkVisible = pictureHandleForm.isWatermarkVisible();
    String text = pictureHandleForm.getText();
    String type = pictureHandleForm.getType();
    Integer size = pictureHandleForm.getSize();
    String color = pictureHandleForm.getColor();
    boolean fill = pictureHandleForm.isFill();
    boolean shadowVisible = pictureHandleForm.isShadowVisible();
    Integer shadow = pictureHandleForm.getShadow();
    Integer opacity = pictureHandleForm.getOpacity();
    String position = pictureHandleForm.getPosition();
    if (bright != 0) {
      style += "/bright," + bright;
    }
    if (contrast != 0) {
      style += "/contrast," + contrast;
    }
    if (sharpenVisible) {
      style += "/sharpen," + sharpen;
    }
    if (blurVisible) {
      style += "/blur," + "r_" + blurRadius + "," + "s_" + blurStandard;
    }
    if (rotate != 0) {
      style += "/rotate," + rotate;
    }
// 是否水印
    if (watermarkVisible) {
      if (StringUtils.isEmpty(text)) {
        throw new ServiceException("水印文字为空", 500);
      }
      if (text.length() > 15) {
        throw new ServiceException("水印文字不能超过15位", 500);
      }
      style += "/watermark";
      style += ",text_" + StringUtils.convertBase64(text);
      if (!type.equals(Constants.DEFAULT_WATERMARK_TYPE) && !StringUtils.strEquals(type, "使用默认字体")) {
        style += ",type_" + type;
      }
      style += ",size_" + size;
      if (!StringUtils.strEquals(color, "000000")) {
        style += ",color_" + StringUtils.convertLower(color);
        System.out.println(StringUtils.convertLower(color));
      }
      if (fill) {
        style += ",fill_" + 1;
      }
      if (shadowVisible && shadow != 100) {
        style += ",shadow_" + shadow;
      }
      if (opacity != 100) {
        style += ",t_" + opacity;
      }
      if (StringUtils.strEquals(position, "中部")) {
        position = "center";
      }
      style += ",g_" + position;
      style += ",x_" + 10;
      style += ",y_" + 10;
    }
    UuidSnowflake.SnowflakeDistributeId idWorker = new UuidSnowflake.SnowflakeDistributeId(0, 0);
    long uuid = idWorker.nextId();
    String newFilePath = aliyunOssTemporary + uuid + Constants.FILE_SUFFIX_PNG;
    OSS ossClient = ossConfig.ossClient();
    try {
//      String path = "8356083440779264/8361758882463744/836230270268211220230201.png";
//      String localPath = "F:\\cnm\\example-resize.jpg";
//      GetObjectRequest request = new GetObjectRequest(aliyunOssBucketName, path);
//      request.setProcess(style);
//      ossClient.getObject(request, new File(localPath));

      StringBuilder sbStyle = new StringBuilder();
      Formatter styleFormatter = new Formatter(sbStyle);
      // 将处理后的图片命名为example-resize.png并保存到当前Bucket。
      // 填写Object完整路径。Object完整路径中不能包含Bucket名称。

      styleFormatter.format("%s|sys/saveas,o_%s,b_%s", style, BinaryUtil.toBase64String(newFilePath.getBytes()), BinaryUtil.toBase64String(aliyunOssBucketName.getBytes()));
      ProcessObjectRequest request = new ProcessObjectRequest(aliyunOssBucketName, filePath, sbStyle.toString());
      GenericResult processResult = ossClient.processObject(request);
      String json = IOUtils.readStreamAsString(processResult.getResponse().getContent(), "UTF-8");
      Gson gson = new Gson();
      HashMap<String, Object> hashMap = gson.fromJson(json, HashMap.class);
      processResult.getResponse().getContent().close();
      if (!StringUtils.strEquals((String) hashMap.get("status"), "OK")) {
        throw new ServiceException("修改失败", 500);
      }
    } catch (OSSException | IOException oe) {
      throw new ServiceException("图像高级处理失败", 500);
    } catch (ClientException ce) {
      throw new ServiceException("oss服务链接失败", 500);
    }
    return aliyunOssHost + "/" + newFilePath;
  }

  /**
   * 删除文件
   *
   * @param filePath 文件路径
   * @param fileId   文件id
   * @param userId   用户id
   * @return row
   */
  @Override
  public int deleteFileByFilePath(String filePath, Long fileId, Long userId) {
    OSS ossClient = ossConfig.ossClient();
    Long catalogueId = Long.valueOf(StringUtils.substringAfterLast(StringUtils.substringBeforeLast(filePath, "/"), "/"));
    Catalogue oldCatalogue = catalogueMapper.selectCatalogue(catalogueId);
    Catalogue newCatalogue = new Catalogue();
    newCatalogue.setCatalogueId(catalogueId);
    newCatalogue.setCatalogueId(catalogueId);
    newCatalogue.setPicNumber(oldCatalogue.getPicNumber() - 1);

    int updateCatalogueRow = catalogueMapper.updateCatalogue(newCatalogue);
    if (updateCatalogueRow < 1) {
      throw new ServiceException("修改目录信息失败", HttpStatus.NOT_MODIFIED);
    }

    Operation operation = new Operation();
    operation.setUserId(userId);
    operation.setFileId(fileId);
    operation.setType(2);
    int insertOperationRow = operationMapper.insertOperation(operation);
    if (insertOperationRow < 1) {
      throw new ServiceException("记录操作日志失败", HttpStatus.NOT_MODIFIED);
    }
    try {
      ossClient.deleteObject(aliyunOssBucketName, filePath);
    } catch (OSSException | ClientException oe) {
      throw new ServiceException("连接oss服务失败" + oe, HttpStatus.NOT_MODIFIED);
    }

    int row = ossMapper.deleteFileByFilePath(filePath);
    return row;
  }

  /**
   * 批量删除文件
   *
   * @param idList 文件路径
   * @param userId 用户id
   * @return row
   */
  @Override
  public int deleteFiles(Integer[] idList, Long userId) {
    OSS ossClient = ossConfig.ossClient();
    for (Integer id : idList) {
      Picture picture = ossMapper.selectFilesById(id);
      Long catalogueId = Long.valueOf(StringUtils.substringAfterLast(StringUtils.substringBeforeLast(picture.getFilePath(), "/"), "/"));
      Catalogue oldCatalogue = catalogueMapper.selectCatalogue(catalogueId);
      Catalogue newCatalogue = new Catalogue();
      newCatalogue.setCatalogueId(catalogueId);
      newCatalogue.setCatalogueId(catalogueId);
      newCatalogue.setPicNumber(oldCatalogue.getPicNumber() - 1);
      int updateCatalogueRow = catalogueMapper.updateCatalogue(newCatalogue);
      if (updateCatalogueRow < 1) {
        throw new ServiceException("修改目录信息失败", HttpStatus.NOT_MODIFIED);
      }

      Operation operation = new Operation();
      operation.setUserId(userId);
      operation.setFileId(picture.getFileId());
      operation.setType(2);
      int insertOperationRow = operationMapper.insertOperation(operation);
      if (insertOperationRow < 1) {
        throw new ServiceException("记录操作日志失败", HttpStatus.NOT_MODIFIED);
      }
      try {
        ossClient.deleteObject(aliyunOssBucketName, picture.getFilePath());
      } catch (OSSException | ClientException oe) {
        throw new ServiceException("连接oss服务失败" + oe, HttpStatus.NOT_MODIFIED);
      }
      ossMapper.deleteFileByFilePath(picture.getFilePath());
    }
    return 1;
  }

  /**
   * 查询所有文件信息
   *
   * @param catalogueId 目录id
   * @return 文件信息列表信息
   */
  @Override
  public List<Picture> selectFiles(Long catalogueId) {
    List<Picture> pictures = ossMapper.selectFilesByCatalogueId(catalogueId);
    return pictures;
  }

  /**
   * 文件上传策略（签名）生成
   *
   * @param userId      用户id
   * @param catalogueId 目录id
   * @return OssPolicyResult 策略（签名）信息
   */
  @Override
  public OssPolicyResult policy(Long userId, Long catalogueId) {
    OssPolicyResult result = new OssPolicyResult();
    // 创建存储目录
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    String createTime = sdf.format(new Date());
    UuidSnowflake.SnowflakeDistributeId idWorker = new UuidSnowflake.SnowflakeDistributeId(0, 0);
    long uuid = idWorker.nextId();
    String dir = userId + "/" + catalogueId + "/" + uuid + createTime + Constants.FILE_SUFFIX_PNG;

    // 提交节点
    OSS ossClient = ossConfig.ossClient();
    try {
      // 签名有效期
      long expireEndTime = System.currentTimeMillis() + aliyunOssPolicyExpire * 1000L;
      Date expiration = new Date(expireEndTime);
      // 文件大小
      long maxSize = (long) aliyunOssMaxSize * 1024 * 1024;

      PolicyConditions policyConds = new PolicyConditions();
      policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, maxSize);
      policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

      String postPolicy = ossClient.generatePostPolicy(expiration, policyConds);
      byte[] binaryData = postPolicy.getBytes(StandardCharsets.UTF_8);
      String encodedPolicy = BinaryUtil.toBase64String(binaryData);
      String signature = ossConfig.ossClient().calculatePostSignature(postPolicy);
      //回调
      OssCallbackParam callback = new OssCallbackParam();
      callback.setCallbackUrl("http://47.93.50.126:5206/oss/callback");
      callback.setCallbackBody("filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
      callback.setCallbackBodyType("application/x-www-form-urlencoded");
      String callbackData = BinaryUtil.toBase64String(JSONUtil.parse(callback).toString().getBytes(StandardCharsets.UTF_8));
      // 返回结果
      result.setAccessKeyId(aliyunOssAccessKeyId);
      result.setPolicy(encodedPolicy);
      result.setSignature(signature);
      result.setDir(dir);
      result.setCallback(callbackData);
      result.setHost(aliyunOssHost);
    } catch (Exception e) {
      throw new ServiceException("签名失败", 500);
    }
    return result;
  }

  /**
   * oss上传成功回调
   *
   * @param request 请求对象
   * @return OssCallbackResult 回调信息
   */
  @Override
  public OssCallbackResult callback(HttpServletRequest request) {
    OssCallbackResult result = new OssCallbackResult();
    String filePath = request.getParameter("filename");
    String fileUrl = aliyunOssHost + "/" + filePath;
    Long userId = Long.valueOf(StringUtils.substringBefore(filePath, "/"));
    String fileName = StringUtils.substringAfterLast(filePath, "/");
    Long catalogueId = Long.valueOf(StringUtils.substringAfterLast(StringUtils.substringBeforeLast(filePath, "/"), "/"));

    UuidSnowflake.SnowflakeDistributeId idWorker = new UuidSnowflake.SnowflakeDistributeId(0, 0);
    long fileId = idWorker.nextId();

    Picture picture = new Picture();
    picture.setFileId(fileId);
    picture.setUserId(userId);
    picture.setCatalogueId(catalogueId);
    picture.setFileName(fileName);
    picture.setFilePath(filePath);
    picture.setFileUrl(fileUrl);
    picture.setStatus(1);
    picture.setType(request.getParameter("mimeType"));
    picture.setSize(Integer.parseInt(request.getParameter("size")));
    picture.setWidth(Integer.parseInt(request.getParameter("width")));
    picture.setHeight(Integer.parseInt(request.getParameter("height")));

    int row = ossMapper.insertFile(picture);
    if (row < 1) {
      throw new ServiceException("上传图片失败", HttpStatus.NOT_MODIFIED);
    }

    User user = userMapper.selectUserByUserId(userId);
    User userInfo = new User();
    userInfo.setUserId(user.getUserId());
    userInfo.setUploadNumber(user.getUploadNumber() + 1);
    userMapper.updateUser(userInfo);

    Catalogue oldCatalogue = catalogueMapper.selectCatalogue(catalogueId);
    Catalogue newCatalogue = new Catalogue();
    newCatalogue.setCatalogueId(catalogueId);
    newCatalogue.setPicNumber(oldCatalogue.getPicNumber() + 1);
    int updateCatalogueRow = catalogueMapper.updateCatalogue(newCatalogue);
    if (updateCatalogueRow < 1) {
      throw new ServiceException("修改目录信息失败", HttpStatus.NOT_MODIFIED);
    }

    Operation operation = new Operation();
    operation.setUserId(userId);
    operation.setFileId(fileId);
    operation.setType(0);
    int insertOperationRow = operationMapper.insertOperation(operation);
    if (insertOperationRow < 1) {
      throw new ServiceException("记录操作日志失败", HttpStatus.NOT_MODIFIED);
    }

    result.setFileUrl(fileUrl);
    result.setSize(request.getParameter("size"));
    result.setMimeType(request.getParameter("mimeType"));
    result.setWidth(request.getParameter("width"));
    result.setHeight(request.getParameter("height"));
    return result;
  }

  /**
   * 下载文件
   *
   * @param openId 用户openId
   * @param fileId 文件id
   * @param userId 用户id
   * @return row
   */
  @Override
  public int downloadFile(String openId, Long fileId, Long userId) {
    Operation operation = new Operation();
    operation.setUserId(userId);
    operation.setFileId(fileId);
    operation.setType(1);
    int insertOperationRow = operationMapper.insertOperation(operation);
    if (insertOperationRow < 1) {
      throw new ServiceException("记录操作日志失败", HttpStatus.NOT_MODIFIED);
    }

    User user = userMapper.selectUserByOpenId(openId);
    User userInfo = new User();
    userInfo.setDownloadNumber(user.getDownloadNumber() + 1);
    userInfo.setUserId(user.getUserId());
    int row = userMapper.updateUser(userInfo);
    return row;
  }


}


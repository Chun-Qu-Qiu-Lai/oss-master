package com.zscyun.Oss.service;

import com.zscyun.Oss.entity.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * oss业务
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/12/16
 */
public interface OssService {

  public String updateFile(PictureHandleForm pictureHandleForm) throws UnsupportedEncodingException;

  /**
   * 删除文件
   *
   * @param filePath 文件路径
   * @return row
   */
  public int deleteFileByFilePath(String filePath, Long fileId, Long userId);

  /**
   * 批量删除文件
   *
   * @param idList 文件路径
   * @param userId 用户id
   * @return row
   */
  public int deleteFiles(Integer[] idList, Long userId);


  /**
   * 查询所有文件信息
   *
   * @param catalogueId 目录id
   * @return 文件信息列表信息
   */
  public List<Picture> selectFiles(Long catalogueId);

  /**
   * 文件上传策略生成
   *
   * @param userId      用户id
   * @param catalogueId 目录id
   * @return OssPolicyResult 策略信息
   */
  public OssPolicyResult policy(Long userId, Long catalogueId);

  /**
   * 上传回调信息
   *
   * @param request request
   * @return OssCallbackResult
   */
  public OssCallbackResult callback(HttpServletRequest request);

  /**
   * 下载文件
   *
   * @param openId 用户openId
   * @return row
   */
  public int downloadFile(String openId, Long fileId, Long userId);

  /**
   * 获取所有图片
   *
   * @param userId 用户id
   * @return 结果
   */
  public List<Picture> selectFileList(Long userId);

  /**
   * @param userId    userid
   * @param tokenName
   * @return
   */
  public int createToken(Long userId, String tokenName);

  /**
   * 查询所有token
   *
   * @param userId 用户id
   * @return 结果
   */
  public List<Token> selectTokens(Long userId);

  /**
   * 删除token
   *
   * @param tokenId tokenId
   * @return 结果
   */
  public int deleteToken(Integer tokenId);

  /**
   * 为外界提供api接口
   *
   * @param userId
   * @param multipartFile
   * @return
   */
  public String uploadApi(Long userId, MultipartFile multipartFile);
}

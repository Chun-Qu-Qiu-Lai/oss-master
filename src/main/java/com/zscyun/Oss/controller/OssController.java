package com.zscyun.Oss.controller;

import com.zscyun.Oss.constant.Constants;
import com.zscyun.Oss.constant.HttpStatus;
import com.zscyun.Oss.entity.*;
import com.zscyun.Oss.exception.ServiceException;
import com.zscyun.Oss.mapper.UserMapper;
import com.zscyun.Oss.service.impl.CatalogueServiceImpl;
import com.zscyun.Oss.service.impl.OssServiceImpl;
import com.zscyun.Oss.utils.JwtUtil;
import com.zscyun.Oss.utils.StringUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 图片业务
 *
 * @author 蛋炒饭不加蛋
 * @date 2022/11/27
 */
@RestController
@RequestMapping("/oss")
public class OssController {
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private OssServiceImpl ossService;

  @Autowired
  private CatalogueServiceImpl catalogueService;

  /**
   * 删除图片
   *
   * @param authorizationJwt token
   * @param filePath         文件路径
   * @param fileId           文件id
   * @return Result
   */
  @PostMapping("/removeFile")
  public Result removeFile(@RequestHeader("Authorization") String authorizationJwt,
                           @RequestParam("filePath") String filePath,
                           @RequestParam("fileId") Long fileId) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }

    if (StringUtils.isEmpty(filePath)) {
      throw new ServiceException("文件路径为空", HttpStatus.BAD_REQUEST);
    }
    int row = ossService.deleteFileByFilePath(filePath, fileId, user.getUserId());
    if (row > 0) {
      return Result.success();
    } else {
      return Result.fail();
    }
  }

  /**
   * 批量删除图片
   *
   * @param authorizationJwt token
   * @param idList           文件id
   * @return Result
   */
  @PostMapping("/removeFiles/{idList}")
  public Result removeFiles(@RequestHeader("Authorization") String authorizationJwt,
                            @PathVariable Integer[] idList) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }
    int row = ossService.deleteFiles(idList, user.getUserId());
    if (row > 0) {
      return Result.success();
    } else {
      return Result.fail();
    }
  }

  /**
   * 获取当前目录下所有图片信息
   *
   * @param authorizationJwt token
   * @param catalogueId      目录（相册）id
   * @return Result
   */
  @PostMapping("/applet/getFiles")
  public Result getFiles(@RequestHeader("Authorization") String authorizationJwt,
                         @RequestParam("catalogueId") Long catalogueId) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }

    List<Picture> pictures = ossService.selectFiles(catalogueId);
    return Result.success(ResultStatus.HTTP_STATUS_200, pictures);
  }

  /**
   * 获取当前目录（相册）下的所有图片表格信息
   *
   * @param catalogueId 目录（相册）id
   * @return Result
   */
  @PostMapping("/admin/getPicTables")
  public Result getPicTables(@RequestParam("catalogueId") Long catalogueId) {
    if (StringUtils.isNull(catalogueId)) {
      throw new ServiceException("目录id为空", HttpStatus.BAD_REQUEST);
    }
    List<PicTable> picTables = catalogueService.selectPicTables(catalogueId);
    return Result.success(ResultStatus.HTTP_STATUS_200, picTables);
  }

  /**
   * 签名
   *
   * @param authorizationJwt token
   * @param catalogueId      目录（相册）id
   * @return Result
   */
  @PostMapping("/policy")
  public Result policy(@RequestHeader("Authorization") String authorizationJwt,
                       @RequestParam("catalogueId") Long catalogueId) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }
    if (StringUtils.isNull(catalogueId)) {
      throw new ServiceException("目录id为空", HttpStatus.BAD_REQUEST);
    }

    OssPolicyResult result = ossService.policy(user.getUserId(), catalogueId);
    return Result.success(ResultStatus.HTTP_STATUS_200, result);
  }

  /**
   * 回调
   *
   * @param request request
   * @return Result
   */
  @PostMapping("/callback")
  public Result callback(HttpServletRequest request) {
    OssCallbackResult callback = ossService.callback(request);
    return Result.success(ResultStatus.HTTP_STATUS_200, callback);
  }

  /**
   * 下载图片
   *
   * @param authorizationJwt token
   * @param fileId           文件id
   * @return Result
   */
  @PostMapping("/download")
  private Result addDownloadNumber(@RequestHeader("Authorization") String authorizationJwt,
                                   @RequestParam("fileId") Long fileId) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }

    if (ossService.downloadFile(openid, fileId, user.getUserId()) > 0) {
      return Result.success();
    } else {
      return Result.fail();
    }
  }

  /**
   * 处理图片
   *
   * @param pictureHandleForm 表单参数
   * @return
   */
  @PostMapping("/editFile")
  private Result editFile(@RequestBody PictureHandleForm pictureHandleForm) {
    String s = ossService.updateFile(pictureHandleForm);
    return Result.success(ResultStatus.HTTP_STATUS_200, s);
  }

  /**
   * 获取所有图片
   *
   * @param authorizationJwt token
   * @return 结果
   */
  @GetMapping("/admin/getFileList")
  private Result getFileList(@RequestHeader("Authorization") String authorizationJwt) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }
    List<Picture> pictures = ossService.selectFileList(user.getUserId());
    return Result.success(ResultStatus.HTTP_STATUS_200, pictures);
  }

  /**
   * 创建token
   *
   * @param authorizationJwt
   * @return
   */
  @PostMapping("/admin/addToken")
  private Result addToken(@RequestHeader("Authorization") String authorizationJwt,
                          @RequestParam("tokenName") String tokenName) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }
    if (StringUtils.isEmpty(tokenName)) {
      throw new ServiceException("token名称为空", HttpStatus.BAD_REQUEST);
    }
    int row = ossService.createToken(user.getUserId(), tokenName);

    if (row > 0) {
      return Result.success();
    } else {
      return Result.fail();
    }
  }

  /**
   * 删除token
   *
   * @param authorizationJwt
   * @param tokenId
   * @return
   */
  @PostMapping("/admin/removeToken")
  private Result removeToken(@RequestHeader("Authorization") String authorizationJwt,
                             @RequestParam("tokenId") Integer tokenId) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }
    int row = ossService.deleteToken(tokenId);
    if (row > 0) {
      return Result.success();
    } else {
      return Result.fail();
    }
  }

  /**
   * 查询所有token
   *
   * @param authorizationJwt
   * @return 结果
   */
  @GetMapping("/admin/getTokens")
  private Result getTokens(@RequestHeader("Authorization") String authorizationJwt) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }
    List<Token> tokens = ossService.selectTokens(user.getUserId());
    return Result.success(ResultStatus.HTTP_STATUS_200, tokens);
  }

  /**
   * 为外界提供上传接口，随机相册
   *
   * @param
   * @param
   * @return
   */
  @PostMapping("/admin/token/upload")
  private Result apiUpload(@RequestHeader("Authorization") String token,
                           @RequestParam("imgFile") MultipartFile imgFile) {
    Claims claims = Jwts.parser()
            .setSigningKey(Constants.JWT_TOKEN_KEY)
            .parseClaimsJws(token)
            .getBody();
    Long userId = (Long) claims.get("userId");
    if (StringUtils.isNull(userId)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }
    String url = ossService.uploadApi(userId, imgFile);
    return Result.success(ResultStatus.HTTP_STATUS_200, url);
  }


}

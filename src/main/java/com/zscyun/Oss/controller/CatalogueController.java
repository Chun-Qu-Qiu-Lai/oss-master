package com.zscyun.Oss.controller;

import com.zscyun.Oss.constant.Constants;
import com.zscyun.Oss.constant.HttpStatus;
import com.zscyun.Oss.entity.*;
import com.zscyun.Oss.exception.ServiceException;
import com.zscyun.Oss.mapper.UserMapper;
import com.zscyun.Oss.service.impl.CatalogueServiceImpl;
import com.zscyun.Oss.service.impl.UserServiceImpl;
import com.zscyun.Oss.utils.JwtUtil;
import com.zscyun.Oss.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 目录（相册）业务
 *
 * @author 蛋炒饭不加蛋
 * @date 2023/1/6
 */
@RestController
@RequestMapping("/catalogue")
public class CatalogueController {
  @Autowired
  private CatalogueServiceImpl catalogueService;

  @Autowired
  private UserMapper userMapper;

  @Autowired
  private UserServiceImpl userService;

  /**
   * 创建目录（相册）
   *
   * @param authorizationJwt token
   * @param catalogueName    目录（相册）名称
   * @return Result
   */
  @PostMapping("/addCatalogue")
  public Result addCatalogue(@RequestHeader("Authorization") String authorizationJwt,
                             @RequestParam("catalogueName") String catalogueName) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("请先登录", HttpStatus.UNAUTHORIZED);
    }
    if (StringUtils.isEmpty(catalogueName)) {
      throw new ServiceException("相册名称为空", HttpStatus.BAD_REQUEST);
    }
    if (catalogueName.length() > Constants.CATALOGUE_NAME_LENGTH) {
      throw new ServiceException("相册名称不能超过" + Constants.CATALOGUE_NAME_LENGTH + "位", HttpStatus.BAD_REQUEST);
    }
    int row = catalogueService.createCatalogue(user, catalogueName);
    if (row > 0) {
      return Result.success(ResultStatus.HTTP_STATUS_200);
    } else {
      return Result.fail();
    }
  }

  /**
   * 获取目录信息（相册）
   *
   * @param authorizationJwt token
   * @return Result
   */
  @GetMapping("/applet/getCatalogues")
  public Result getCatalogues(@RequestHeader("Authorization") String authorizationJwt) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }

    List<Catalogue> catalogues = catalogueService.selectCatalogues(user.getUserId());
    return Result.success(ResultStatus.HTTP_STATUS_200, catalogues);
  }

  /**
   * 修改目录（相册）信息
   *
   * @param newAlbumName 目录（相册）名称
   * @param catalogueId  目录（相册）id
   * @return Result
   */
  @PostMapping("/admin/editCatalogueName")
  public Result editCatalogue(@RequestParam("newAlbumName") String newAlbumName,
                              @RequestParam("catalogueId") Long catalogueId) {
    if (StringUtils.isEmpty(newAlbumName)) {
      throw new ServiceException("相册名称为空", HttpStatus.BAD_REQUEST);
    }
    if (newAlbumName.length() > Constants.CATALOGUE_NAME_LENGTH) {
      throw new ServiceException("相册名称过长", HttpStatus.BAD_REQUEST);
    }
    if (StringUtils.isNull(catalogueId)) {
      throw new ServiceException("目录id为空", HttpStatus.BAD_REQUEST);
    }

    Catalogue catalogue = new Catalogue();
    catalogue.setName(newAlbumName);
    catalogue.setCatalogueId(catalogueId);
    int row = catalogueService.updateCatalogue(catalogue);
    if (row > 0) {
      return Result.success(ResultStatus.HTTP_STATUS_200);
    } else {
      return Result.fail();
    }
  }

  /**
   * 获取目录（相册）表格信息
   *
   * @param authorizationJwt token
   * @return Result
   */
  @GetMapping("/admin/getCatalogueTables")
  public Result getCatalogueTables(@RequestHeader("Authorization") String authorizationJwt) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userService.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("未授权", HttpStatus.UNAUTHORIZED);
    }
    List<CatalogueTable> catalogueTables = catalogueService.selectCatalogueTables(user.getUserId());
    return Result.success(ResultStatus.HTTP_STATUS_200, catalogueTables);
  }

  /**
   * 删除目录（相册）及其下的所有文件
   *
   * @param authorizationJwt token
   * @param catalogueId      目录（相册）id
   * @return Result
   */
  @PostMapping("/admin/removeCatalogue")
  public Result removeCatalogue(@RequestHeader("Authorization") String authorizationJwt,
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
    int row = catalogueService.deleteCatalogue(user.getUserId(), catalogueId);
    if (row > 0) {
      return Result.success(ResultStatus.HTTP_STATUS_200);
    } else {
      return Result.fail();
    }
  }

}

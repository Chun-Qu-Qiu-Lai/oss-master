package com.zscyun.Oss.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import com.zscyun.Oss.config.OssConfig;
import com.zscyun.Oss.constant.HttpStatus;
import com.zscyun.Oss.entity.Catalogue;
import com.zscyun.Oss.entity.CatalogueTable;
import com.zscyun.Oss.entity.PicTable;
import com.zscyun.Oss.entity.User;
import com.zscyun.Oss.exception.ServiceException;
import com.zscyun.Oss.mapper.CatalogueMapper;
import com.zscyun.Oss.mapper.OssMapper;
import com.zscyun.Oss.service.CatalogueService;
import com.zscyun.Oss.utils.UuidSnowflake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 目录 业务层处理
 *
 * @author 蛋炒饭不加蛋
 * @date 2023/1/6
 */
@Service
public class CatalogueServiceImpl implements CatalogueService {

  @Autowired
  private CatalogueMapper catalogueMapper;

  @Autowired
  private OssMapper ossMapper;

  @Autowired
  private OssConfig ossConfig;

  @Value("${aliyun.oss.bucketName}")
  private String aliyunOssBucketName;



  /**
   * 创建目录
   *
   * @param user          用户信息
   * @param catalogueName 目录名称
   * @return row
   */

  @Override
  public int createCatalogue(User user, String catalogueName) {
    Long parentId = user.getUserId();
    OSS ossClient = ossConfig.ossClient();
    UuidSnowflake.SnowflakeDistributeId idWorker = new UuidSnowflake.SnowflakeDistributeId(0, 0);
    long catalogueId = idWorker.nextId();
    String catalogueUrl = parentId + "/" + catalogueId + "/";
    Catalogue catalogue = new Catalogue();
    catalogue.setCatalogueId(catalogueId);
    catalogue.setStatus(1);
    catalogue.setParentId(parentId);
    catalogue.setName(catalogueName);
    try {
      String content = "";
      // 创建PutObjectRequest对象。
      PutObjectRequest putObjectRequest = new PutObjectRequest(aliyunOssBucketName, catalogueUrl, new ByteArrayInputStream(content.getBytes()));
      // 上传字符串。
      ossClient.putObject(putObjectRequest);
    } catch (OSSException | ClientException oe) {
      throw new ServiceException("创建目录失败", HttpStatus.ERROR);
    }
    int row = catalogueMapper.insertCatalogue(catalogue);
    return row;
  }

  /**
   * 查询指定根目录下的所有目录信息
   *
   * @param parentId 根目录id
   * @return 目录集合信息
   */
  @Override
  public List<Catalogue> selectCatalogues(Long parentId) {
    List<Catalogue> catalogues = catalogueMapper.selectCatalogues(parentId);
    return catalogues;
  }

  /**
   * 修改目录信息
   *
   * @param catalogue 目录
   * @return row
   */
  @Override
  public int updateCatalogue(Catalogue catalogue) {
    int row = catalogueMapper.updateCatalogue(catalogue);
    return row;
  }

  @Override
  public List<CatalogueTable> selectCatalogueTables(Long userId) {
    List<CatalogueTable> catalogueTables = catalogueMapper.selectAlbumTables(userId);
    return catalogueTables;
  }

  /**
   * @param catalogueId
   * @return
   */
  @Override
  public List<PicTable> selectPicTables(Long catalogueId) {
    List<PicTable> picTables = catalogueMapper.selectPicTables(catalogueId);
    return picTables;
  }

  /**
   * 删除目录（相册）及其下的所有文件
   *
   * @param parentId    父目录id
   * @param catalogueId 目录id
   * @return row
   */
  @Override
  public int deleteCatalogue(Long parentId, Long catalogueId) {
    // 填写待删除目录的完整路径，完整路径中不包含Bucket名称。
    String path = parentId + "/" + catalogueId + "/";
    // 创建OSSClient实例。
    OSS ossClient = ossConfig.ossClient();
    try {
      // 删除目录及目录下的所有文件。
      String nextMarker = null;
      ObjectListing objectListing = null;
      do {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest(aliyunOssBucketName)
                .withPrefix(path)
                .withMarker(nextMarker);
        objectListing = ossClient.listObjects(listObjectsRequest);
        if (objectListing.getObjectSummaries().size() > 0) {
          List<String> keys = new ArrayList<String>();
          for (OSSObjectSummary s : objectListing.getObjectSummaries()) {
            keys.add(s.getKey());
          }
          DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(aliyunOssBucketName).withKeys(keys).withEncodingType("url");
          DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(deleteObjectsRequest);
          List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
          try {
            for (String obj : deletedObjects) {
              String deleteObj = URLDecoder.decode(obj, "UTF-8");
            }
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          }
        }

        nextMarker = objectListing.getNextMarker();
      } while (objectListing.isTruncated());
    } catch (OSSException oe) {
      throw new ServiceException("删除目录失败", HttpStatus.ERROR);
    } catch (ClientException ce) {
      throw new ServiceException("连接oss服务失败", HttpStatus.ERROR);
    }
    int deleteCatalogueRow = catalogueMapper.deleteCatalogue(catalogueId);
    if (deleteCatalogueRow < 1) {
      throw new ServiceException("删除目录失败", HttpStatus.NOT_MODIFIED);
    }
    int deleteFileByCatalogueIdRow = ossMapper.deleteFileByCatalogueId(catalogueId);
    return deleteFileByCatalogueIdRow;
  }
}

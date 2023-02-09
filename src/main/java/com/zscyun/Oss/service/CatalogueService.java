package com.zscyun.Oss.service;

import com.zscyun.Oss.entity.Catalogue;
import com.zscyun.Oss.entity.CatalogueTable;
import com.zscyun.Oss.entity.PicTable;
import com.zscyun.Oss.entity.User;

import java.util.List;

/**
 * 目录 业务层
 *
 * @author 蛋炒饭不加蛋
 * @date 2023/1/6
 */
public interface CatalogueService {
  /**
   * 创建目录
   *
   * @param user          用户信息
   * @param catalogueName 目录名称
   * @return row
   */
  public int createCatalogue(User user, String catalogueName);

  /**
   * 查询指定根目录下的所有目录信息
   *
   * @param parentId 根目录id
   * @return 目录集合信息
   */
  public List<Catalogue> selectCatalogues(Long parentId);

  /**
   * 修改目录信息
   *
   * @param catalogue 目录
   * @return row
   */
  public int updateCatalogue(Catalogue catalogue);

  /**
   * 查询目录（相册）表格信息
   *
   * @param userId 目录
   * @return 目录（相册）列表
   */
  public List<CatalogueTable> selectCatalogueTables(Long userId);

  /**
   * 查询图片表格信息
   *
   * @param catalogueId 目录（相册）id
   * @return 图片列表
   */
  public List<PicTable> selectPicTables(Long catalogueId);

  /**
   * 删除目录（相册）及其下的所有文件
   *
   * @param parentId    父目录id
   * @param catalogueId 目录id
   * @return row
   */
  public int deleteCatalogue(Long parentId, Long catalogueId);
}

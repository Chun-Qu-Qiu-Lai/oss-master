package com.zscyun.Oss.mapper;

import com.zscyun.Oss.entity.Catalogue;
import com.zscyun.Oss.entity.CatalogueTable;
import com.zscyun.Oss.entity.PicTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 铔嬬倰楗笉鍔犺泲
 * @date 2023/1/6
 */
@Mapper
public interface CatalogueMapper {

  /**
   * 创建目录
   *
   * @param catalogue 目录信息
   * @return row
   */
  public int insertCatalogue(Catalogue catalogue);

  /**
   * 查询指定根目录下的所有目录信息
   *
   * @param parentId 根目录id
   * @return 目录集合信息
   */
  public List<Catalogue> selectCatalogues(Long parentId);

  public Catalogue selectCatalogue(Long catalogueId);

  /**
   * 修改目录信息
   *
   * @param catalogue
   * @return
   */
  public int updateCatalogue(Catalogue catalogue);

  /**
   * 查询相册列表
   *
   * @param userId
   * @return
   */
  public List<CatalogueTable> selectAlbumTables(Long userId);

  /**
   * 查询图片信息
   *
   * @param catalogueId
   * @return
   */
  public List<PicTable> selectPicTables(Long catalogueId);

  /**
   * 删除目录（相册）
   *
   * @param catalogueId 目录id
   * @return row
   */
  public int deleteCatalogue(Long catalogueId);
}

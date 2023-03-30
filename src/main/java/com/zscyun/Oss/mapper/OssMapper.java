package com.zscyun.Oss.mapper;

import com.zscyun.Oss.entity.Picture;
import com.zscyun.Oss.entity.Token;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 铔嬬倰楗笉鍔犺泲
 * @date 2022/12/31
 */
@Mapper
public interface OssMapper {
  /**
   * 新增文件信息
   *
   * @param picture 文件信息
   * @return row
   */
  public int insertFile(Picture picture);

  /**
   * 查询所有文件信息
   *
   * @param catalogueId 目录id
   * @return 文件信息列表
   */
  public List<Picture> selectFilesByCatalogueId(Long catalogueId);

  /**
   * 查询文件信息
   *
   * @param id 文件id
   * @return 图片信息
   */
  public Picture selectFilesById(Integer id);

  /**
   * 删除文件
   *
   * @param filePath 文件路径
   * @return row
   */
  public int deleteFileByFilePath(String filePath);

  /**
   * 批量删除文件
   *
   * @param catalogueId 目录（相册）id
   * @return row
   */
  public int deleteFileByCatalogueId(Long catalogueId);

  /**
   * 获取所有图片
   *
   * @param userId 用户id
   * @return 结果
   */
  public List<Picture> selectFileList(Long userId);


}

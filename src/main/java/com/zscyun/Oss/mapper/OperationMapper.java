package com.zscyun.Oss.mapper;


import com.zscyun.Oss.entity.CurveDownloadData;
import com.zscyun.Oss.entity.CurveUploadData;
import com.zscyun.Oss.entity.Operation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 操作记录
 *
 * @author 蛋炒饭不加蛋
 * @date 2023/1/28
 */
@Mapper
public interface OperationMapper {

  /**
   * 记录文件操作
   *
   * @param operation 对象
   * @return row
   */
  public int insertOperation(Operation operation);

  /**
   * 查询近一周的下载每天的下载数据
   *
   * @return CurveDownloadData
   */
  public List<CurveDownloadData> selectCurveDownloadData(Long userId);

  /**
   * 查询近一周的上传每天的下载数据
   *
   * @return CurveUploadData
   */
  public List<CurveUploadData> selectCurveUploadData(Long userId);

  public List<CurveDownloadData> cehshiOpe();
}

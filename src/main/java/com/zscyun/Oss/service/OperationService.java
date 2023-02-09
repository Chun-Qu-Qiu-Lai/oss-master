package com.zscyun.Oss.service;

import com.zscyun.Oss.entity.CurveDownloadData;
import com.zscyun.Oss.entity.CurveUploadData;

import java.util.List;

/**
 * 操作记录
 *
 * @author 蛋炒饭不加蛋
 * @date 2023/1/30
 */
public interface OperationService {

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
}

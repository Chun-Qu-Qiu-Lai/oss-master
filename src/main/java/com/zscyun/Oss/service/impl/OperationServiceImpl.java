package com.zscyun.Oss.service.impl;

import com.zscyun.Oss.entity.CurveDownloadData;
import com.zscyun.Oss.entity.CurveUploadData;
import com.zscyun.Oss.mapper.OperationMapper;
import com.zscyun.Oss.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 铔嬬倰楗笉鍔犺泲
 * @date 2023/1/30
 */
@Service
public class OperationServiceImpl implements OperationService {
  @Autowired
  private OperationMapper operationMapper;

  /**
   * 查询近一周的下载每天的下载数据
   *
   * @param userId 用户id
   * @return CurveDownloadData
   */
  @Override
  public List<CurveDownloadData> selectCurveDownloadData(Long userId) {
    List<CurveDownloadData> curveDownloadData = operationMapper.selectCurveDownloadData(userId);
    return curveDownloadData;
  }

  /**
   * 查询近一周的上传每天的下载数据
   *
   * @param userId 用户id
   * @return CurveUploadData
   */
  @Override
  public List<CurveUploadData> selectCurveUploadData(Long userId) {
    List<CurveUploadData> curveUploadData = operationMapper.selectCurveUploadData(userId);
    return curveUploadData;
  }
}

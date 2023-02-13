package com.zscyun.Oss.controller;

import com.zscyun.Oss.constant.HttpStatus;
import com.zscyun.Oss.entity.*;
import com.zscyun.Oss.exception.ServiceException;
import com.zscyun.Oss.mapper.UserMapper;
import com.zscyun.Oss.service.OperationService;
import com.zscyun.Oss.utils.JwtUtil;
import com.zscyun.Oss.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作记录（图表）
 *
 * @author 蛋炒饭不加蛋
 * @date 2023/1/30
 */
@RestController
@RequestMapping("/operation")
public class OperationController {
  @Autowired
  private OperationService operationService;
  @Autowired
  private UserMapper userMapper;

  @GetMapping("/admin/downloadOperation")
  public Result getOperationDownload(@RequestHeader("Authorization") String authorizationJwt) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }

    List<CurveDownloadData> curveDownloadDatas = operationService.selectCurveDownloadData(user.getUserId());
    List<Integer> downloadValue = new ArrayList<>();
    List<String> downloadTime = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
    for (CurveDownloadData curveDownloadData : curveDownloadDatas) {
      downloadValue.add(curveDownloadData.getValue());
      downloadTime.add(sdf.format(curveDownloadData.getTime()));
    }
    Map<String, Object> curveDownloadData = new HashMap<>();
    curveDownloadData.put("downloadValue", downloadValue);
    curveDownloadData.put("downloadTime", downloadTime);
    return Result.success(ResultStatus.HTTP_STATUS_200, curveDownloadData);
  }

  @GetMapping("/admin/uploadOperation")
  public Result getOperationUpload(@RequestHeader("Authorization") String authorizationJwt) {
    String token = authorizationJwt.substring(6);
    String openid = JwtUtil.parseToken(token);
    User user = userMapper.selectUserByOpenId(openid);
    if (StringUtils.isNull(user)) {
      throw new ServiceException("用户未授权", HttpStatus.UNAUTHORIZED);
    }

    List<CurveUploadData> curveUploadDatas = operationService.selectCurveUploadData(user.getUserId());
    List<Integer> uploadValue = new ArrayList<>();
    List<String> uploadTime = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
    for (CurveUploadData curveUploadData : curveUploadDatas) {
      uploadValue.add(curveUploadData.getValue());
      uploadTime.add(sdf.format(curveUploadData.getTime()));
    }
    Map<String, Object> curveUploadData = new HashMap<>();
    curveUploadData.put("uploadValue", uploadValue);
    curveUploadData.put("uploadTime", uploadTime);
    System.out.println(uploadValue);
    System.out.println(uploadTime);
    return Result.success(ResultStatus.HTTP_STATUS_200, curveUploadData);
  }

}

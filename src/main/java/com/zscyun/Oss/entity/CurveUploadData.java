package com.zscyun.Oss.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author 蛋炒饭不加蛋
 * @date 2023/1/30
 */
public class CurveUploadData implements Serializable {
  private Integer value;

  private Date time;

  public CurveUploadData() {
  }

  public CurveUploadData(Integer value, Date time) {
    this.value = value;
    this.time = time;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CurveUploadData that = (CurveUploadData) o;
    return Objects.equals(value, that.value) && Objects.equals(time, that.time);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value, time);
  }

  @Override
  public String toString() {
    return "CurveUploadData{" +
            "value=" + value +
            ", time=" + time +
            '}';
  }
}

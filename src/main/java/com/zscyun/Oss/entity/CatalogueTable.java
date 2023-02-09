package com.zscyun.Oss.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

/**
 * @author 铔嬬倰楗笉鍔犺泲
 * @date 2023/1/29
 */
public class CatalogueTable {
  private Integer key;
  private String name;
  private Integer count;
  private Long catalogueId;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private Date createTime;
  private Integer status;

  public CatalogueTable() {
  }

  public CatalogueTable(Integer key, String name, Integer count, Long catalogueId, Date createTime, Integer status) {
    this.key = key;
    this.name = name;
    this.count = count;
    this.catalogueId = catalogueId;
    this.createTime = createTime;
    this.status = status;
  }

  public Integer getKey() {
    return key;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public Long getCatalogueId() {
    return catalogueId;
  }

  public void setCatalogueId(Long catalogueId) {
    this.catalogueId = catalogueId;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogueTable that = (CatalogueTable) o;
    return Objects.equals(key, that.key) && Objects.equals(name, that.name) && Objects.equals(count, that.count) && Objects.equals(catalogueId, that.catalogueId) && Objects.equals(createTime, that.createTime) && Objects.equals(status, that.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, name, count, catalogueId, createTime, status);
  }

  @Override
  public String toString() {
    return "CatalogueTable{" +
            "key=" + key +
            ", name='" + name + '\'' +
            ", count=" + count +
            ", catalogueId=" + catalogueId +
            ", createTime=" + createTime +
            ", status=" + status +
            '}';
  }
}


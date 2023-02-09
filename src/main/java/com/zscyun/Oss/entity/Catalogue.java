package com.zscyun.Oss.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 目录
 *
 * @author 蛋炒饭不加蛋
 * @date 2023/1/6
 */
public class Catalogue implements Serializable {
  private Long id;
  private Long catalogueId;
  private String name;
  private Long parentId;
  private Integer picNumber;
  private int status;
  private Date createTime;
  private Date deleteTime;

  public Catalogue() {
  }

  public Catalogue(Long id, Long catalogueId, String name, Long parentId, Integer picNumber, int status, Date createTime, Date deleteTime) {
    this.id = id;
    this.catalogueId = catalogueId;
    this.name = name;
    this.parentId = parentId;
    this.picNumber = picNumber;
    this.status = status;
    this.createTime = createTime;
    this.deleteTime = deleteTime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCatalogueId() {
    return catalogueId;
  }

  public void setCatalogueId(Long catalogueId) {
    this.catalogueId = catalogueId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getParentId() {
    return parentId;
  }

  public void setParentId(Long parentId) {
    this.parentId = parentId;
  }

  public Integer getPicNumber() {
    return picNumber;
  }

  public void setPicNumber(Integer picNumber) {
    this.picNumber = picNumber;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public Date getDeleteTime() {
    return deleteTime;
  }

  public void setDeleteTime(Date deleteTime) {
    this.deleteTime = deleteTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Catalogue catalogue = (Catalogue) o;
    return status == catalogue.status && Objects.equals(id, catalogue.id) && Objects.equals(catalogueId, catalogue.catalogueId) && Objects.equals(name, catalogue.name) && Objects.equals(parentId, catalogue.parentId) && Objects.equals(picNumber, catalogue.picNumber) && Objects.equals(createTime, catalogue.createTime) && Objects.equals(deleteTime, catalogue.deleteTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, catalogueId, name, parentId, picNumber, status, createTime, deleteTime);
  }

  @Override
  public String toString() {
    return "Catalogue{" +
            "id=" + id +
            ", catalogueId=" + catalogueId +
            ", name='" + name + '\'' +
            ", parentId=" + parentId +
            ", picNumber=" + picNumber +
            ", status=" + status +
            ", createTime=" + createTime +
            ", deleteTime=" + deleteTime +
            '}';
  }
}

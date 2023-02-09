package com.zscyun.Oss.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 蛋炒饭不加蛋
 */
public class PictureHandleForm implements Serializable {

  private String filePath;

  /**
   * 亮度
   */
  private Integer bright;

  /**
   * 对比度
   */
  private Integer contrast;

  /**
   * 是否锐化
   */
  private boolean sharpenVisible;

  /**
   * 锐化值
   */
  private Integer sharpen;

  /**
   * 是否图片模糊
   */
  private boolean blurVisible;

  /**
   * 模糊半径
   */
  private Integer blurRadius;

  /**
   * 模糊标准差
   */
  private Integer blurStandard;

  /**
   * 图片旋转
   */
  private Integer rotate;

  /**
   * 是否使用水印
   */
  private boolean watermarkVisible;

  /**
   * 水印内容
   */
  private String text;

  /**
   * 水印字体
   */
  private String type;

  /**
   * 水印大小
   */
  private Integer size;

  /**
   * 水印颜色
   */
  private String color;

  /**
   * 水印旋转
   */
  private Integer textRotate;

  /**
   * 水印是否铺满
   */
  private boolean fill;

  /**
   * 是否文字阴影
   */
  private boolean shadowVisible;

  /**
   * 阴影透明度
   */
  private Integer shadow;

  /**
   * 水印透明度
   */
  private Integer opacity;

  /**
   * 水印位置
   */
  private String position;

  public PictureHandleForm() {
  }

  public PictureHandleForm(String filePath, Integer bright, Integer contrast, boolean sharpenVisible, Integer sharpen, boolean blurVisible, Integer blurRadius, Integer blurStandard, Integer rotate, boolean watermarkVisible, String text, String type, Integer size, String color, Integer textRotate, boolean fill, boolean shadowVisible, Integer shadow, Integer opacity, String position) {
    this.filePath = filePath;
    this.bright = bright;
    this.contrast = contrast;
    this.sharpenVisible = sharpenVisible;
    this.sharpen = sharpen;
    this.blurVisible = blurVisible;
    this.blurRadius = blurRadius;
    this.blurStandard = blurStandard;
    this.rotate = rotate;
    this.watermarkVisible = watermarkVisible;
    this.text = text;
    this.type = type;
    this.size = size;
    this.color = color;
    this.textRotate = textRotate;
    this.fill = fill;
    this.shadowVisible = shadowVisible;
    this.shadow = shadow;
    this.opacity = opacity;
    this.position = position;
  }

  public String getFilePath() {
    return filePath;
  }

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  public Integer getBright() {
    return bright;
  }

  public void setBright(Integer bright) {
    this.bright = bright;
  }

  public Integer getContrast() {
    return contrast;
  }

  public void setContrast(Integer contrast) {
    this.contrast = contrast;
  }

  public boolean isSharpenVisible() {
    return sharpenVisible;
  }

  public void setSharpenVisible(boolean sharpenVisible) {
    this.sharpenVisible = sharpenVisible;
  }

  public Integer getSharpen() {
    return sharpen;
  }

  public void setSharpen(Integer sharpen) {
    this.sharpen = sharpen;
  }

  public boolean isBlurVisible() {
    return blurVisible;
  }

  public void setBlurVisible(boolean blurVisible) {
    this.blurVisible = blurVisible;
  }

  public Integer getBlurRadius() {
    return blurRadius;
  }

  public void setBlurRadius(Integer blurRadius) {
    this.blurRadius = blurRadius;
  }

  public Integer getBlurStandard() {
    return blurStandard;
  }

  public void setBlurStandard(Integer blurStandard) {
    this.blurStandard = blurStandard;
  }

  public Integer getRotate() {
    return rotate;
  }

  public void setRotate(Integer rotate) {
    this.rotate = rotate;
  }

  public boolean isWatermarkVisible() {
    return watermarkVisible;
  }

  public void setWatermarkVisible(boolean watermarkVisible) {
    this.watermarkVisible = watermarkVisible;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public Integer getTextRotate() {
    return textRotate;
  }

  public void setTextRotate(Integer textRotate) {
    this.textRotate = textRotate;
  }

  public boolean isFill() {
    return fill;
  }

  public void setFill(boolean fill) {
    this.fill = fill;
  }

  public boolean isShadowVisible() {
    return shadowVisible;
  }

  public void setShadowVisible(boolean shadowVisible) {
    this.shadowVisible = shadowVisible;
  }

  public Integer getShadow() {
    return shadow;
  }

  public void setShadow(Integer shadow) {
    this.shadow = shadow;
  }

  public Integer getOpacity() {
    return opacity;
  }

  public void setOpacity(Integer opacity) {
    this.opacity = opacity;
  }

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PictureHandleForm that = (PictureHandleForm) o;
    return sharpenVisible == that.sharpenVisible && blurVisible == that.blurVisible && watermarkVisible == that.watermarkVisible && fill == that.fill && shadowVisible == that.shadowVisible && Objects.equals(filePath, that.filePath) && Objects.equals(bright, that.bright) && Objects.equals(contrast, that.contrast) && Objects.equals(sharpen, that.sharpen) && Objects.equals(blurRadius, that.blurRadius) && Objects.equals(blurStandard, that.blurStandard) && Objects.equals(rotate, that.rotate) && Objects.equals(text, that.text) && Objects.equals(type, that.type) && Objects.equals(size, that.size) && Objects.equals(color, that.color) && Objects.equals(textRotate, that.textRotate) && Objects.equals(shadow, that.shadow) && Objects.equals(opacity, that.opacity) && Objects.equals(position, that.position);
  }

  @Override
  public int hashCode() {
    return Objects.hash(filePath, bright, contrast, sharpenVisible, sharpen, blurVisible, blurRadius, blurStandard, rotate, watermarkVisible, text, type, size, color, textRotate, fill, shadowVisible, shadow, opacity, position);
  }

  @Override
  public String toString() {
    return "PictureHandleForm{" +
            "filePath='" + filePath + '\'' +
            ", bright=" + bright +
            ", contrast=" + contrast +
            ", sharpenVisible=" + sharpenVisible +
            ", sharpen=" + sharpen +
            ", blurVisible=" + blurVisible +
            ", blurRadius=" + blurRadius +
            ", blurStandard=" + blurStandard +
            ", rotate=" + rotate +
            ", watermarkVisible=" + watermarkVisible +
            ", text='" + text + '\'' +
            ", type='" + type + '\'' +
            ", size=" + size +
            ", color='" + color + '\'' +
            ", textRotate=" + textRotate +
            ", fill=" + fill +
            ", shadowVisible=" + shadowVisible +
            ", shadow=" + shadow +
            ", opacity=" + opacity +
            ", position='" + position + '\'' +
            '}';
  }
}

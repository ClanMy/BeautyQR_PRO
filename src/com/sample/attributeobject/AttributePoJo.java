package com.sample.attributeobject;


import com.google.zxing.common.BitMatrix;

/** 二维码相关属性 */
public class AttributePoJo {
    /**
     * 图片宽
     */
    private Integer width;
    /**
     * 图片高
     */
    private Integer height;
    /**
     * 图片格式
     */
    private String format;
    /**
     * 图片名称
     */
    private String imageName;
    /**
     * 图片内容
     */
    private String content;
    /**
     * 生成的二维码
     */
    private BitMatrix bitMatrix;

    //初始化对象
    private static AttributePoJo attributeobject = new AttributePoJo();
    //唯一对外的方法
    public static AttributePoJo getAttribute() {
        return attributeobject;
    }

    private AttributePoJo() {
    }

    public BitMatrix getBitMatrix() {
        return bitMatrix;
    }

    public void setBitMatrix(BitMatrix bitMatrix) {
        this.bitMatrix = bitMatrix;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
}

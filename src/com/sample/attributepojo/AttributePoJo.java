package com.sample.attributepojo;


import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.common.BitMatrix;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * 二维码相关属性
 */
public class AttributePoJo {
    /**
     * 图片格式数组
     */
    private String[] formatArray = {"png", "jpg", "gif"};

    public String[] getFormatArray() {
        return formatArray;
    }

    /**
     * 二维码宽
     */
    private Integer width;
    /**
     * 二维码高
     */
    private Integer height;
    /**
     * 二维码格式
     */
    private String format;
    /**
     * 二维码名称
     */
    private String imageName;
    /**
     * 二维码背景颜色
     */
    private ObjectProperty<Color> backgroundColor;
    /**
     * 二维码前景颜色
     */
    private ObjectProperty<Color> foregroundColor;
    /**
     * 二维码内容
     */
    private String content;
    /**
     * 生成的二维码原始数据（矩阵图像）
     */
    private BitMatrix bitMatrix;
    /**
     * 矩阵图像背景颜色和前景颜色配置
     */
    private MatrixToImageConfig matrixToImageConfig;
    /**
     * 转换的可写图片数据
     */
    private WritableImage writableImage;
    /**
     * 二维码输出路径
     */
    private String outPath;
    /**
     * 二维码中间LOGO
     */
    private Image logo;
    /**
     * LOGO路径
     */
    private String logoPath;


    //初始化对象
    private static AttributePoJo attributeobject = new AttributePoJo();

    //唯一对外的方法
    public static AttributePoJo getAttribute() {
        return attributeobject;
    }

    //无参构造函数
    private AttributePoJo() {
    }

    public MatrixToImageConfig getMatrixToImageConfig() {
        return matrixToImageConfig;
    }

    public void setMatrixToImageConfig(MatrixToImageConfig matrixToImageConfig) {
        this.matrixToImageConfig = matrixToImageConfig;
    }

    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public ObjectProperty<Color> getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(ObjectProperty<Color> backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public ObjectProperty<Color> getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(ObjectProperty<Color> foregroundColor) {
        this.foregroundColor = foregroundColor;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public WritableImage getWritableImage() {
        return writableImage;
    }

    public void setWritableImage(WritableImage writableImage) {
        this.writableImage = writableImage;
    }

    public String getFormat() {
        return format;
    }

    public BitMatrix getBitMatrix() {
        return bitMatrix;
    }

    public void setBitMatrix(BitMatrix bitMatrix) {
        this.bitMatrix = bitMatrix;
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

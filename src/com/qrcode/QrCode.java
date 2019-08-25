package com.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sample.attributepojo.AttributePoJo;
import com.sample.attributepojo.FxAttributePoJo;
import com.sample.contro.tippopup.PopUps;
import com.sample.utensil.ColorToInt;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Clan.My
 * 生成二维码类
 */
public class QrCode {
    //图片全局属性
    private AttributePoJo attribute = AttributePoJo.getAttribute();
    //FX全局组件
    private FxAttributePoJo fxAttribute = FxAttributePoJo.getFxAttributePoJo();
    //格式
    private BarcodeFormat barcodeFormat = BarcodeFormat.QR_CODE;
    //定义二维码参数
    private static Map<EncodeHintType, Object> hashMap = new HashMap<>();

    static {
        //定义字符集
        hashMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 存储字符大小 从小到大分别为 L,M,Q,H
        hashMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);

        hashMap.put(EncodeHintType.MARGIN, 2);
    }

    /**
     * 描述：生成二维码图片并转换为可写的图片数据
     *
     * @return WritableImage
     */
    public WritableImage generate() {
        WritableImage writableImage = null;
        try {
            //生成二维码图片矩阵（原始二维码数据）
            BitMatrix bitMatrix = new MultiFormatWriter().encode(attribute.getContent(), barcodeFormat, attribute.getWidth(), attribute.getHeight(), hashMap);
            //判断不为空时转换为可写的图片数据
            if (bitMatrix != null) {
                //放入全局图片属性中
                attribute.setBitMatrix(bitMatrix);
                //转化为16进制颜色
                int onColor = ColorToInt.getColorToInt(attribute.getForegroundColor().get());
                int offColor = ColorToInt.getColorToInt(attribute.getBackgroundColor().get());
                //设置图片的背景颜色
                MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(onColor, offColor);
                //放入二维码全局对象中方便下面输出时调用
                attribute.setMatrixToImageConfig(matrixToImageConfig);
                //先转换为BufferedImage
                BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
                //将转换BufferedImage类型的图片为可写的image
                writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
            } else {
                fxAttribute.getTip_Label().setText("图片生成失败");
            }
        } catch (WriterException e) {
            //异常提示内容
            PopUps.pointa("图片生成异常", e.getLocalizedMessage());
        }
        return writableImage;
    }

    /**
     * 描述：输出生成的图片
     */
    public void outMatrix() {
        // 获取原始二维码数据
        BitMatrix bitMatrix = attribute.getBitMatrix();
        //判断是否有二维码
        if (bitMatrix != null) {
            try {
                //图片输出
                MatrixToImageWriter.writeToPath(bitMatrix, attribute.getFormat(), new File(attribute.getOutPath()).toPath(), attribute.getMatrixToImageConfig());
                fxAttribute.getTip_Label().setText("输出成功");
            } catch (IOException e) {
                //异常提示内容
                PopUps.pointa("输出失败", e.getMessage());
            }
        } else {
            fxAttribute.getTip_Label().setText("没有二维码");
        }
    }

    /**
     * 描述：存入剪贴板
     *
     * @param writableImage 一个可以写入的图片
     */
    public void clipBoard(WritableImage writableImage) {
        //获得一个系统剪切板
        Clipboard systemClipboard = Clipboard.getSystemClipboard();
        //获得一个内容
        ClipboardContent content = new ClipboardContent();
        //添加到内容中
        content.putImage(writableImage);
        //将内容放入剪贴板中
        systemClipboard.setContent(content);
    }

}

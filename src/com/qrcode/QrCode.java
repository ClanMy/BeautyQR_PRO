package com.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sample.attributeobject.AttributePoJo;
import com.sample.attributeobject.FxAttributePoJo;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
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
    //当前桌面路径(输出路径)
    private String desPath = FileSystemView.getFileSystemView().getHomeDirectory().getAbsolutePath() + File.separator;
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
     * 描述：生成二维码
     */
    public BitMatrix generate() {

        BitMatrix bitMatrix = null;
        try {
            //生成的二维码
            bitMatrix = new MultiFormatWriter().encode(attribute.getContent(), barcodeFormat, attribute.getWidth(), attribute.getHeight(), hashMap);
        } catch (WriterException e) {
            System.out.println("图片生成失败");
            e.printStackTrace();
        }
        return bitMatrix;
    }

    /**
     * 描述：输出生成的图片
     */
    public void outMatrix() {
        //提示信息
        Label tip_label = fxAttribute.getTip_Label();
        //输出路径
        String outPath = fxAttribute.getOutPath();
        //图片格式
        String format = attribute.getFormat();
        //要输出的二维码
        BitMatrix bitMatrix = attribute.getBitMatrix();
        //判断是否有自定义路径，如果没有就使用本地桌面
        if (outPath == null || "".equals(outPath)) {
            outPath = desPath;
        }
        //判断图片格式为空时就设置默认值
        if (format == null || "".equals(format)) {
            format = "png";
        }
        //判断是否有二维码
        if (bitMatrix != null) {
            //输出路径
            Path paint = new File(outPath + attribute.getImageName() + "." + format).toPath();
            try {
                //图片输出
                MatrixToImageWriter.writeToPath(bitMatrix, format, paint);
                tip_label.setText("输出成功");
            } catch (IOException e) {
//            e.printStackTrace();
                tip_label.setText("输出失败");
            }
        } else {
            tip_label.setText("没有二维码");
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

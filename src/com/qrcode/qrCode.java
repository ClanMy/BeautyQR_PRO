package com.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Clan.My
 * 生成二维码类
 */
public class qrCode {
    /*
     *初始化相关参数
     * */
    //日期个格式
    private String dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S").format(new Date());
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
     * @param content 内容
     * @param width   宽
     * @param height  高
     * @return  返回一个矩阵数据流格式的图片
     */

    public BitMatrix generate(String content, int width, int height) {

        BitMatrix encode = null;
        try {
            //生成二维码
            encode = new MultiFormatWriter().encode(content, barcodeFormat, width, height, hashMap);
        } catch (WriterException e) {
            System.out.println("图片生成失败");
            e.printStackTrace();
        }
        return encode;
    }

    /**
     * 描述：输出生成的图片
     *
     * @param encode  生成的二维码
     * @param format  二维码类型（.png, .jpg,）
     * @param outPath 输出路径
     */
    public boolean outMatrix(BitMatrix encode, String format, String outPath) {

        if (outPath == null || "".equals(outPath)) {
            outPath = desPath;
        }

        //输出路径
        Path paint = new File(outPath + dateFormat + "." + format).toPath();

        try {
            //图片输出
            MatrixToImageWriter.writeToPath(encode, format, paint);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 描述：存入剪贴板
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

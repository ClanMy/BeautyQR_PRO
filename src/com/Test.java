package com;

import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.qrcode.qrCode;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.WritableImage;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.awt.image.BufferedImage;

public class Test {

    public static void main(String[] args) {

        int width = 300;
        int height = 300;

        String format = "png";

        String content = "宗国闯";

        qrCode qrCode = new qrCode();
        //生成二维码
        BitMatrix bitMatrix = qrCode.generate(content, width, height);
        //输出二维码
        boolean b = qrCode.outMatrix(bitMatrix, format, null);
        System.out.println(b);

//        MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
//        //先转换为BufferedImage
//        BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix,matrixToImageConfig);
//        //将转换BufferedImage类型的图片为可写的image
//        WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
        //复制到剪切板
//        qrCode.clipBoard(writableImage);



    }

}

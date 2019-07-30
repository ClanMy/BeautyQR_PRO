package com.sample.contro;

import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.qrcode.qrCode;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;

public class Controller {

    private Stage stage = new Stage();

    //生成的二维码
    private BitMatrix bitMatrix;

    public void mainStage() {

        int width = 300;
        int height = 300;

        String format = "png";

        String content = "www.baidu.com";

        qrCode qrCode = new qrCode();

        Label label = new Label();
        Button generate_button = new Button("生成二维码");
        Button outMatrix_button = new Button("保存到桌面");

        HBox hBox = new HBox(10, generate_button, outMatrix_button, label);

        ImageView imageView = new ImageView();

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(hBox);
        borderPane.setCenter(imageView);
        Scene scene = new Scene(borderPane, 500, 700);
        stage.setScene(scene);
        stage.setTitle("二维码生成工具");
        stage.show();

        //点击生成二维码
        generate_button.setOnAction(event -> {

            //生成二维码
            bitMatrix = qrCode.generate(content, width, height);

            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
            //先转换为BufferedImage
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
            //将转换BufferedImage类型的图片为可写的image
            WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);

            //显示
            imageView.setImage(writableImage);


        });

        //点击输出生成二维码
        outMatrix_button.setOnAction(event -> {
            //输出二维码
            boolean b = qrCode.outMatrix(bitMatrix, format, null);

            if (bitMatrix != null) {

                if (b) {
                    label.setText("输出成功");
                } else {
                    label.setText("输出失败");

                }
            } else {
                label.setText("没有二维码");
            }
        });
    }
}

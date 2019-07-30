package com.qrcodes;

public class QrCodeTest {


    public static void main(String[] args) throws Exception {

        // 存放在二维码中的内容
        String text = "www.baidu.com";
        // 嵌入二维码的图片路径
        String imgPath = "D:/qrCode/logo.png";
        // 生成的二维码的路径及名称
        String destPath = "D:/qrCode/jam.png";
        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
//        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
//        // 打印出解析出的内容
        System.out.println(str);

    }
}

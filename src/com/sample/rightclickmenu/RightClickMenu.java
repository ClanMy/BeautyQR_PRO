package com.sample.rightclickmenu;

import com.qrcode.QrCode;
import com.sample.attributeobject.AttributePoJo;

import com.sample.attributeobject.FxAttributePoJo;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * 右键菜单
 */
public class RightClickMenu {
    //二维码全局属性
    private static AttributePoJo attribute = AttributePoJo.getAttribute();
    //FX全局组件
    private static FxAttributePoJo fxAttribute = FxAttributePoJo.getFxAttributePoJo();
    //二维码主类
    private static QrCode qrCode = new QrCode();

    public static void rightkMenu(Control control) {

        MenuItem saveAs = new MenuItem("另存为");
        MenuItem saveToYourDesktop = new MenuItem("保存到桌面");
        MenuItem menuItem3 = new MenuItem("复制到粘贴板");
        MenuItem menuItem4 = new MenuItem("待开发");

        //右键按钮菜单栏
        ContextMenu contextMenu = new ContextMenu(saveAs, saveToYourDesktop, menuItem3, menuItem4);
        //将右键菜单和要在右键点击的位置绑定
        control.setContextMenu(contextMenu);
        //另存为
        saveAs.setOnAction(event -> {
            //文件选择器
            FileChooser fileChooser = new FileChooser();
            //设置打开窗口的标题 （如果不设置默认显示“另存为”或打“开”）
            fileChooser.setTitle("保存文件");
            //保存文件的默认名
            fileChooser.setInitialFileName(attribute.getImageName());
            //打开窗口默认路径
            fileChooser.setInitialDirectory(new File("D:" + File.separator + "Programming" + File.separator + "api"));
            //上传文件类型筛选
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("图片类型", "*.png", "*.jpg","*.gif"),
                    new FileChooser.ExtensionFilter("所有", "*.*")
            );
            //打开一个窗口(选择文件后返回文件的路径)(单选，只能选择一个文件)
            File file = fileChooser.showSaveDialog(new Stage());
            //判断非空否则会报错
            if (file != null) {
                fxAttribute.setOutPath(file.getAbsolutePath());
            }
            //输出
            qrCode.outMatrix();
        });
        //保存到桌面
        saveToYourDesktop.setOnAction(event -> qrCode.outMatrix());
        //复制到粘贴板
        menuItem3.setOnAction(event -> System.out.println("复制到粘贴板"));


    }
}

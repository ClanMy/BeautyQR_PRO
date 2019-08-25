package com.sample.contro;

import com.sample.attributepojo.FxAttributePoJo;
import com.sample.contro.content.Content;
import com.sample.contro.top.Top;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller {

    //FX全局组件
    private FxAttributePoJo fxAttribute = FxAttributePoJo.getFxAttributePoJo();

    public void mainStage(Stage stage) {
        //窗口传入FX全局组件
        fxAttribute.setStage(stage);
        //主布局
        BorderPane borderPane = new BorderPane();
        //顶部菜单栏
        borderPane.setTop(new Top().getNode());
        //中部内容
        borderPane.setCenter(new Content().getNode());
        //场景
        Scene scene = new Scene(borderPane, 500, 800);
        scene.getStylesheets().add("com/sample/css/PointStage.css");
        stage.getIcons().add(new Image("com/sample/image/icon.png"));
        stage.setScene(scene);
        stage.setTitle("二维码生成工具");
        //设置窗口不可拉伸
//        stage.setResizable(false);
        stage.show();
    }
}

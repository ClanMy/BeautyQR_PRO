package com.sample.contro.top;

import com.sample.contro.FXBeas;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class Top implements FXBeas {

    @Override
    public Node getNode() {

        //临时状态栏提示
        Label labeltemp = new Label("菜单栏（待补充）");
        labeltemp.setFont(Font.font(20));
        HBox hBox_temp = new HBox(labeltemp);
        hBox_temp.setAlignment(Pos.CENTER);

        return hBox_temp;
    }




}

package com.sample.attributepojo;

import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 *FX全局组件
 */
public class FxAttributePoJo {

    /**
     * 主窗口
     */
    private Stage stage;
    /**
     * 提示信息
     */
    private Label tip_Label = new Label();

    private static FxAttributePoJo fxAttributePoJo = new FxAttributePoJo();

    public static FxAttributePoJo getFxAttributePoJo() {

        return fxAttributePoJo;
    }

    private FxAttributePoJo() {
    }


    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Label getTip_Label() {
        return tip_Label;
    }

    public void setTip_Label(Label tip_Label) {
        this.tip_Label = tip_Label;
    }
}

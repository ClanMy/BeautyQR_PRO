package com.sample.attributeobject;

import javafx.scene.control.Label;

/**
 *FX全局组件
 */
public class FxAttributePoJo {
    /**
     * 提示信息
     */
    private Label tip_Label = new Label();
    /**
     * 二维码输出路径
     */
    private String outPath;

    private static FxAttributePoJo fxAttributePoJo = new FxAttributePoJo();

    public static FxAttributePoJo getFxAttributePoJo() {

        return fxAttributePoJo;
    }

    private FxAttributePoJo() {
    }


    public String getOutPath() {
        return outPath;
    }

    public void setOutPath(String outPath) {
        this.outPath = outPath;
    }

    public Label getTip_Label() {
        return tip_Label;
    }

    public void setTip_Label(Label tip_Label) {
        this.tip_Label = tip_Label;
    }
}

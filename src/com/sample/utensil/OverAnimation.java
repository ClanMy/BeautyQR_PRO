package com.sample.utensil;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class OverAnimation {
    //创建一个动画
    private static FadeTransition fadeTransition = new FadeTransition();
    //渐隐
    public static void fadingNode(Node node) {
        //添加动画的对象(因为所有的组件都是继承Node类)
        fadeTransition.setNode(node);
        //持续时间
        fadeTransition.setDuration(Duration.seconds(0.3));
        //开始值
        fadeTransition.setFromValue(0);
        //结束值
        fadeTransition.setToValue(1);
        //开始动画
        fadeTransition.play();
    }


}

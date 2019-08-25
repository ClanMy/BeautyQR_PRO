package com.sample.contro.tippopup;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * 异常提示
 */

public class PopUps {
    /**
     * @param promptTitle   提示标题
     * @param promptContent 提示内容
     */
    public static void pointa(String promptTitle, String promptContent) {
        //提示弹窗
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(promptTitle);
        alert.setHeaderText(promptContent);
        Optional<ButtonType> buttonType = alert.showAndWait();
    }
}

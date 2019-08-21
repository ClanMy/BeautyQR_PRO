package com.sample.contro.content;

import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.qrcode.QrCode;
import com.sample.attributeobject.AttributePoJo;
import com.sample.attributeobject.FxAttributePoJo;
import com.sample.contro.FXBeas;
import com.sample.rightclickmenu.RightClickMenu;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Content implements FXBeas {

    //图片全局属性对象
    private AttributePoJo attribute = AttributePoJo.getAttribute();
    //FX全局组件
    private FxAttributePoJo fxAttribute = FxAttributePoJo.getFxAttributePoJo();
    //提示信息
    private Label tip_label = fxAttribute.getTip_Label();
    //二维码主类
    private QrCode qrCode = new QrCode();
    //单选按钮
    private CheckBox OpenOrClose = new CheckBox("是否约束宽高");
    //显示二维码
    private ImageView imageView = new ImageView();
    //使用Label可以使用右键菜单
    private Label label_image = new Label("二维码图片", imageView);
    //图片内容
    private TextArea contentTextArea = new TextArea();
    //获取宽
    private TextField qrCode_Width = new TextField();
    //获取高
    private TextField qrCode_Height = new TextField();
    //图片格式下拉列表
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();


    @Override
    public Node getNode() {
        //设置默认图片名称（日期个格式）
        attribute.setImageName(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S").format(new Date()));
        //默认宽高
        qrCode_Width.setText("300");
        qrCode_Height.setText("300");
        //提示信息
        tip_label.setFont(Font.font(20));
        //图片Label
        label_image.setAlignment(Pos.CENTER);
        label_image.setFont(Font.font(20));
        //生成按钮
        HBox button_hBox = new HBox(10, generate(), outMatrix());
        button_hBox.setAlignment(Pos.TOP_CENTER);
        //右键菜单（传入要点击右键显示菜单的ImageView）
        RightClickMenu.rightkMenu(label_image);
        VBox vBox_center = new VBox(20, qrCodeAttribute(), button_hBox, tip_label, contentTextArea, label_image);
        vBox_center.setAlignment(Pos.TOP_CENTER);
        vBox_center.setPadding(new Insets(20));
        return vBox_center;
    }

    //二维码属性
    private GridPane qrCodeAttribute() {
        //多选框初始化默认选中状态
        OpenOrClose.setSelected(true);
        //监听事件判断是否绑定
        OpenOrClose.selectedProperty().addListener((observable, oldValue, newValue) -> {
            //当选择框为true时绑定
            if (newValue) {
                qrCode_Height.textProperty().bindBidirectional(qrCode_Width.textProperty());
            } else {
                qrCode_Height.textProperty().unbindBidirectional(qrCode_Width.textProperty());
            }
        });
        //图片格式
        choiceBox.getItems().addAll("PNG", "JPG", "GIF");
        //默认选择第一个
        choiceBox.getSelectionModel().selectFirst();
        //选择图片格式(并设置为小写)
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> attribute.setFormat(newValue.toLowerCase()));
        //网格布局
        GridPane gridPane = new GridPane();
        //子组件对齐方式
        gridPane.setAlignment(Pos.CENTER);
        //水平间距
        gridPane.setHgap(10);
        //垂直间距
        gridPane.setVgap(10);
        //布局
        gridPane.add(new Label("宽："), 0, 0);
        gridPane.add(qrCode_Width, 1, 0);
        //第三位，第四位，合并单元格
        gridPane.add(OpenOrClose, 2, 0, 2, 1);
        gridPane.add(new Label("高："), 0, 1);
        gridPane.add(qrCode_Height, 1, 1);
        gridPane.add(new Label("选择图片格式："), 2, 1);
        gridPane.add(choiceBox, 3, 1);

        return gridPane;
    }

    //生成二维码
    private Button generate() {
        Button generate_button = new Button("生成二维码");
        //点击生成二维码
        generate_button.setOnAction(event -> {
            //图片Label设置阴影（点击时添加样式）
            label_image.getStyleClass().addAll("menuBox");
            //去除label_image提示文字
            label_image.setText(null);

            String widthText = qrCode_Width.getText();
            String heightText = qrCode_Height.getText();
            String content = contentTextArea.getText();

            if (widthText != null) {
                attribute.setWidth(Integer.valueOf(widthText));
            } else {
                tip_label.setText("请给宽度填写数字类型");
                return;
            }
            if (heightText != null) {
                attribute.setHeight(Integer.valueOf(heightText));
            } else {
                tip_label.setText("请给高度填写数字类型");
                return;
            }
            if ("".equals(content)) {
                tip_label.setText("内容不能为空");
                return;
            } else {
                attribute.setContent(content);
            }
            //获取生成的二维码
            BitMatrix bitMatrix = qrCode.generate();
            //将生成的二维码放入对象中
            attribute.setBitMatrix(bitMatrix);
            if (bitMatrix != null) tip_label.setText("生成成功");
            MatrixToImageConfig matrixToImageConfig = new MatrixToImageConfig(0xFF000001, 0xFFFFFFFF);
            //先转换为BufferedImage
            assert bitMatrix != null;
            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix, matrixToImageConfig);
            //将转换BufferedImage类型的图片为可写的image
            WritableImage writableImage = SwingFXUtils.toFXImage(bufferedImage, null);
            //显示
            imageView.setImage(writableImage);
        });
        return generate_button;
    }

    //清除图片
    private Button outMatrix() {
        Button outMatrix_button = new Button("清除图片");
        //点击输出生成二维码
        outMatrix_button.setOnAction(event -> imageView.setImage(null));
        return outMatrix_button;
    }
}

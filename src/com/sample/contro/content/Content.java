package com.sample.contro.content;

import com.qrcode.QrCode;
import com.sample.attributepojo.AttributePoJo;
import com.sample.attributepojo.FxAttributePoJo;
import com.sample.contro.FXBeas;
import com.sample.contro.tippopup.PopUps;
import com.sample.rightclickmenu.RightClickMenu;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
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
    private CheckBox OpenOrClose = new CheckBox("   是否约束宽高");
    //显示二维码
    private ImageView imageView = new ImageView();
    //使用Label可以使用右键菜单
    private Label label_image = new Label("二维码图片", imageView);
    //显示LOGO图片
    private ImageView logo_imageView = new ImageView();
    //LOGO路径
    private TextField logoPathField = new TextField();
    //用于暂时预览LOGO
//    private AnchorPane anchorPane = new AnchorPane();
    //图片内容
    private TextArea contentTextArea = new TextArea("www.baidu.com");
    //获取宽
    private TextField qrCode_Width = new TextField();
    //获取高
    private TextField qrCode_Height = new TextField();

    //图片格式下拉列表
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();
    //图片格式数组("PNG", "JPG", "GIF")
    private String[] formatArray = attribute.getFormatArray();

    @Override
    public Node getNode() {
        //设置二维码和Logo位置自适应
//        adaptive();

        //鼠标悬浮提示
        Tooltip tooltip = new Tooltip();
        tooltip.setStyle("-fx-background-color:#ffffff");
        Tooltip.install(logoPathField, tooltip);
        Label labellogo = new Label("Logo图片");
        labellogo.setFont(Font.font(14));
        labellogo.setStyle("-fx-text-fill: #000000");
        tooltip.setGraphic(new VBox(5, labellogo, logo_imageView));
        //设置显示内容字体大小
        contentTextArea.setFont(Font.font(16));
        //默认宽高
        qrCode_Width.setText("300");
        qrCode_Height.setText("300");
        //提示信息
        tip_label.setFont(Font.font(20));
        //图片Label
        label_image.setAlignment(Pos.CENTER);
        label_image.setFont(Font.font(18));
        //生成,解析，清理 ，按钮
        HBox button_hBox = new HBox(10, generate(), analyzeQRCode(), outMatrix());
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
        //默认绑定
        qrCode_Height.textProperty().bindBidirectional(qrCode_Width.textProperty());
        //监听事件判断是否绑定
        OpenOrClose.selectedProperty().addListener((observable, oldValue, newValue) -> {
            //当选择框为fleas时解绑
            if (newValue) {
                qrCode_Height.textProperty().bindBidirectional(qrCode_Width.textProperty());
            } else {
                qrCode_Height.textProperty().unbindBidirectional(qrCode_Width.textProperty());
            }
        });
        //图片格式下拉列表
        choiceBox.setPrefWidth(100);
        //图片格式
        ObservableList<String> choiceBox_items = choiceBox.getItems();
        //遍历添加到下拉框中
        for (String s : formatArray) {
            choiceBox_items.add(s.toUpperCase());
        }
        //默认选择第一个
        choiceBox.getSelectionModel().selectFirst();
        //选择图片格式(并设置为小写)
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            //图片格式转小写
            String toLowerCase = newValue.toLowerCase();
            //放入全局属性中
            attribute.setFormat(toLowerCase);
            //重新排序图片格式数组
            formatArray[0] = toLowerCase;
        });
        //前景色
        ColorPicker foreColor = new ColorPicker(Color.valueOf("#000000"));
        //绑定属性
        attribute.setForegroundColor(foreColor.valueProperty());
        //背景色
        ColorPicker backColor = new ColorPicker(Color.valueOf("#ffffff"));
        //绑定属性
        attribute.setBackgroundColor(backColor.valueProperty());
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

        gridPane.add(new Label("前景色："), 2, 0);
        gridPane.add(foreColor, 3, 0);

        gridPane.add(new Label("高："), 0, 1);
        gridPane.add(qrCode_Height, 1, 1);

        gridPane.add(new Label("背景色："), 2, 1);
        gridPane.add(backColor, 3, 1);

        //第三位参数，第四位参数，合并单元格
        gridPane.add(OpenOrClose, 0, 2, 2, 1);
        gridPane.add(new Label("图片格式"), 2, 2);
        gridPane.add(choiceBox, 3, 2);

        gridPane.add(logoPathField, 0, 3, 3, 1);
        gridPane.add(getLogo(), 3, 3);

//        gridPane.add(logoScale(), 0, 4, 4, 1);

        return gridPane;
    }

    //选取Logo
    private Button getLogo() {
        Button logo_button = new Button("选择LOGO");
        logo_button.setPrefWidth(100);
        logo_button.setOnAction(event -> {
            //文件选择器
            FileChooser fileChooser = new FileChooser();
            //设置打开窗口的标题 （如果不设置默认显示“另存为”或打“开”）
            fileChooser.setTitle("单选文件asd");
            //打开窗口默认路径
//        fileChooser.setInitialDirectory(new File(""));
            //上传文件类型筛选
            String FormatPrefix = "*.";
            //过滤选择
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("图片类型", FormatPrefix + formatArray[0], FormatPrefix + formatArray[1], FormatPrefix + formatArray[2]),
                    new FileChooser.ExtensionFilter("所有", "*.*")
            );
            //打开一个窗口(选择文件后返回文件的路径)(单选，只能选择一个文件)
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                String logoP = file.toString();
                logoPathField.setText(logoP);
                attribute.setLogoPath(logoP);
                logo_imageView.setImage(new Image("file:" + logoP));
            }
        });
        return logo_button;
    }

    //logo比例设置
//    private Slider logoScale() {
//        //最大值，最小值，默认当前位置
//        Slider slider = new Slider(1, 70, 20);
//        //设置显示带数字的刻度
//        slider.setShowTickLabels(true);
//        //设置显示带数字的刻度
//        slider.setShowTickMarks(true);
//        //刻度密度
//        slider.setMajorTickUnit(5);
//
//        //绑定宽高
//        DoubleProperty doubleProperty = slider.valueProperty();
//        logo_imageView.fitWidthProperty().bindBidirectional(doubleProperty);
//        logo_imageView.fitHeightProperty().bindBidirectional(doubleProperty);
//
//
//        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
//            System.out.println(newValue);
//        });
//
//        return slider;
//    }

    //生成二维码
    private Button generate() {
        Button generate_button = new Button("生成");
        //点击生成二维码
        generate_button.setOnAction(event -> {
            //图片Label设置阴影（点击时添加样式）
            label_image.getStyleClass().addAll("menuBox");
            //去除label_image提示文字
            label_image.setText(null);
            //设置二维码显示宽高  跟生成宽高没关系
            imageView.setFitWidth(300);
            imageView.setPreserveRatio(true);
            //设置默认图片名称（日期个格式）
            attribute.setImageName(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-S").format(new Date()));
            //宽
            String widthText = qrCode_Width.getText();
            //高
            String heightText = qrCode_Height.getText();
            //内容
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
            if (!"".equals(content)) {
                attribute.setContent(content);
            } else {
                tip_label.setText("内容不能为空");
                return;
            }
            //获取图片格式如果为空就设置默认值为.png
            if (attribute.getFormat() == null) {
                attribute.setFormat(formatArray[0]);
            }
            //获取生成的二维码
            WritableImage writableImage = qrCode.generate();
            if (writableImage != null) {
                tip_label.setText("生成成功");
                //将生成的二维码放入对象中
                attribute.setWritableImage(writableImage);
                //显示
                imageView.setImage(writableImage);
            } else {
                tip_label.setText("图片为空");
            }
        });
        return generate_button;
    }

    //解析二维码
    private Button analyzeQRCode() {
        Button analyzeQRCode_button = new Button("解析");
        analyzeQRCode_button.setOnAction(event -> PopUps.pointa("待开发", "解析二维码功能待开发中"));
        return analyzeQRCode_button;
    }

    //清除图片
    private Button outMatrix() {
        Button outMatrix_button = new Button("清除");
        //点击输出生成二维码
        outMatrix_button.setOnAction(event -> imageView.setImage(null));
        return outMatrix_button;
    }


    /*
     * 图片自适应
     *
     * 因为图片设置宽高之前已经获取了Label的宽高，所以设值暂时有问题暂时去掉该功能
     *
     * */
//    private void adaptive() {
//        //主窗口
//        Stage stage = fxAttribute.getStage();
//
//        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
//            double stageWidth = newValue.doubleValue();
//            double imageWidth = label_image.getWidth();
//            double logWidth = logo_imageView.getFitWidth();
//
//            System.out.println(stageWidth +"~~~"+imageWidth+"~~~"+logWidth);
//
//            AnchorPane.setLeftAnchor(label_image, (stageWidth / 2 - imageWidth));
//            AnchorPane.setLeftAnchor(logo_imageView, (stageWidth - logWidth) / 2);
//        });
//    }

}

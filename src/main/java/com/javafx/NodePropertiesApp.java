package com.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 矩形图像分布，界面加载数量在一万以上，就开始卡
 */
public class NodePropertiesApp extends Application {

    private Rectangle rectA;
    private Rectangle rectB;
    private Rectangle rectC;
    static Background bgYellow = new Background(new BackgroundFill(Paint.valueOf("#e3d81c"), CornerRadii.EMPTY, Insets.EMPTY));
    static Background bgRed = new Background(new BackgroundFill(Paint.valueOf("#ff4500"), CornerRadii.EMPTY, Insets.EMPTY));
    static Background bgBlue = new Background(new BackgroundFill(Paint.valueOf("#472EF5"), CornerRadii.EMPTY, Insets.EMPTY));
    static Background bgGreen = new Background(new BackgroundFill(Paint.valueOf("#009a44"), CornerRadii.EMPTY, Insets.EMPTY));
    static List<List<Label>> data = new ArrayList<>();

    static {

        for (int i = 0; i < 300; i++) {
            List<Label> temp1 = new ArrayList<>();
            data.add(temp1);
            for (int j = 0; j < 250; j++) {
                Label a = new Label("a");
                double width = 5;

                a.setLayoutX(width * i);
                a.setLayoutY(width * j);

                a.setPrefWidth(width);
                a.setPrefHeight(width);
                a.setBackground(bgRed);
                a.setAlignment(Pos.CENTER);
                a.setFont(Font.font(width));
                temp1.add(a);
//                list.add(a);

            }
        }
    }

    public Parent createContent() {
        System.out.println(LocalDateTime.now());
        List<Label> list = new ArrayList<>(75000);
        for (int i = 0; i < 300; i++) {
            for (int j = 0; j < 250; j++) {
                Label label = data.get(i).get(j);
                label.setText("b");
                list.add(label);
            }
        }
//        Label b = new Label("b");
//        b.setLayoutX(20);
//        b.setLayoutY(10);
//        width = 10;
//        b.setPrefWidth(width);
//        b.setPrefHeight(width);
//        b.setBackground(bgBlue);
//        b.setAlignment(Pos.CENTER);
//        b.setFont(Font.font(width));

        //X position of node = X + LayoutX + TranslateX
//        rectA = new Rectangle(50, 50, Color.LIGHTSALMON);
//        //set position of node temporary (can be changed after)
//        rectA.setTranslateX(10);
//
//        rectB = new Rectangle(50, 50, Color.LIGHTGREEN);
//        //set position of node when addinf to some layout
//        rectB.setLayoutX(20);
//        rectB.setLayoutY(10);
//
//        rectC = new Rectangle(50, 50, Color.DODGERBLUE);
//        //last posibility of setting X position of node
//        rectC.setX(30);
//        rectC.setY(20);
//        //opacity of node can be set
//        rectC.setOpacity(0.8);
//        rectC.setAccessibleText("ssss");
        Label[] temp = new Label[list.size()];
        list.toArray(temp);
        System.out.println(LocalDateTime.now());
        Pane root = new Pane(temp);
        root.setOnScroll(
                new EventHandler<ScrollEvent>() {//鼠标滚轮放大缩小
                    @Override
                    public void handle(ScrollEvent event) {
                        double zoomNumber = 2;
                        double deltaY = event.getDeltaY();

                        if (deltaY < 0) {
                            zoomNumber = 0.95;
                        }
                        root.setScaleX(root.getScaleX() * zoomNumber);
                        root.setScaleY(root.getScaleY() * zoomNumber);
                        event.consume();
                    }
                }
        );

        root.setPrefSize(130, 100);
        root.setMinSize(130, 100);
        root.setMaxSize(130, 100);
        System.out.println(LocalDateTime.now());
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        System.out.println(LocalDateTime.now() + "：数据加载完毕");
        primaryStage.setScene(scene);
        System.out.println(LocalDateTime.now() + "：准备进行页面展示");
        primaryStage.show();
        System.out.println(LocalDateTime.now() + "：开始展示页面");
    }

    /**
     * Java main for when running without JavaFX launcher
     */
    public static void main(String[] args) {
        launch(args);
    }
}

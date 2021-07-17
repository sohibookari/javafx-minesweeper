package org.kiteki.minesweeper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Minesweeper extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader rootLoader = new FXMLLoader();
        FXMLLoader configLoader = new FXMLLoader();
        rootLoader.setLocation(getClass().getResource("/MainPane.fxml"));
        configLoader.setLocation(getClass().getResource("/SettingPane.fxml"));
        Parent config = configLoader.load();
        Scene configScene = new Scene(config);

        SettingPaneController settingPaneController = configLoader.getController();
        settingPaneController.setPrimaryStage(primaryStage);
        settingPaneController.setRootLoader(rootLoader);

        primaryStage.setTitle("扫雷配置");
        primaryStage.setScene(configScene);
        primaryStage.show();
    }
}

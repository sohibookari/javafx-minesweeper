package org.sohibookari.minesweeper;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.sohibookari.minesweeper.module.Constraints;
import org.sohibookari.minesweeper.module.cell.Coords;
import org.sohibookari.minesweeper.module.cell.MineCell;
import org.sohibookari.minesweeper.module.field.MineField;


public class Minesweeper extends Application {

    private MineField mineField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MainPane.fxml"));

        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

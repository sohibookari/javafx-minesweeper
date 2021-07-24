package org.kiteki.minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.kiteki.minesweeper.module.Constraints;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingPaneController implements Initializable {
    private Constraints constraints = new Constraints();
    private int ix;
    private int iy;
    private int im;
    FXMLLoader rootLoader;
    Stage primaryStage;

    @FXML
    public TextField xField;

    @FXML
    public TextField yField;

    @FXML
    public TextField mField;

    @FXML
    public void handleXTyping(KeyEvent e) {
        ix = Integer.parseInt(xField.getText());
        im = (int) (ix * iy * 0.15);
        mField.setText(im + "");
    }

    @FXML
    public void handleYTyping(KeyEvent e) {
        iy = Integer.parseInt(yField.getText());
        im = (int) (ix * iy * 0.15);
        mField.setText(im + "");
    }

    @FXML
    public void handleSubmit(ActionEvent e) {
        constraints.setFieldWidth(ix);
        constraints.setFieldHeight(iy);
        constraints.setTotalMines(im);
        constraints.writeData();
        Parent root = null;
        try {
            root = rootLoader.load();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        primaryStage.setTitle("扫雷");
        primaryStage.setScene(new Scene(root));
    }

    @FXML
    public void handleCancel(ActionEvent e) {

    }

    public void setRootLoader(FXMLLoader rootLoader) {
        this.rootLoader = rootLoader;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ix = constraints.getFieldWidth();
        iy = constraints.getFieldHeight();
        im = (int) (ix * iy * 0.15);
        xField.setText(ix + "");
        yField.setText(iy + "");
        mField.setText(im + "");
    }
}

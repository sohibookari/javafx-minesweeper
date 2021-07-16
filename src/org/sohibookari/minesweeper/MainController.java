package org.sohibookari.minesweeper;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import org.sohibookari.minesweeper.module.Constraints;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @FXML
    public Label statusLabel;

    @FXML
    public GridPane fieldPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldPane.setPrefWidth(new Constraints().getFieldPaneWidth());
        fieldPane.setPrefHeight(new Constraints().getFieldPaneHeight());
        fieldPane.setHgap(new Constraints().getGridWidth());
        fieldPane.setVgap(new Constraints().getGridHeight());
    }
}
package org.sohibookari.minesweeper;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import org.sohibookari.minesweeper.module.Constraints;
import org.sohibookari.minesweeper.module.cell.CellStatus;
import org.sohibookari.minesweeper.module.cell.Coords;
import org.sohibookari.minesweeper.module.cell.MineCell;
import org.sohibookari.minesweeper.module.cell.MineCellRender;
import org.sohibookari.minesweeper.module.field.MineField;

import java.net.URL;
import java.util.ResourceBundle;


class MineFieldController {
    private MineField mineField;
    private MineCellRender[] mineCellRender;

    public MineFieldController() {
        mineField = new MineField();
        mineCellRender = new MineCellRender[new Constraints().getMaxIndex() + 1];
        mineField.initialize();

        for (int i = 0; i <= new Constraints().getMaxIndex(); i++) {
            MineCell mineCell = mineField.getCellByCoords(new Coords(i));
            mineCellRender[i] = new MineCellRender(mineCell);
        }
    }

    void updateField() {
        for (MineCellRender mcr : mineCellRender) {
            mcr.update();
        }
    }

    void setEventHandle() {
        for (MineCellRender mcr : mineCellRender) {
            mcr.getStackPane().setOnMouseClicked(mouseEvent -> {
                MineCell mc = mineField.getCellByCoords(mcr.getCoords());
                MouseButton mb = mouseEvent.getButton();
                // one left click.
                if (mb == MouseButton.PRIMARY) {
                    if (mc.getStatus() == CellStatus.EMPTY) {
                        mc.reveal();
                    }
                    else if (mc.getStatus() == CellStatus.MINED) {
                        mc.expose();
                        mineField.revealAll();
                        updateField();
                    }
                }

                else if (mb == MouseButton.SECONDARY) {
                    mc.flag();
                }

                mcr.update();
            });
        }
    }

    void setFieldPane(GridPane fieldPane) {
        for (MineCellRender mcr : mineCellRender) {
            fieldPane.add(mcr.getStackPane(), mcr.getCoords().getX(), mcr.getCoords().getY());
        }
    }
}


public class MainController implements Initializable {
    @FXML
    public Label statusLabel;

    @FXML
    public GridPane fieldPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize basic field pane.
        fieldPane.setPrefWidth(new Constraints().getFieldPaneWidth());
        fieldPane.setPrefHeight(new Constraints().getFieldPaneHeight());
        fieldPane.setHgap(2);
        fieldPane.setVgap(2);
        fieldPane.setPadding(new Insets(10));

        MineFieldController mineFieldController = new MineFieldController();
        mineFieldController.setEventHandle();
        mineFieldController.setFieldPane(fieldPane);
    }
}
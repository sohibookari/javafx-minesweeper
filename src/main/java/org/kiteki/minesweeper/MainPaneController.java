package org.kiteki.minesweeper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.kiteki.minesweeper.module.Constraints;
import org.kiteki.minesweeper.module.cell.CellStatus;
import org.kiteki.minesweeper.module.cell.MineCell;
import org.kiteki.minesweeper.module.cell.MineCellRender;
import org.kiteki.minesweeper.module.field.MineField;
import org.kiteki.minesweeper.module.cell.Coords;

import java.net.URL;
import java.util.ResourceBundle;


class MineFieldController {
    private MineField mineField;
    private MineCellRender[] mineCellRender;
    private Label label;

    private final int[] offsetY = {-1, 0, 1};
    private final int[] offsetX = {-1, 0, 1};

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

     void dfsAroundEmpty(int x, int y, Boolean f) {
        if (x < 0 || x > new Constraints().getFieldWidth() - 1 ||
                y < 0 || y > new Constraints().getFieldHeight() - 1) return;

        MineCell mc = mineField.getCellByCoords(new Coords(x, y));

        if (mc.getStatus() != CellStatus.EMPTY && !f) return;
        if (mc.getAroundMines() != 0) {
            mc.reveal();
            return;
        }

        try {
            mc.reveal();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }

        for (int oy : offsetY) {
            for (int ox : offsetX) {
                int newY = y + oy;
                int newX = x + ox;
                dfsAroundEmpty(newX, newY, false);
            }
        }
    }

    boolean checkIfWin() {
        return mineField.getFlaggedMine() == new Constraints().getTotalMines();
    }

    void revealEventHandler(MineCell mc) {
            if (mc.getStatus() == CellStatus.EMPTY) {
                mc.reveal();
                if (mc.getAroundMines() == 0) {
                    dfsAroundEmpty(mc.getCoords().getX(), mc.getCoords().getY(), true);
                    updateField();
                }
            }
            else if (mc.getStatus() == CellStatus.MINED) {
                mc.expose();
                mineField.revealAll();
                updateField();
                label.setText("Game Over");
            }
    }

    void flagEventHandler(MineCell mc) {
        if (mc.getStatus() == CellStatus.EMPTY || mc.getStatus() == CellStatus.MINED) {
            mc.flag();
            if (mc.getStatus() == CellStatus.MINED) {
                mineField.flagAMine();
                if (checkIfWin()) {
                    mineField.revealAll();
                    updateField();
                    label.setText("You WIN");
                }
            }
        }
        else if (mc.getStatus() == CellStatus.EMPTY_FLAGGED) {
            mc.empty();
        }
        else if (mc.getStatus() == CellStatus.MINED_FLAGGED) {
            mineField.unFlagAMine();
            mc.mine();
        }
    }

    void setEventHandler() {
        for (MineCellRender mcr : mineCellRender) {
            mcr.getStackPane().setOnMouseClicked(mouseEvent -> {
                MineCell mc = mineField.getCellByCoords(mcr.getCoords());
                MouseButton mb = mouseEvent.getButton();
                // one left click.
                if (mb == MouseButton.PRIMARY) revealEventHandler(mc);
                // one right click.
                else if (mb == MouseButton.SECONDARY) flagEventHandler(mc);

                mcr.update();
            });
        }
    }

    void fillFieldPane(GridPane fieldPane) {
        for (MineCellRender mcr : mineCellRender) {
            fieldPane.add(mcr.getStackPane(), mcr.getCoords().getX(), mcr.getCoords().getY());
        }
    }

    void setLabel(Label label) {
        this.label = label;
    }
}


public class MainPaneController implements Initializable {
    private Stage primaryStage;
    private Scene settingScene;

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
        mineFieldController.setEventHandler();
        mineFieldController.fillFieldPane(fieldPane);
        mineFieldController.setLabel(statusLabel);
    }
}
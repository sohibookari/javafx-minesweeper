package org.sohibookari.minesweeper.module.cell;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import org.sohibookari.minesweeper.module.Constraints;


public class MineCellRender {
    private StackPane stackPane;
    private Rectangle rect;
    private MineCell mineCell;
    private CellStatus status;

    public MineCellRender(MineCell mineCell) {
        this.mineCell = mineCell;

        rect = new Rectangle();
        stackPane = new StackPane();
        rect.setWidth(new Constraints().getGridWidth());
        rect.setHeight(new Constraints().getGridHeight());

        update();
    }

    public void update() {
        if (status == mineCell.getStatus()) return;
        status = mineCell.getStatus();
        if (status == CellStatus.EMPTY_FLAGGED || status == CellStatus.MINED_FLAGGED) {
            makeFlagged();
        }
        else if (status == CellStatus.EMPTY_REVEALED) {
            makeRevealed();
        }
        else if (status == CellStatus.MINED_EXPLODED) {
            makeExploded();
        }
        else {
            makeOriginal();
        }
    }

    public void makeOriginal() {
        stackPane.getChildren().clear();
        rect.setFill(Color.BLUEVIOLET);
        stackPane.getChildren().add(rect);
    }

    public void makeFlagged() {
        stackPane.getChildren().clear();
        rect.setFill(Color.YELLOW);
        stackPane.getChildren().add(rect);
    }

    public void makeExploded() {
        stackPane.getChildren().clear();
        rect.setFill(Color.RED);
        stackPane.getChildren().add(rect);
    }

    public void makeRevealed() {
        stackPane.getChildren().clear();
        rect.setFill(Color.GRAY);
        Text text = new Text("" + mineCell.getAroundMines());
        text.setStyle("-fx-fill: white; -fx-font-size: 14px;");
        stackPane.getChildren().addAll(rect, text);
    }

    public StackPane getStackPane() {
        return stackPane;
    }

    public Coords getCoords() { return mineCell.getCoords(); }

    public Color getFill() { return (Color) rect.getFill(); }
}

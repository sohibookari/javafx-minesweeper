package org.sohibookari.minesweeper.module.cell;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.sohibookari.minesweeper.module.Constraints;

public class MineCellView {
    private Rectangle rect;

    public MineCellView() {
        rect = new Rectangle();
        rect.setWidth(new Constraints().getGridWidth());
        rect.setHeight(new Constraints().getGridHeight());
        makeOriginal();
    }

    final void makeOriginal() {
        rect.setFill(Color.BLUE);
    }

    final void makeFlagged() {
        rect.setFill(Color.YELLOW);
    }

    final void makeExploded() {
        rect.setFill(Color.RED);
    }

    final void makeRevealed() {
        rect.setFill(Color.GRAY);
    }
}

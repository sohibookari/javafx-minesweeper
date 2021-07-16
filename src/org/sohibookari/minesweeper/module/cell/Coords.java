package org.sohibookari.minesweeper.module.cell;

import org.sohibookari.minesweeper.module.Constraints;

public class Coords {
    private int x;
    private int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coords(int cellId) {
        this.x = cellId % new Constraints().getFieldWidth();
        this.y = cellId / new Constraints().getFieldWidth();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCellId() {
        return new Constraints().getFieldWidth() * y + x;
    }
}

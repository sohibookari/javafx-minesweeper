package org.sohibookari.minesweeper.module;

public class Constraints {
    private int gridWidth = 10;
    private int gridHeight = 10;
    private int fieldWidth = 20;
    private int fieldHeight = 20;

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldPaneWidth() {
        return fieldWidth * gridWidth;
    }

    public int getFieldPaneHeight() {
        return fieldHeight * gridHeight;
    }

    public void setGridHeight(int gridHeight) {
        this.gridHeight = gridHeight;
    }

    public void setGridWidth(int gridWidth) {
        this.gridWidth = gridWidth;
    }

    public void setFieldHeight(int fieldHeight) {
        this.fieldHeight = fieldHeight;
    }

    public void setFieldWidth(int fieldWidth) {
        this.fieldWidth = fieldWidth;
    }


}

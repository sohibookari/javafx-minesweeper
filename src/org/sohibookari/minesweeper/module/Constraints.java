package org.sohibookari.minesweeper.module;

public class Constraints {
    private int gridWidth = 10;
    private int gridHeight = 10;
    private int fieldWidth = 20;
    private int fieldHeight = 20;
    private int totalMines = 60;

    public int getGridHeight() {
        return gridHeight;
    }

    public int getGridWidth() {
        return gridWidth;
    }

    public int getFieldHeight() { return fieldHeight; }

    public int getFieldWidth() { return fieldWidth; }

    public int getFieldPaneWidth() {
        return fieldWidth * gridWidth;
    }

    public int getFieldPaneHeight() {
        return fieldHeight * gridHeight;
    }

    public int getTotalMines() { return totalMines; }

    public int getMaxIndex() { return fieldWidth * fieldHeight - 1; }

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

    public void setTotalMines(int totalMines) { this.totalMines = totalMines; }
}

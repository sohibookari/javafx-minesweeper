package org.sohibookari.minesweeper.module.cell;


public class MineCell {
    private Coords coords;
    private CellStatus status;
    private int aroundMines;
    private String locationInfo;

    public MineCell(int cellId, CellStatus cellStatus) {
        coords = new Coords(cellId);
        status = cellStatus;
        aroundMines = 0;
        locationInfo = String.format("\n\tat Cell (%d,%d : %d)", coords.getX(), coords.getY(), coords.getCellId());
    }

    public void mine() throws UnsupportedOperationException {
        if (status == CellStatus.EMPTY) {
            status = CellStatus.MINED;
        }
        else {
            throw new UnsupportedOperationException("Can only mine the cell with EMPTY status." + locationInfo);
        }
    }

    public void expose() throws UnsupportedOperationException {
        if (status == CellStatus.MINED) {
            status = CellStatus.MINED_EXPLODED;
        }
        else {
            throw new UnsupportedOperationException("Can only explode the cell with MINED status." + locationInfo);
        }
    }

    public void flag() throws UnsupportedOperationException {
        if (status == CellStatus.EMPTY) {
            status = CellStatus.EMPTY_FLAGGED;
        }
        else if (status == CellStatus.MINED) {
            status= CellStatus.MINED_FLAGGED;
        }
        else {
            throw new UnsupportedOperationException("Can not flag a cell has revealed." + locationInfo);
        }
    }

    public void reveal() throws UnsupportedOperationException {
        if (status == CellStatus.EMPTY) {
            status = CellStatus.EMPTY_REVEALED;
        }
        else if (status == CellStatus.MINED) {
            expose();
        }
        else {
            throw new UnsupportedOperationException("Can not reveal a cell has marked." + locationInfo);
        }
    }

    public Coords getCoords() {
        return coords;
    }

    public CellStatus getStatus() { return status; }

    public int getAroundMines() { return aroundMines; }

    public void setAroundMines(int aroundMines) {
        this.aroundMines = aroundMines;
    }
}

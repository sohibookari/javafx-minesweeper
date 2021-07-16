package org.sohibookari.minesweeper.module.cell;


public class MineCellModel {
    private Coords coords;
    private CellStatus status;
    private int aroundMines;

    public MineCellModel(int cellId, CellStatus cellStatus) {
        coords = new Coords(cellId);
        status = cellStatus;
        aroundMines = 0;
    }

    final void expose() throws UnsupportedOperationException {
        if (status == CellStatus.MINED) {
            status = CellStatus.MINED_EXPLODED;
        }
        else {
            throw new UnsupportedOperationException("Can only explode a cell with MINED status.");
        }
    }

    final void flag() throws UnsupportedOperationException {
        if (status == CellStatus.EMPTY) {
            status = CellStatus.EMPTY_FLAGGED;
        }
        else if (status == CellStatus.MINED) {
            status= CellStatus.MINED_FLAGGED;
        }
        else {
            throw new UnsupportedOperationException("Can not flag a cell has revealed.");
        }
    }

    final void reveal() throws UnsupportedOperationException {
        if (status == CellStatus.EMPTY) {
            status = CellStatus.EMPTY_REVEALED;
        }
        else if (status == CellStatus.MINED) {
            expose();
        }
        else {
            throw new UnsupportedOperationException("Can not reveal a cell has marked.");
        }
    }

    Coords getCoords() {
        return coords;
    }
}

package org.sohibookari.minesweeper.module.field;

import org.sohibookari.minesweeper.module.Constraints;
import org.sohibookari.minesweeper.module.cell.CellStatus;
import org.sohibookari.minesweeper.module.cell.Coords;
import org.sohibookari.minesweeper.module.cell.MineCell;

public class MineField {
    private int fieldWidth;
    private int fieldHeight;
    private int flaggedMine;
    private MineCell[][] fieldData;

    private final Constraints constraints = new Constraints();
    private final int[] offsetY = {-1, 0, 1};
    private final int[] offsetX = {-1, 0, 1};

    public MineField() {
        fieldWidth = constraints.getFieldWidth();
        fieldHeight = constraints.getFieldHeight();
    }

    public void initialize() {

        fieldData = new MineCell[fieldHeight][fieldWidth];
        // initialize field data with EMPTY cells.
        {
            int index = 0;
            for (int i = 0; i < constraints.getFieldHeight(); i++) {
                for (int j = 0; j < constraints.getFieldWidth(); j++) {
                    fieldData[i][j] = new MineCell(index, CellStatus.EMPTY);
                    index += 1;
                }
            }
        }

        // initialize MINED cells in field data;
        for (int i = 0; i < constraints.getTotalMines(); i++) {
            try {
                double seed = Math.random() * constraints.getMaxIndex();
                Coords coords = new Coords((int) seed);
                fieldData[coords.getY()][coords.getX()].mine();
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
                i--;
            }
        }

        // initialize cells' around mines.
        for (int i = 0; i <= constraints.getMaxIndex(); i++) {
            MineCell mc = getCellByCoords(new Coords(i));
            int mineCount = 0;
            for (int oy : offsetY) {
                for (int ox : offsetX) {
                    if (oy == 0 && ox == 0) continue;
                    int newY = mc.getCoords().getY() + oy;
                    int newX = mc.getCoords().getX() + ox;
                    if (newX < 0 ||
                            newX > constraints.getFieldWidth() - 1 ||
                            newY < 0 ||
                            newY > constraints.getFieldHeight() - 1) {
                        continue;
                    }
                    if (getCellByCoords(new Coords(newX, newY)).getStatus() == CellStatus.MINED) mineCount++;
                }
            }
            mc.setAroundMines(mineCount);
        }

    }

    public void check() {
        // check field data.
        {
            for (MineCell[] fieldRow : fieldData) {
                for (MineCell cell : fieldRow) System.out.println(cell.getStatus());
            }
        }
    }

    public void revealAll() {
        for (int i = 0; i <= constraints.getMaxIndex(); i++) {
            MineCell mc = getCellByCoords(new Coords(i));
            if (mc.getStatus() == CellStatus.EMPTY) {
                mc.reveal();
            } else if (mc.getStatus() == CellStatus.MINED) {
                mc.expose();
            }
        }

    }

    public MineCell getCellByCoords(Coords coords) {
        return fieldData[coords.getY()][coords.getX()];
    }

     public static void main(String[] args) {
        MineField mf = new MineField();
        mf.initialize();
        mf.check();
    }
}

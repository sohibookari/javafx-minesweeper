package org.sohibookari.minesweeper.module.field;

import org.sohibookari.minesweeper.module.Constraints;
import org.sohibookari.minesweeper.module.cell.CellStatus;
import org.sohibookari.minesweeper.module.cell.Coords;
import org.sohibookari.minesweeper.module.cell.MineCell;

public class MineField {
    int fieldWidth;
    int fieldHeight;
    int flaggedMine;
    MineCell[][] fieldData;

    private final Constraints constraints = new Constraints();
    private final int[] offsetY = {-1, 0, 1};
    private final int[] offsetX = {-1, 0, 1};

    public MineField() {
        fieldWidth = constraints.getFieldWidth();
        fieldHeight = constraints.getFieldHeight();
    }

    private void check() {
        // check field data.
        {
            for (MineCell[] fieldRow : fieldData) {
                for (MineCell cell : fieldRow) System.out.println(cell.getStatus());
            }
        }
    }

    private MineCell getCellByCoords(Coords coords) {
        return fieldData[coords.getY()][coords.getX()];
    }

    void initialize() {

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
        for (int i = 0; i < constraints.getMaxIndex(); i++) {
            MineCell mc = getCellByCoords(new Coords(i));
            int mineCount = 0;
            for (int j = 0; j < offsetY.length; j++) {
                for (int k = 0; k < offsetX.length; k++) {
                    if ( j == 0 && k == 0 ) continue;
                    int newY = mc.getCoords().getY() + j;
                    int newX = mc.getCoords().getX() + i;
                    if (    newX < 0 ||
                            newX > constraints.getFieldWidth() ||
                            newY < 0 ||
                            newY > constraints.getFieldHeight() ) {
                        continue;
                    }
                    mineCount++;
                }
            }
            mc.setAroundMines(mineCount);
        }

    }

     public static void main(String[] args) {
        MineField mf = new MineField();
        mf.initialize();
        mf.check();
    }
}

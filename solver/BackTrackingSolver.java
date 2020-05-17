/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import grid.Coordinate;
import grid.SudokuGrid;

import java.util.ArrayList;
import java.util.List;


/**
 * Backtracking solver for standard Sudoku.
 */
public class BackTrackingSolver extends StdSudokuSolver
{
    // TODO: Add attributes as needed.
    private List<List<Coordinate>> sudoku;
    private ArrayList<Integer> values;

    public BackTrackingSolver() {
        // TODO: any initialisation you want to implement.
        sudoku = new ArrayList<>();
        values = new ArrayList<>();
    } // end of BackTrackingSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
        // TODO: your implementation of the backtracking solver for standard Sudoku.
        System.out.println("Solving");
        int row = -1;
        int col = -1;
        boolean empty = true;
        int size = grid.getSize();
        sudoku = grid.getBoard();
        values = grid.getValues();

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(sudoku.get(i).get(j).getValue() == -1){
                    System.out.println("Board == -1");
                    row = i;
                    col = j;

                    empty = false;
                    break;
                }
            }
            if(!empty)
                break;
        }

        if(empty)
            return true;

        for(Integer val : values) {
            if(grid.validate()) {
                sudoku.get(row).get(col).setValue(val);
                grid.setBoard(sudoku);
                if(solve(grid)) {
                    return true;
                } else {
                    sudoku.get(row).get(col).setValue(-1);
                }
            }
        }
        // placeholder
        return false;
    } // end of solve()

} // end of class BackTrackingSolver()

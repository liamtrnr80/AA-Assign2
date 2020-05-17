/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import grid.Coordinate;
import grid.SudokuGrid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Backtracking solver for standard Sudoku.
 */
public class BackTrackingSolver extends StdSudokuSolver
{
    // TODO: Add attributes as needed.
    private List<List<Coordinate>> sudoku;

    public BackTrackingSolver() {
        // TODO: any initialisation you want to implement.
        sudoku = new ArrayList<>();
    } // end of BackTrackingSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
        // TODO: your implementation of the backtracking solver for standard Sudoku.
        int row = -1;
        int col = -1;
        boolean empty = true;
        int size = grid.getSize();
        sudoku = grid.getBoard();


        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(sudoku.get(i).get(j).getValue() == 0){
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

        for(int num = 1; num <= size; num++) {

        }
        // placeholder
        return false;
    } // end of solve()

} // end of class BackTrackingSolver()

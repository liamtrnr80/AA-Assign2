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
        sudoku = new ArrayList<>();
        values = new ArrayList<>();
    } // end of BackTrackingSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
        int row = -1;
        int col = -1;
        boolean empty = true;
        int size = grid.getSize();
        sudoku = grid.getBoard();
        values = grid.getValues();

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(sudoku.get(i).get(j).getValue() == -1){
                    row = i;
                    col = j;

                    empty = false;
                    break;
                }
            }
            if(!empty) {
                break;
            }
        }

        if(empty){
            return true;
        }

        for(Integer val : values) {
            if(isSafe(row, col, val)) {
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

    private boolean isSafe(int row, int col, int num) {
        for(int i = 0; i < sudoku.size(); i++) {
            if(sudoku.get(row).get(i).getValue() == (num)) {
                return false;
            }
        }
    
        for (List<Coordinate> coordinates : sudoku) {
            if (coordinates.get(col).getValue() == (num)) {
                return false;
            }
        }
    
        int sqrt = (int) Math.sqrt(sudoku.size());
        int boxXStart = row - row % sqrt;
        int boxYStart = col - col % sqrt;
    
        for(int i = boxXStart; i < boxXStart + sqrt; i++) {
            for(int j = boxYStart; j < boxYStart + sqrt; j++) {
                if (sudoku.get(i).get(j).getValue() == num) {
                    return false;
                }
            }
        }
       
        return true;
    }
} // end of class BackTrackingSolver()

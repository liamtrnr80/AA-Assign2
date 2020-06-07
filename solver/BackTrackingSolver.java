/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import cell.AbstractCell;
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
    private List<AbstractCell> board;
    private List<Integer> values;

    public BackTrackingSolver() {
        board = new ArrayList<>();
        values = new ArrayList<>();
    } // end of BackTrackingSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
        if(grid == null) {
            throw new RuntimeException("Please specify sudoku board to solve");
        }
    
        board = grid.getBoard();
        values = grid.getValues();
    
        return solve(0);
    } // end of solve()
    
    private boolean solve(int index) {
        if(index == board.size()) {
            return board.stream().allMatch(AbstractCell::isValid);
        }
        
        AbstractCell cell = board.get(index);
        if(cell.isFinal()) {
            return solve(index + 1);
        } else {
            for(Integer val : values) {
                cell.setValue(val);
                if(cell.isValid()) {
                    boolean done = solve(index + 1);
                    if(done) {
                        return true;
                    }
                }
            }
            cell.setValue(-1);
            return false;
        }
    }
} // end of class BackTrackingSolver()

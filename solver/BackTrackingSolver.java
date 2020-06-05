/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import cell.AbstractCell;
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
        // TODO: any initialisation you want to implement.
        board = new ArrayList<>();
        values = new ArrayList<>();
    } // end of BackTrackingSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
        // TODO: your implementation of the backtracking solver for standard Sudoku.
        int row = -1;
        int col = -1;
        boolean empty = true;
        int size = grid.size();
        board = grid.board();
        values = grid.values();
        
        
        
        return false;
    } // end of solve()

} // end of class BackTrackingSolver()

/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import grid.Coordinate;
import grid.SudokuGrid;

import java.util.ArrayList;
import java.util.List;


/**
 * Dancing links solver for standard Sudoku.
 */
public class DancingLinksSolver extends StdSudokuSolver
{
    // TODO: Add attributes as needed.
    private List<List<Coordinate>> sudoku;
    private ArrayList<Integer> values;
    
    public DancingLinksSolver() {
        // TODO: any initialisation you want to implement.
        sudoku = new ArrayList<>();
        values = new ArrayList<>();
    } // end of DancingLinksSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
        // TODO: your implementation of the dancing links solver for Killer Sudoku.

        // placeholder
        return false;
    } // end of solve()

} // end of class DancingLinksSolver

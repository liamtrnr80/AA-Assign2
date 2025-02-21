/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package grid;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Abstract class representing the general interface for a Sudoku grid.
 * Both standard and Killer Sudoku extend from this abstract class.
 */
public abstract class SudokuGrid {
    
    protected List<AbstractCell> board;
    protected List<List<AbstractCell>> tempBoard;
    protected List<Integer> values;
    
    protected int size;
    protected int sqr;
    
    protected boolean isFinished;
    
    public SudokuGrid() {
        values = new ArrayList<>();
        tempBoard = new ArrayList<>();
        isFinished = false;
    }
    
    /**
     * Load the specified file and construct an initial grid from the contents
     * of the file.  See assignment specifications and sampleGames to see
     * more details about the format of the input files.
     *
     * @param filename Filename of the file containing the intial configuration
     *                 of the grid we will solve.
     * @throws FileNotFoundException If filename is not found.
     * @throws IOException           If there are some IO exceptions when openning or closing
     *                               the files.
     */
    public abstract void initGrid(String filename)
            throws FileNotFoundException, IOException;
    
    
    /**
     * Write out the current values in the grid to file.  This must be implemented
     * in order for your assignment to be evaluated by our testing.
     *
     * @param filename Name of file to write output to.
     * @throws FileNotFoundException If filename is not found.
     * @throws IOException           If there are some IO exceptions when openning or closing
     *                               the files.
     */
    public abstract void outputGrid(String filename)
            throws FileNotFoundException, IOException;
    
    
    /**
     * Converts grid to a String representation.  Useful for displaying to
     * output streams.
     *
     * @return String representation of the grid.
     */
    public abstract String toString();
    
    
    /**
     * Checks and validates whether the current grid satisfies the constraints
     * of the game in question (either standard or Killer Sudoku).  Override to
     * implement game specific checking.
     *
     * @return True if grid satisfies all constraints of the game in question.
     */
    public abstract boolean validate();
    
    /**
     * A way to get the size of the sudoku grid when trying to solve the grid.
     *
     * @return The size of the grid.
     */
    public int getSize() {
        return size;
    }
    
    public int getSqr() {
        return sqr;
    }
    
    /**
     * A way to retrieve the sudoku board after it is initiated by the user and can be used
     * to solve properly
     *
     * @return The ArrayList of the board
     */
    public List<AbstractCell> getBoard() {
        return Collections.unmodifiableList(board);
    }
    
    
    /**
     * A way to retrieve the values to be used on to solve the sudoku board.
     * @return
     */
    public List<Integer> getValues() {
        return values;
    }
    
} // end of abstract class SudokuGrid

/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package grid;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class implementing the grid for standard Sudoku.
 * Extends SudokuGrid (hence implements all abstract methods in that abstract
 * class).
 * You will need to complete the implementation for this for task A and
 * subsequently use it to complete the other classes.
 * See the comments in SudokuGrid to understand what each overriden method is
 * aiming to do (and hence what you should aim for in your implementation).
 */
public class StdSudokuGrid extends SudokuGrid
{
    // TODO: Add your own attributes
    /** The sudoku board that was generated at the start */
    private List<Coordinate> initSudoku;
    /** The solution of the sudoku board */
    private List<Coordinate> soluSudoku;
    /** ArrayList containing the non-zero values */
    private ArrayList<Integer> values;
    /** The size of the sudoku board */
    private int size;
    
    public StdSudokuGrid() {
        initSudoku = new ArrayList<>();
        soluSudoku = new ArrayList<>();
        values = new ArrayList<Integer>();
        // TODO: any necessary initialisation at the constructor
    } // end of StdSudokuGrid()


    /* ********************************************************* */


    @Override
    public void initGrid(String filename)
        throws FileNotFoundException, IOException
    {
        // TODO
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        String line;
        String[] fields;
        
        int rowNum = 0;
        int colNum = 0;
        
        while((line = reader.readLine()) != null) {
            fields = line.trim().split("\\s+");
            System.out.println(line);
        }
        
        
        
    } // end of initBoard()


    @Override
    public void outputGrid(String filename)
        throws FileNotFoundException, IOException
    {
        // TODO
    } // end of outputBoard()


    @Override
    public String toString() {
        // TODO

        // placeholder
        return String.valueOf("");
    } // end of toString()


    @Override
    public boolean validate() {
        // TODO

        // placeholder
        return false;
    } // end of validate()

} // end of class StdSudokuGrid

/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package grid;

import cell.AbstractCell;
import cell.KillerCell;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Class implementing the grid for Killer Sudoku.
 * Extends SudokuGrid (hence implements all abstract methods in that abstract
 * class).
 * You will need to complete the implementation for this for task E and
 * subsequently use it to complete the other classes.
 * See the comments in SudokuGrid to understand what each overriden method is
 * aiming to do (and hence what you should aim for in your implementation).
 */
public class KillerSudokuGrid extends SudokuGrid
{
    // TODO: Add your own attributes

    public KillerSudokuGrid() {
        super();
    } // end of KillerSudokuGrid()

    /* ********************************************************* */

    @Override
    public void initGrid(String filename)
        throws FileNotFoundException, IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        size = Integer.parseInt(reader.readLine());
        sqr = (int) Math.sqrt(size);
    
        System.out.println(size);
        
        String line = reader.readLine();
        String[] vals = line.trim().split("\\s+|,\\s*");
    
        for (String string : vals) {
            values.add(Integer.parseInt(string));
        }
    
        int numCages = Integer.parseInt(reader.readLine());
        board = new ArrayList<>(size * size);
        
        List<List<KillerCell>> column = new ArrayList<>(size);
        List<List<KillerCell>> grid = new ArrayList<>(size);
        List<List<KillerCell>> rows = new ArrayList<>(size);
        
        for(int i = 0; i < size; i++) {
            column.add(new ArrayList<>());
            grid.add(new ArrayList<>());
        }
        
        int index = 0;
        Map<String, Integer> values = new HashMap<>();
        Map<String, List<KillerCell>> cage = new HashMap<>();
        
        for(int r = 0; r < size; r++) {
        
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

} // end of class KillerSudokuGrid

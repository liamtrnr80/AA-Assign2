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
        initSudoku = new ArrayList<Coordinate>();
        soluSudoku = new ArrayList<Coordinate>();
        values = new ArrayList<Integer>();
        size = 0;
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
        int rowNum = 0;
        int colNum = 0;
        int lineNum = 0;

        while((line = reader.readLine()) != null) {
            String[] field = line.trim().split("\\s+|,\\s*");
            if (lineNum == 0) {
                System.out.println("Initiating Size");
                this.size = Integer.parseInt(field[0]);
                setupSudoku();
            } else if (lineNum == 1) {
                for (String string : field) {
                    this.values.add(Integer.parseInt(string));
                }
            } else {
                Coordinate newCoord = new Coordinate(Integer.parseInt(field[0]), Integer.parseInt(field[1]), Integer.parseInt(field[2]));
                for(Coordinate coordinate : initSudoku) {
                    if(coordinate.equals(newCoord)) {
                        initSudoku.set(initSudoku.indexOf(coordinate), newCoord);
                    }
                }
            }
            lineNum++;
        }
        System.out.println(this);
    } // end of initBoard()


    @Override
    public void outputGrid(String filename)
        throws FileNotFoundException, IOException
    {
        // TODO

    } // end of outputBoard()


    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for(int i = 0; i < (size*size); i++) {
            if(i % size == 0) {
                stringBuffer.append("\n" + initSudoku.get(i));
            } else {
                stringBuffer.append(" " + initSudoku.get(i));
            }
        }
        return stringBuffer.toString();
    } // end of toString()



    @Override
    public boolean validate() {
        // TODO

        // placeholder
        return false;
    } // end of validate()

    private void setupSudoku() {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                initSudoku.add(new Coordinate(i, j));
            }
        }
    }

} // end of class StdSudokuGrid

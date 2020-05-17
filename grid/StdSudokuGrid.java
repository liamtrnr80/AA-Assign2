/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package grid;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

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
    private List<List<Coordinate>> initSudoku;
    /** The solution of the sudoku board */
    private List<List<Coordinate>> soluSudoku;
    /** ArrayList containing the non-zero values */
    private ArrayList<Integer> values;
    /** The size of the sudoku board */
    private int size;
    
    public StdSudokuGrid() {
        initSudoku = new ArrayList<>();
        soluSudoku = new ArrayList<>();
//        initSudoku = new ArrayList<new ArrayList<Coordinate>()>();
//        soluSudoku = new ArrayList<new ArrayList<Coordinate>()>();
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
                this.size = Integer.parseInt(field[0]);
                setupSudoku();
            } else if (lineNum == 1) {
                for (String string : field) {
                    this.values.add(Integer.parseInt(string));
                }
            } else {
                Coordinate newCoord = new Coordinate(Integer.parseInt(field[0]), Integer.parseInt(field[1]), Integer.parseInt(field[2]));
                for(List<Coordinate> list : initSudoku) {
                    for(Coordinate coordinate : list) {
                        if(coordinate.equals(newCoord)) {
                            coordinate.setValue(Integer.parseInt(field[2]));
                        }
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

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                stringBuffer.append(initSudoku.get(i).get(j) + " ");
            }
            stringBuffer.append("\n");
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
            initSudoku.add(new ArrayList<>());
            for(int j = 0; j < size; j++) {
                initSudoku.get(i).add(new Coordinate(i, j));
            }
        }
    }

    /**
     * Checks to make sure that the row is valid
     * @param coordinate the coordinate to check
     * @return true if coordinate is unique to row
     */
    private boolean rowSafe(Coordinate coordinate) {
        for(int i = 0; i < size; i++) {
            if(initSudoku.get(coordinate.getRow()).get(i).getValue() == coordinate.getValue())
                return false;
        }
        return true;
    }

    private boolean colSafe(Coordinate coordinate) {
        for(int i = 0; i < size; i++) {
            if(initSudoku.get(i).get(coordinate.getColumn()).getValue() == coordinate.getValue())
                return false;
        }
        return true;
    }

    private boolean boxSafe(Coordinate coordinate) {
        int sqrt = (int) Math.sqrt(size);
        return false;
    }

} // end of class StdSudokuGrid

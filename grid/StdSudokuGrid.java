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
    /** ArrayList containing the non-zero values */
    private ArrayList<Integer> values;
    /** The size of the sudoku board */
    private int size;
    
    public StdSudokuGrid() {
        initSudoku = new ArrayList<>();
        values = new ArrayList<>();
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
        StringBuilder stringBuilder = new StringBuilder();

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                stringBuilder.append(initSudoku.get(i).get(j)).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    } // end of toString()



    @Override
    public boolean validate() {
        // TODO
        if(initSudoku == null || initSudoku.size() != size || initSudoku.get(0).size() != size)
            return false;
        for(int i = 0; i < size; i++) {
            List check = new ArrayList();
            for(int j = 0; j < size; j++) {
                if(initSudoku.get(i).get(j).getValue() != -1) {
                    if(check.contains(initSudoku.get(i).get(j).getValue())) {
                        return false;
                    }
                    check.add(initSudoku.get(i).get(j).getValue());
                }
            }
        }

        for(int j = 0; j < size; j++) {
            List check = new ArrayList();
            for(int i = 0; i < size; i++) {
                if(initSudoku.get(i).get(j).getValue() != -1) {
                    if(check.contains(initSudoku.get(i).get(j).getValue())) {
                        return false;
                    }
                    check.add(initSudoku.get(i).get(j).getValue());
                }
            }
        }

        int sqrt = (int) Math.sqrt(size);
        for(int j = 0; j < size; j++) {
            List check = new ArrayList();
            for(int i = 0; i < size; i++) {
                if(initSudoku.get(i).get(j).getValue() != -1) {
                    if(check.contains(initSudoku.get(i).get(j).getValue())) {
                        return false;
                    }
                    check.add(initSudoku.get(i).get(j).getValue());
                }
            }
        }

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
        for(int i = 0; i < size; i++)
            if(initSudoku.get(coordinate.getRow()).get(i).getValue() == coordinate.getValue())
                return false;

        return true;
    }

    /**
     * Checks to make sure that the column is valid
     * @param coordinate the coordinate to check
     * @return true if coordinate is unique to column
     */
    private boolean colSafe(Coordinate coordinate) {
        for(int i = 0; i < size; i++)
            if(initSudoku.get(i).get(coordinate.getColumn()).getValue() == coordinate.getValue())
                return false;

        return true;
    }

    /**
     * Checks to make sure that sudoku box is valid
     * @param coordinate the coordinate to check
     * @return true if coordinate is unique to column
     */
    private boolean boxSafe(Coordinate coordinate) {
        int sqrt = (int) Math.sqrt(size);
        int boxXStart = coordinate.row - coordinate.row % sqrt;
        int boxYStart = coordinate.column - coordinate.column % sqrt;

        for(int i = boxXStart; i < boxXStart + sqrt; i++)
            for(int j = boxYStart; j < boxYStart + sqrt; j++)
                if(initSudoku.get(i).get(j).getValue() == coordinate.getValue())
                    return false;

        return true;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public List<List<Coordinate>> getBoard() {
        return initSudoku;
    }

    @Override
    public void setBoard(List board) {
        this.initSudoku = board;
    }

    @Override
    public ArrayList<Integer> getValues() {
        return values;
    }
} // end of class StdSudokuGrid

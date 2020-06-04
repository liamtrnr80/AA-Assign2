/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package grid;

import cell.Cell;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
    public StdSudokuGrid() {
        super();
        
        // TODO: any necessary initialisation at the constructor
    } // end of StdSudokuGrid()


    /* ********************************************************* */


    @Override
    public void initGrid(String filename)
        throws FileNotFoundException, IOException
    {
        // TODO
        Scanner reader;
        try {
            reader = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
             throw new RuntimeException(e);
        }
        
        String line = reader.nextLine();
        
        size = Integer.parseInt(line);
        sqr = (int) Math.sqrt(size);

        
        
        line = reader.nextLine();
        String[] vals = line.trim().split("\\s+|,\\s*");
        
        for(String string : vals) {
            values.add(Integer.parseInt(string));
        }
        
        board = new ArrayList<>(size*size);
        
        List<List<Cell>> col = new ArrayList<>(size);
        List<List<Cell>> rows = new ArrayList<>(size);
        List<List<Cell>> grid = new ArrayList<>(size);
    
        setupBoard();
        
        int index = 0;
        
        for(int y = 0; y < size; y++) {
            List<Cell> currentRow = new ArrayList<>();
            rows.add(currentRow);
            for(int x = 0; x < size; x++) {
                index = (x/sqr) + ((y/sqr) * sqr);
                List<Cell> currentColumn = col.get(x);
                List<Cell> currentNonet = grid.get(index);
                
                int value = tempBoard.get(y).get(x).getValue();
                
                Cell s = new Cell(x, y, value);
                
                s.setRow(currentRow);
                s.setCol(currentColumn);
                s.setGrid(currentNonet);
                
                currentRow.add(s);
                currentColumn.add(s);
                currentNonet.add(s);
            }
        }
        
//        for(int i = 0; i < size; i++) {
//            col.add(new ArrayList<>());
//            grid.add(new ArrayList<>());
//        }
//
//        int index = 0;
//
//        List<Cell> input = new ArrayList<>();
//
//        while((line = reader.nextLine()) != null) {
//            String[] field = line.trim().split("\\s+|,\\s*");
//            Cell s = new Cell(Integer.parseInt(field[0]), Integer.parseInt(field[1]), Integer.parseInt(field[2]));
//            input.add(s);
//        }
//
//        for (int y = 0; y < size; y++) {
//            List<Cell> newRow = new ArrayList<Cell>();
//            row.add(newRow);
//            for(int x = 0; x < size; x++) {
//                index = (x/sqr) + ((y/sqr) * sqr);
//                List<Cell> newCol = col.get(x);
//                List<Cell> newGrid = grid.get(index);
//
//                Cell s = new Cell(x, y, 0);
//
//                System.out.println("New Cell = " + s);
//
//                s.setRow(newRow);
//                s.setCol(newCol);
//                s.setGrid(newGrid);
//
//                newRow.add(s);
//                newCol.add(s);
//                newGrid.add(s);
//
//                board.add(s);
//            }
//        }
//        System.out.println(this);
//        System.out.println(board);
        
        reader.close();
//
//        System.out.println();
//
//        System.out.println(this);
//        System.out.println(board);
    } // end of initBoard()


    public void setupBoard() {
        for(int y = 0; y < size; y++) {
            tempBoard.add(new ArrayList<>());
            for(int x = 0; x < size; x++) {
                tempBoard.get(y).add(new Cell(x, y, -1));
            }
        }
    }
    
    @Override
    public void outputGrid(String filename)
        throws FileNotFoundException, IOException
    {
        // TODO
        
    } // end of outputBoard()


    @Override
    public String toString() {
        // TODO
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(board.get(0).getValue());
        
        for (int i = 1; i < board.size(); i++) {
            if(i % size == 0) {
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(",");
            }
            stringBuilder.append(board.get(i).getValue());
        }
        
        return stringBuilder.toString();
    } // end of toString()

    

    @Override
    public boolean validate() {
        // TODO

        // placeholder
        return false;
    } // end of validate()

} // end of class StdSudokuGrid

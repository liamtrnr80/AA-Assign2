/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package grid;

import cell.AbstractCell;
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
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        String line = reader.readLine();
        
        size = Integer.parseInt(line);
        sqr = (int) Math.sqrt(size);
        
        line = reader.readLine();
        String[] vals = line.trim().split("\\s+|,\\s*");
        
        for(String string : vals)
            values.add(Integer.parseInt(string));
        
        board = new ArrayList<>(size*size);
        
        List<List<Cell>> col = new ArrayList<>(size);
        List<List<Cell>> rows = new ArrayList<>(size);
        List<List<Cell>> grid = new ArrayList<>(size);
    
        for(int i = 0; i < size; i++) {
            col.add(new ArrayList<>());
            grid.add(new ArrayList<>());
        }
        
        setupBoard(reader);
        
        int index = 0;
        
        for(int r = 0; r < size; r++) {
            List<Cell> currentRow = new ArrayList<>();
            rows.add(currentRow);
            for(int c = 0; c < size; c++) {
                index = (c/sqr) + ((r/sqr) * sqr);
                
                List<Cell> currentColumn = col.get(c);
                List<Cell> currentNonet = grid.get(index);
                
                int value = tempBoard.get(r).get(c).getValue();
                
                Cell s = new Cell(r, c, value);
                
                s.setRow(currentRow);
                s.setCol(currentColumn);
                s.setGrid(currentNonet);
                
                currentRow.add(s);
                currentColumn.add(s);
                currentNonet.add(s);
                
                board.add(s);
            }
        }
        System.out.println(this);
    } // end of initBoard()


    public void setupBoard(BufferedReader reader) throws IOException {
        for(int y = 0; y < size; y++) {
            tempBoard.add(new ArrayList<>());
            for(int x = 0; x < size; x++) {
                tempBoard.get(y).add(new Cell(y, x, -1));
            }
        }
        String line;
        
        while((line = reader.readLine()) != null) {
            String[] field = line.trim().split("\\s+|,\\s*");
            Cell newCell = new Cell(Integer.parseInt(field[0]), Integer.parseInt(field[1]), Integer.parseInt(field[2]));
            for(List<AbstractCell> list : tempBoard)
                for(AbstractCell cell : list)
                    if(cell.equals(newCell))
                        cell.setValue(Integer.parseInt(field[2]));
        }
        
    }
    
    @Override
    public void outputGrid(String filename)
        throws FileNotFoundException, IOException
    {
        // TODO
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(this.toString());
        writer.close();
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
        return board.stream().allMatch(AbstractCell::isValid);
        
//        return false;
    } // end of validate()
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public List<Integer> values() {
        return values;
    }
    
} // end of class StdSudokuGrid

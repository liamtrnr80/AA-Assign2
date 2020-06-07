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
public class StdSudokuGrid extends SudokuGrid {
    
    public StdSudokuGrid() {
        super();
    } // end of StdSudokuGrid()
    
    
    /* ********************************************************* */
    
    
    @Override
    public void initGrid(String filename)
            throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        size = Integer.parseInt(reader.readLine());
        sqr = (int) Math.sqrt(size);
    
        String line = reader.readLine();
        String[] vals = line.trim().split("\\s+|,\\s*");
        
        for (String string : vals)
            values.add(Integer.parseInt(string));
        
        board = new ArrayList<>(size * size);
        
        List<List<Cell>> col = new ArrayList<>(size);
        List<List<Cell>> rows = new ArrayList<>(size);
        List<List<Cell>> grid = new ArrayList<>(size);
        
        for (int i = 0; i < size; i++) {
            col.add(new ArrayList<>());
            grid.add(new ArrayList<>());
        }
        
        setupSudoku(reader);
        
        int index = 0;
        
        for (int r = 0; r < size; r++) {
            List<Cell> currentRow = new ArrayList<>();
            rows.add(currentRow);
            for (int c = 0; c < size; c++) {
                index = (c / sqr) + ((r / sqr) * sqr);
                
                List<Cell> currentColumn = col.get(c);
                List<Cell> currentNonent = grid.get(index);
                
                int value = tempBoard.get(r).get(c).getValue();
                
                Cell s = new Cell(r, c, value);
                
                s.setRow(currentRow);
                s.setCol(currentColumn);
                s.setGrid(currentNonent);
                
                currentRow.add(s);
                currentColumn.add(s);
                currentNonent.add(s);
                
                board.add(s);
            }
        }
//        System.out.println(this);
    } // end of initBoard()
    
    
    @Override
    public void outputGrid(String filename)
            throws FileNotFoundException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        
        stringBuilder.append(board.get(0).getValue());
        for (int i = 1; i < board.size(); i++) {
            if (i % size == 0) {
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(",");
            }
            stringBuilder.append(board.get(i).getValue());
        }
        
        writer.write(stringBuilder.append("\n").toString());
        writer.close();
    } // end of outputBoard()
    
    
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        
        stringBuilder.append(board.get(0).getValue());
        for (int i = 1; i < board.size(); i++) {
            if (i % size == 0) {
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(" ");
            }
            stringBuilder.append(board.get(i).getValue());
        }

        return stringBuilder.append("\n").toString();
    } // end of toString()
    
    
    @Override
    public boolean validate() {
        if (board == null || board.size() != size * size)
            return false;
        
        return board.stream().allMatch(AbstractCell::isValid);
    } // end of validate()
    
    private void setupSudoku(BufferedReader reader) throws IOException {
        for (int r = 0; r < size; r++) {
            tempBoard.add(new ArrayList<>());
            for (int c = 0; c < size; c++) {
                tempBoard.get(r).add(new Cell(r, c, -1));
            }
        }
        String line;
        
        while ((line = reader.readLine()) != null) {
            String[] field = line.trim().split("\\s+|,\\s*");
            Cell newCell = new Cell(Integer.parseInt(field[0]), Integer.parseInt(field[1]), Integer.parseInt(field[2]));
            for (List<AbstractCell> list : tempBoard)
                for (AbstractCell cell : list)
                    if (cell.equals(newCell))
                        cell.setValue(Integer.parseInt(field[2]));
        }
    }
} // end of class StdSudokuGrid

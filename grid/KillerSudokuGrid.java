/**
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package grid;

import cell.AbstractCell;
import cell.KillerCell;

import java.io.*;
import java.util.*;


/**
 * Class implementing the grid for Killer Sudoku.
 * Extends SudokuGrid (hence implements all abstract methods in that abstract
 * class).
 * You will need to complete the implementation for this for task E and
 * subsequently use it to complete the other classes.
 * See the comments in SudokuGrid to understand what each overriden method is
 * aiming to do (and hence what you should aim for in your implementation).
 */
public class KillerSudokuGrid extends SudokuGrid {
    // TODO: Add your own attributes
    private Map<String, List<KillerCell>> tempCage;
    private Map<String, Integer> tempVals;
    
    private int numCages;
    
    public KillerSudokuGrid() {
        super();
        tempCage = new HashMap<>();
        tempVals = new HashMap<>();
        
    } // end of KillerSudokuGrid()
    
    /* ********************************************************* */
    
    @Override
    public void initGrid(String filename)
            throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        
        size = Integer.parseInt(reader.readLine());
        sqr = (int) Math.sqrt(size);
        
        
        String line = reader.readLine();
        String[] vals = line.trim().split("\\s+|,\\s*");
        
        for (String string : vals) {
            values.add(Integer.parseInt(string));
        }
        
        numCages = Integer.parseInt(reader.readLine());
        
        
        board = new ArrayList<>(size * size);
        
        List<List<KillerCell>> column = new ArrayList<>(size);
        List<List<KillerCell>> grid = new ArrayList<>(size);
        List<List<KillerCell>> rows = new ArrayList<>(size);
        
        for (int i = 0; i < size; i++) {
            column.add(new ArrayList<>());
            grid.add(new ArrayList<>());
        }
        
        int index = 0;
        
        Map<String, List<KillerCell>> cage = new HashMap<>();
        setupTempCage(reader);
        
        for (int r = 0; r < size; r++) {
            List<KillerCell> currentRow = new ArrayList<>();
            rows.add(currentRow);
            
            for (int c = 0; c < size; c++) {
                index = (c / sqr) + ((r / sqr) * sqr);
                List<KillerCell> currentColumn = column.get(c);
                List<KillerCell> currentNonet = grid.get(index);
                
                KillerCell cell = new KillerCell(r, c, -1);
                
                cell.setRow(currentRow);
                cell.setCol(currentColumn);
                cell.setGrid(currentNonet);
                
                String key = getKey(cell);
                
                List<KillerCell> currentCage = cage.getOrDefault(key, new ArrayList<>());
                
                currentCage.add(cell);
                cage.put(key, currentCage);
                cell.setCage(currentCage);
                
                currentRow.add(cell);
                currentColumn.add(cell);
                currentNonet.add(cell);
                
                board.add(cell);
            }
        }
        
        cage.forEach((k, v) -> v.forEach(c -> c.setTotal(tempVals.get(k))));
        
        System.out.println("Cage " + cage);
    } // end of initBoard()
    
    private void setupTempCage(BufferedReader reader) throws IOException {
        int index = 0;
        String key = "";
        String[] keys = new String[numCages];
        String line;
        
        while ((line = reader.readLine()) != null) {
            String[] newLine = line.trim().split(" ");
            List<KillerCell> tempList = new ArrayList<>(newLine.length - 1);
            
            int total = Integer.parseInt(newLine[0]);
            
            char c = (char) ('a' + (index % 26));
            key = c + "";
            
            if (index > 25)
                key = keys[(index / 26) - 1] + "" + c;
            
            for (int i = 1; i < newLine.length; i++) {
                String[] tempCell = newLine[i].trim().split(",");
                
                tempList.add(new KillerCell(Integer.parseInt(tempCell[0]), Integer.parseInt(tempCell[1]), -1));
                
                tempVals.put(key, total);
                tempCage.put(key, tempList);
            }
            
            index++;
        }
    }
    
    private String getKey(KillerCell cell) {
        
        for (Map.Entry<String, List<KillerCell>> entry : tempCage.entrySet()) {
            List<KillerCell> entryCells = entry.getValue();
            if (entryCells.contains(cell)) {
                return entry.getKey();
            }
        }
        return null;
    }
    
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
        
        writer.write(stringBuilder.toString());
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
        return stringBuilder.toString();
    } // end of toString()
    
    
    @Override
    public boolean validate() {
        // TODO
        
        // placeholder
        return false;
    } // end of validate()
    
} // end of class KillerSudokuGrid

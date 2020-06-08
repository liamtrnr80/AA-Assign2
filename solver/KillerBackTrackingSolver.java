/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import grid.AbstractCell;
import grid.SudokuGrid;

import java.util.ArrayList;
import java.util.List;


/**
 * Backtracking solver for Killer Sudoku.
 */
public class KillerBackTrackingSolver extends KillerSudokuSolver
{
    private List<AbstractCell> board;
    private List<Integer> values;
    
    public KillerBackTrackingSolver() {
        board = new ArrayList<>();
        values = new ArrayList<>();
    } // end of KillerBackTrackingSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
        if(grid == null) {
            throw new RuntimeException("Please specify sudoku board to solve");
        }
        
        board = grid.getBoard();
        values = grid.getValues();
    
        return solve(0);
    } // end of solve()
    
    private boolean solve(int index) {
        
        if(index == board.size()) {
            return board.stream().allMatch(AbstractCell::isValid);
        }
        
        AbstractCell cell = board.get(index);
        
        if(cell.isFinal()) {
            return solve(index + 1);
        } else {
            for(Integer val : values) {
                cell.setValue(val);
                if(cell.isValid()) {
                    boolean done = solve(index + 1);
                    if(done) {
                        return true;
                    }
                }
            }
            cell.setValue(0);
            return false;
        }
    }

    public String print() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(board.get(0).getValue());
        for (int i = 1; i < board.size(); i++) {
            if (i % ((int) Math.sqrt(board.size())) == 0) {
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(" ");
            }
            stringBuilder.append(board.get(i).getValue());
        }

        return stringBuilder.append("\n").toString();
    }
} // end of class KillerBackTrackingSolver()

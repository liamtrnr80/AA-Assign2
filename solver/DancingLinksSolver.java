/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */

package solver;

import grid.AbstractCell;
import grid.SudokuGrid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * Dancing links solver for standard Sudoku.
 */
public class DancingLinksSolver extends StdSudokuSolver
{
    // TODO: Add attributes as needed.
    private final int INDEX = 1;
    private final int CONSTRAINTS = 4;
    
    private ColNode header;
    private List<Node> answer;
    
    private List<AbstractCell> board;
    private List<Integer> values;
    private int size;
    private int sqr;
    
    public DancingLinksSolver() {
        // TODO: any initialisation you want to implement.
        board = new ArrayList<>();
        values = new ArrayList<>();
    } // end of DancingLinksSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
        // TODO: your implementation of the dancing links solver for Killer Sudoku.

        // placeholder
        return false;
    } // end of solve()

    private void search(int k) {
        if(header.right == header) {
            solution(answer);
        } else {
            ColNode c = selectColNodeHeur();
            c.cover();
            
            for(Node r = c.bottom; r != c; r = r.bottom) {
                answer.add(r);
                
                for(Node j = r.right; j != r; j = j.right) {
                    j.column.cover();
                }
            }
            c.uncover();
        }
    }
    
    private ColNode selectColNodeHeur() {
        int min = values.size();
        ColNode ret = null;
        
        for(ColNode c = (ColNode) header.right; c != header; c = (ColNode) c.right) {
            if(c.size < min) {
                min = c.size;
                ret = c;
            }
        }
        return ret;
    }
    
    private boolean solution(List<Node> answer) {
        
        return true;
    }
} // end of class DancingLinksSolver

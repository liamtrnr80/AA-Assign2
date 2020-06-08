/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package solver;

import com.sun.xml.internal.bind.v2.model.core.ID;
import grid.AbstractCell;
import grid.SudokuGrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Algorithm X solver for standard Sudoku.
 */
public class AlgorXSolver extends StdSudokuSolver
{
    // TODO: Add attributes as needed.
    private final int INDEX = 1;
    private final int CONSTRAINTS = 4;
    private List<AbstractCell> board;
    private List<Integer> values;
    private int size;
    private int sqr;
    
    
    public AlgorXSolver() {
        // TODO: any initialisation you want to implement.
        board = new ArrayList<>();
        values = new ArrayList<>();
    } // end of AlgorXSolver()


    @Override
    public boolean solve(SudokuGrid grid) {
        // TODO: your implementation of the Algorithm X solver for standard Sudoku.
        board = grid.getBoard();
        values = grid.getValues();
        size = grid.getSize();
        sqr = grid.getSqr();
        
        int[][] matrix = sudokuToMatrix();
        ColNode header;
        List<Node> answer;
        
        
        
        // placeholder
        return false;
    } // end of solve()

    
    private int[][] createMatrix() {
        int [][] coverMatrix = new int[size * size * size][size * size * CONSTRAINTS];
    
        int header = 0;
        
        header = cellConstraint(coverMatrix, header);
        header = rowConstraint(coverMatrix, header);
        header = columnConstraint(coverMatrix, header);
        boxConstraint(coverMatrix, header);
        
        return coverMatrix;
    }
    
    private int coverMatrixIndex(int row, int col, int num) {
        return (row - 1) * size * size + (col - 1) * size + (num - 1);
    }
    
    private int boxConstraint(int[][] matrix, int header) {
        for(int r = INDEX; r <= size; r += sqr) {
            for(int c = INDEX; c <= size; c += sqr) {
                for(int n = INDEX; n <= size; n++, header++) {
                    for(int rDel = 0; rDel < sqr; rDel++) {
                        for(int cDel = 0; cDel < sqr; cDel++) {
                            int index = coverMatrixIndex(r + rDel, c + cDel, n);
                            matrix[index][header] = 1;
                        }
                    }
                }
            }
        }
        return header;
    }
    
    private int columnConstraint(int[][] matrix, int header) {
        for(int c = INDEX; c <= size; c++) {
            for(int n = INDEX; n <= size; n++, header++) {
                for (int r = INDEX; r <= size; r++) {
                    int index = coverMatrixIndex(r, c, n);
                    matrix[index][header] = 1;
                }
            }
        }
        
        return header;
    }
    
    private int rowConstraint(int[][] matrix, int header) {
        for(int r = INDEX; r <= size; r++) {
            for(int n = INDEX; n <= size; n++, header++) {
                for (int c = INDEX; c <= size; c++) {
                    int index = coverMatrixIndex(r, c, n);
                    matrix[index][header] = 1;
                }
            }
        }
        
        return header;
    }
    
    private int cellConstraint(int[][] matrix, int header) {
        for(int r = INDEX; r <= size; r++) {
            for(int c = INDEX; c <= size; c++, header++) {
                for (int n = INDEX; n <= size; n++) {
                    int index = coverMatrixIndex(r, c, n);
                    matrix[index][header] = 1;
                }
            }
        }
        
        return header;
    }
    
    private int[][] sudokuToMatrix() {
        int[][] coverMatrix = createMatrix();
        int index = 0;
        
        for(int r = INDEX; r <= size; r++) {
            for(int c = INDEX; c <= size; c++) {
                int n  = board.get(index).getValue();
                if(n != -1) {
                    for(int num = values.get(0); num <= values.get(values.size() - 1); num++) {
                        if(num != n) {
                            Arrays.fill(coverMatrix[coverMatrixIndex(r, c, num)], 0);
                        }
                    }
                }
                index++;
            }
        }
        
        return coverMatrix;
    }
} // end of class AlgorXSolver


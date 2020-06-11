/*
 * @author Jeffrey Chan & Minyi Li, RMIT 2020
 */
package solver;

import grid.AbstractCell;
import grid.SudokuGrid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 * Algorithm X solver for standard Sudoku.
 */
public class AlgorXSolver extends StdSudokuSolver {
    // TODO: Add attributes as needed.
    private final int INDEX = 1;
    private final int CONSTRAINTS = 4;
    
    private ColumnNode header;
    private List<DancingNode> answer;
    
    private List<AbstractCell> board;
    private List<Integer> values;
    private int size;
    private int sqr;
    private boolean solved;
    
    public AlgorXSolver() {
        // TODO: any initialisation you want to implement.
        board = new ArrayList<>();
        values = new ArrayList<>();
        solved = false;
    } // end of AlgorXSolver()
    
    
    @Override
    public boolean solve(SudokuGrid grid) {
        // TODO: your implementation of the Algorithm X solver for standard Sudoku.
        board = grid.getBoard();
        values = grid.getValues();
        size = grid.getSize();
        sqr = grid.getSqr();
        
        int[][] matrix = sudokuToMatrix();
        header = createBoard(matrix);
        answer = new LinkedList<>();
        
        search(0);
        
        return solved;
    } // end of solve()
    
    private void search(int k) {
        if (header.right == header) {
            solution(answer);
        } else {
            ColumnNode c = chooseNextColumn();
            c.cover();
            
            for (DancingNode r = c.bottom; r != c; r = r.bottom) {
                answer.add(r);
                
                for(DancingNode right = r.right; right != r; right = right.right) {
                    right.column.cover();
                }
                
                search(k + 1);
                answer.remove(answer.size() - 1);
                c = r.column;
    
                for (DancingNode j = r.left; j != r; j = j.left) {
                    j.column.uncover();
                }
            }
            c.uncover();
        }
    }
    
    private int[][] createMatrix() {
        int[][] coverMatrix = new int[size * size * size][size * size * CONSTRAINTS];
        
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
        for (int r = INDEX; r <= size; r += sqr) {
            for (int c = INDEX; c <= size; c += sqr) {
                for (int n = INDEX; n <= size; n++, header++) {
                    for (int rDel = 0; rDel < sqr; rDel++) {
                        for (int cDel = 0; cDel < sqr; cDel++) {
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
        for (int c = INDEX; c <= size; c++) {
            for (int n = INDEX; n <= size; n++, header++) {
                for (int r = INDEX; r <= size; r++) {
                    int index = coverMatrixIndex(r, c, n);
                    matrix[index][header] = 1;
                }
            }
        }
        
        return header;
    }
    
    private int rowConstraint(int[][] matrix, int header) {
        for (int r = INDEX; r <= size; r++) {
            for (int n = INDEX; n <= size; n++, header++) {
                for (int c = INDEX; c <= size; c++) {
                    int index = coverMatrixIndex(r, c, n);
                    matrix[index][header] = 1;
                }
            }
        }
        
        return header;
    }
    
    private int cellConstraint(int[][] matrix, int header) {
        for (int r = INDEX; r <= size; r++) {
            for (int c = INDEX; c <= size; c++, header++) {
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
        
        for (int r = INDEX; r <= size; r++) {
            for (int c = INDEX; c <= size; c++) {
                int n = board.get(index).getValue();
                if (n != -1) {
                    for (int num = values.get(0); num <= values.get(values.size() - 1); num++) {
                        if (num != n) {
                            Arrays.fill(coverMatrix[coverMatrixIndex(r, c, num)], 0);
                        }
                    }
                }
                index++;
            }
        }
        
        return coverMatrix;
    }
    
    private ColumnNode chooseNextColumn() {
        int min = values.size();
        ColumnNode ret = null;
        
        for (ColumnNode c = (ColumnNode) header.right; c != header; c = (ColumnNode) c.right) {
            if (c.size < min) {
                min = c.size;
                ret = c;
            }
        }
        return ret;
    }
    
    private ColumnNode createBoard(int[][] grid) {
        final int COLUMNS = grid[0].length;
        
        ColumnNode headerNode = new ColumnNode("header");
        List<ColumnNode> columnNodes = new ArrayList<>();
        
        for(int i = 0; i < COLUMNS; i++) {
            ColumnNode n = new ColumnNode(Integer.toString(i));
            columnNodes.add(n);
            headerNode = (ColumnNode) headerNode.linkRight(n);
        }
        
        headerNode = headerNode.right.column;
        
        for(int[] bGrid : grid) {
            DancingNode prev = null;
            for(int j = 0; j < COLUMNS; j++) {
                if(bGrid[j] == 1) {
                    ColumnNode colNode = columnNodes.get(j);
                    DancingNode newNode = new DancingNode(colNode);
                    if (prev == null) {
                        prev = newNode;
                    }
                    colNode.top.linkDown(newNode);
                    prev = prev.linkRight(newNode);
                    colNode.size++;
                }
            }
        }
        headerNode.size = COLUMNS;
        
        return headerNode;
    }
    
    private void solution(List<DancingNode> answer) {
        int[][] matrix = new int[size][size];
        
        for (DancingNode n : answer) {
            DancingNode rcNode = n;
            int min = Integer.parseInt(rcNode.column.name);
            for (DancingNode temp = n.right; temp != n; temp = temp.right) {
                int val = Integer.parseInt(temp.column.name);
                if (val < min) {
                    min = val;
                    rcNode = temp;
                }
            }
            int ans1 = Integer.parseInt(rcNode.column.name);
            int ans2 = Integer.parseInt(rcNode.right.column.name);
            int r = ans1 / size;
            int c = ans1 % size;
            
            int num = (ans2 % size) + 1;
            
            matrix[r][c] = num;
        }
        
        int index = 0;
        
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                board.get(index).setValue(anInt);
                index++;
            }
        }
        
        solved = true;
    }
} // end of class AlgorXSolver


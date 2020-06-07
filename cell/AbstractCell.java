package cell;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCell {
    
    protected int r, c, value;
    
    protected List<? extends AbstractCell> row = new ArrayList<>();
    protected List<? extends AbstractCell> col = new ArrayList<>();
    protected List<? extends AbstractCell> localGrid = new ArrayList<>();
    
    public AbstractCell(int r, int c, int value) {
        this.r = r;
        this.c = c;
        this.value = value;
    }
    
    public int getR() {
        return r;
    }
    
    public int getC() {
        return c;
    }
    
    public List<? extends AbstractCell> getGrid() {
        return localGrid;
    }
    
    public List<? extends AbstractCell> getRow() {
        return row;
    }
    
    public List<? extends AbstractCell> getColumn() {
        return col;
    }
    
    public boolean isEmpty() {
        return value == -1;
    }
    
    public abstract boolean isFinal();
    
    public void setRow(List<? extends AbstractCell> row) {
        this.row = row;
    }
    
    public void setCol(List<? extends AbstractCell> col) {
        this.col = col;
    }
    
    public void setGrid(List<? extends AbstractCell> nonet) {
        this.localGrid = nonet;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.format("%d,%d", r, c);
    }
    
    public abstract boolean isValid();
    
    public abstract void reset();
    
    protected boolean valid(List<? extends AbstractCell> cells) {
        return cells.stream().filter(s -> !this.equals(s))
                .noneMatch(s -> s.getValue() == this.value);
    }
    
    protected boolean isGridValid() {
        return valid(this.localGrid);
    }
    
    protected boolean isRowValid() {
        return valid(this.row);
    }
    
    protected boolean isColumnValid() {
        return valid(this.col);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Cell) {
            Cell other = (Cell) obj;
            return other.r == this.r && other.c == this.c;
        }
        return false;
    }
}

package cell;

public abstract class AbstractCell {
    
    protected int r, c, value;
    
    public AbstractCell(int r, int c, int value) {
        this.r = r;
        this.c = c;
        this.value = value;
    }
    
    public int getRow() {
        return r;
    }
    
    public int getColumn() {
        return c;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return String.format("%d,%d %d", r, c, value);
    }
    
    public abstract boolean isValid();
    
    public abstract void reset();
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Cell) {
            Cell other = (Cell) obj;
            return other.r == this.r && other.c == this.c;
        }
        return false;
    }
}

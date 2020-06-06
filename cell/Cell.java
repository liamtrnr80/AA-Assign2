package cell;

public class Cell extends AbstractCell implements Comparable<Cell> {
    
    private boolean isFinal;
    
    public Cell(int r, int c, int value) {
        super(r, c, value);
        isFinal = value != -1;
    }
    
    @Override
    public boolean isValid() {
        return false;
    }
    
    @Override
    public void reset() {
        setValue(-1);
    }
    
    @Override
    public int compareTo(Cell o) {
        int yComp = new Integer(r).compareTo(o.getRow());
        if(yComp == 0) {
            return new Integer(c).compareTo(o.getColumn());
        }
        return yComp;
    }
}

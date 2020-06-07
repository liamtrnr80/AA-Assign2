package cell;

public class Cell extends AbstractCell implements Comparable<Cell> {
    
    private boolean isFinal;
    
    public Cell(int r, int c, int value) {
        super(r, c, value);
        isFinal = value != -1;
    }
    
    public boolean isSet() {
        return this.value != -1;
    }
    
    @Override
    public boolean isFinal() {
        return isFinal;
    }
    
    public void setFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
    
    @Override
    public boolean isValid() {
        return isRowValid() && isColumnValid() && isGridValid();
    }
    
    @Override
    public void reset() {
        setValue(-1);
    }
    
    @Override
    public int compareTo(Cell o) {
        int yComp = new Integer(r).compareTo(o.getR());
        if(yComp == 0) {
            return new Integer(c).compareTo(o.getC());
        }
        return yComp;
    }
}

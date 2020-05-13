package grid;

public class Coordinate {
    
    protected int row;
    protected int column;
    protected int value;
    
    /**
     *  Construct coordinate (row, coloum)
     * @param r Row coordinate
     * @param c Column coordinate
     */
    public Coordinate(int r, int c) {
        this(r, c, 0);
    }
    
    /**
     * Construct coordinate (row, column)
     * @param r Row coordinate
     * @param c Column coordinate
     * @param val Cell value
     */
    public Coordinate(int r, int c, int val) {
        this.row = r;
        this.column = c;
        this.value = val;
    }
    
    /**
     * Default constructor
     */
    public Coordinate() {
        this(0, 0);
    }
    
    public int getRow() {
        return row;
    }
    
    public int getColumn() {
        return column;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return "value ";
    }
}

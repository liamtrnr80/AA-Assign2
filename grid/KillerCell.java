package grid;

import java.util.ArrayList;
import java.util.List;

public class KillerCell extends Cell {
    
    private List<KillerCell> cage;
    private int cageTotal;
    
    public KillerCell(int r, int c, int value) {
        super(r, c, value);
        cage = new ArrayList<>();
    }
    
    public void setTotal(int total) {
        this.cageTotal = total;
    }
    
    public void setCage(List<KillerCell> cage) {
        this.cage = cage;
    }
    
    @Override
    public boolean isValid() {
        return isCageValid() && super.isValid();
    }
    
    private boolean isCageValid() {
        return cage.stream().anyMatch(c -> c.getValue() == 0)
                || cageTotal == cage.stream().mapToInt(AbstractCell::getValue).sum();
    }
    
    @Override
    public boolean isFinal() {
        return false;
    }
}

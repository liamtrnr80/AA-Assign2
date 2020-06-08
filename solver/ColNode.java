package solver;

public class ColNode extends Node {
    public int size;
    public String name;
    
    public ColNode(String n) {
        super();
        size = 0;
        name = n;
        column = this;
    }
    
    public void cover() {
        removeLeftRight();
        
        for(Node i = bottom; i != this; i = i.bottom) {
            for(Node j = i.right; j != i; j = j.right) {
                j.removeTopBottom();
                j.column.size--;
            }
        }
    }
    
    public void uncover() {
        for(Node i = top; i != this; i = i.top) {
            for(Node j = i.left; j != i; j = j.left) {
                j.column.size++;
                j.reinsertTopBottom();
            }
        }
        
        reinsertLeftRight();
    }
}
package solver;

public class DancingNode {
    public DancingNode left, right, top, bottom;
    public ColumnNode column;
    
    public DancingNode() {
        left = right = top = bottom = this;
    }
    
    public DancingNode(ColumnNode c) {
        this();
        column = c;
    }
    
    DancingNode linkDown(DancingNode node) {
        assert (this.column == node.column);
        node.bottom = bottom;
        node.bottom.top = node;
        node.top = this;
        bottom = node;
        return node;
    }
    
    DancingNode linkRight(DancingNode node) {
        node.right = right;
        node.right.left = node;
        node.left = this;
        right = node;
        return node;
    }
    
    public void unlinkLeftRight() {
        left.right = right;
        right.left = left;
    }
    
    public void linkLeftRight() {
        left.right = this;
        right.left = this;
    }
    
    public void unlinkTopBottom() {
        top.bottom = bottom;
        bottom.top = top;
    }
    
    public void linkTopBottom() {
        top.bottom = this;
        bottom.top = this;
    }
}
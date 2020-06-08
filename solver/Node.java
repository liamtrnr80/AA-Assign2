package solver;

public class Node {
    public Node left, right, top, bottom;
    public ColNode column;
    
    public Node() {
        left = right = top = bottom = this;
    }
    
    public Node(ColNode c) {
        this();
        column = c;
    }
    
    public Node linkDown(Node node) {
        node.bottom = bottom;
        node.bottom.top = node;
        node.top = this;
        bottom = node;
        return node;
    }
    
    public Node linkRight(Node node) {
        node.right = right;
        node.right.right = node;
        node.left = this;
        right = node;
        return node;
    }
    
    public void removeLeftRight() {
        left.right = right;
        right.left = left;
    }
    
    public void reinsertLeftRight() {
        left.right = this;
        right.left = this;
    }
    
    public void removeTopBottom() {
        top.bottom = bottom;
        bottom.top = top;
    }
    
    public void reinsertTopBottom() {
        top.bottom = this;
        bottom.top = this;
    }
}
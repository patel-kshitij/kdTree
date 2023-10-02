// Node class for the kd-Tree.
public class Node{
    Point point; // The point of that node.

    Node left, right; // Nodes referring to the left child and right child of that node,

    // Constructor used to initialize a new Node
    Node(Point point){
        this.point = point;
        this.left = this.right = null;
    }
}
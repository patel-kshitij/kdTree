import java.util.Set;

class kdTree implements Searchable, TreeDebug {
    Node root = null;

    int expectedDimensionOfTree;  // This is the dimension of the point in the root node.

    public kdTree() {

    }

    /**
     * Creates a k-d tree with the given set of points. The built tree will be balanced.
     *
     * @param points -- A set of point. The point consists of coordinates of the point in an array.
     * @return True if the tree is properly built. Return false if some problem occurs.
     */
    @Override
    public boolean build(Set<Point> points) {
        if (points == null) {
            return false;
        }

        Point[] pointsArray = points.toArray(new Point[0]);


        return true;
    }


    /**
     * Adds a key to the kd-tree.
     *
     * @param point -- The point object to add as a key in a tree.
     * @return True if added and false if already in the tree or some problem occurs.
     */
    @Override
    public boolean add(Point point) {
        boolean result = true;
        boolean left = false; // Determines which child node to add the new node. i.e. left node or right node.
        int dimensionToCompare; // Determines which dimension needs to be compared. i.e. The index of the ArrayList.
        int currentDepth = 0; // Determines the current level of tree while traversing.
        int compareToResponse; // Response from the point.compareTo() function.
        Node currentNode, parentNode; // currentNode and the parentNode of that Node.

        if (point == null || point.dimension() == 0) {  // Basic sanity check.
            result = false;
        } else if (root == null) { // Special Case for root node.
            expectedDimensionOfTree = point.dimension();
            root = new Node(point);
        } else if (point.dimension() != expectedDimensionOfTree) { // Checking if the dimensions are same or not.
            result = false;
        } else {
            parentNode = root;
            currentNode = parentNode;
            while (currentNode != null) {

                dimensionToCompare = calculateDimensionToCompare(currentDepth, expectedDimensionOfTree);

                compareToResponse = point.compareTo(currentNode.point, dimensionToCompare);

                if (compareToResponse == 0) {
                    /*
                    Both the dimensions are equal.
                    If both the points are same then returns false.
                     */
                    result = false;
                    break;
                } else if (compareToResponse < 0) {
                    /*
                    Moving to the left node
                     */
                    parentNode = currentNode;
                    currentNode = parentNode.left;
                    left = true;
                } else {
                    /*
                    Moving to the right node
                     */
                    parentNode = currentNode;
                    currentNode = parentNode.right;
                    left = false;
                }

                currentDepth++;
            }
            if (result) {
                if (left) {
                    parentNode.left = new Node(point);
                } else {
                    parentNode.right = new Node(point);
                }
            }
        }

        return result;
    }

    @Override
    public boolean find(Point point) {
        boolean result = true;
        if (point == null || point.dimension() == 0) {  // Basic sanity check.
            result = false;
        } else {

        }

        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<Point> findInRange(Point minCorner, Point maxCorner) {
        return null;
    }

    @Override
    public String printTree() {
        return null;
    }

    /**
     * @param depth             -- The level of tree where the comparision is taking place.
     * @param dimensionOfPoints -- Total number of dimensions of the points.
     * @return The dimension across which to compare the points.
     */
    private Integer calculateDimensionToCompare(int depth, int dimensionOfPoints) {
        int result = depth % dimensionOfPoints;
        return result;
    }
}

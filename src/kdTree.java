import java.util.*;

class kdTree implements Searchable, TreeDebug {
    Node root = null;

    int sizeOfTheTree = 0;
    int ExpectedDimensionOfTree;  // This is the dimension of the point in the root node.

    int CompareToResponseForEqual = 0;

    public kdTree(int dimensions) {
        ExpectedDimensionOfTree = dimensions;
    }

    /**
     * Creates a k-d tree with the given set of points. The built tree will be balanced.
     *
     * @param points -- A set of point. The point consists of coordinates of the point in an array.
     * @return True if the tree is properly built. Return false if some problem occurs.
     */
    @Override
    public boolean build(Set<Point> points) {

        /* Whenever building make sure that the root is null and size is 0. Because we can call this function multiple times.
        And we can also call this function after calling add(). */
        root = null;
        sizeOfTheTree = 0;

        boolean result = true;
        boolean pointsRemaining = false;

        int leftHalfOfPointsArrayIndex = 0;
        int rightHalfOfPointsArrayIndex = 1;
        int nextSplitIndex;
        int dimensionToCompare = 0;


        Point[][] splitPoints; // The array of points which are supposed to be split.
        Point[][] nextSplitPoints; // The array of split points
        Point[][] sortAndSplitResponse; // The response provided by calling sortAndSplitArray();

        if (points == null || points.isEmpty()) { // Sanity check
            result = false;
        } else {

            Point[] pointsArray = points.toArray(new Point[0]); // Creating an array of points as a constraint not to use data-structure from collections.
            splitPoints = sortAndSplitArray(pointsArray, dimensionToCompare);
            if (!add(pointsArray[splitPoints[leftHalfOfPointsArrayIndex].length - 1])) { // If adding fails then return value becomes false.
                result = false;
            }

            // removes last element from the splitPints as that point will be the median.
            splitPoints[leftHalfOfPointsArrayIndex] = removeLastElement(splitPoints[leftHalfOfPointsArrayIndex]);

            dimensionToCompare++;

            for (int i = 2; ; i = i * 2) {
                nextSplitPoints = new Point[i * 2][];
                nextSplitIndex = 0;

                for (int j = 0; j < splitPoints.length; j++) {
                    if (splitPoints[j].length != 0) {
                        sortAndSplitResponse = sortAndSplitArray(splitPoints[j], dimensionToCompare);

                        if (!add(sortAndSplitResponse[leftHalfOfPointsArrayIndex][sortAndSplitResponse[leftHalfOfPointsArrayIndex].length - 1])) {
                            pointsRemaining = false;
                            result = false;
                            break;
                        }

                        sortAndSplitResponse[leftHalfOfPointsArrayIndex] = removeLastElement(sortAndSplitResponse[leftHalfOfPointsArrayIndex]);

                        if (sortAndSplitResponse[leftHalfOfPointsArrayIndex].length != 0 || sortAndSplitResponse[rightHalfOfPointsArrayIndex].length != 0) {
                            pointsRemaining = true;
                        }

                        nextSplitPoints[nextSplitIndex] = sortAndSplitResponse[leftHalfOfPointsArrayIndex];
                        nextSplitPoints[nextSplitIndex + 1] = sortAndSplitResponse[rightHalfOfPointsArrayIndex];
                        nextSplitIndex += 2;
                    }

                }

                if (!pointsRemaining) {
                    // If there are no more points remaining then break the loop.
                    break;
                }

                splitPoints = nextSplitPoints;
                dimensionToCompare++;
                pointsRemaining = false;

            }
        }

        return result;
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

        if (point == null || point.dimension() == 0 || point.dimension() != ExpectedDimensionOfTree) {  // Basic sanity check.
            result = false;
        } else if (root == null) { // Special Case for root node.
            root = new Node(point);
            sizeOfTheTree++;
        } else {
            currentNode = parentNode = root;

            while (currentNode != null) {

                dimensionToCompare = calculateDimensionToCompare(currentDepth, ExpectedDimensionOfTree);

                compareToResponse = point.compareTo(currentNode.point, dimensionToCompare);

                if (compareToResponse == CompareToResponseForEqual) {
                     /*
                     Both the dimensions are equal.
                     If both the points are same then returns false otherwise move to the left child node.
                      */
                    if (point.getCoordinates().equals(currentNode.point.getCoordinates())) { // TODO: Confirm if .equal breaks the not using Collections constraint.
                        result = false;
                        break;
                    }
                    parentNode = currentNode;
                    currentNode = parentNode.left;
                    left = true;
                } else if (compareToResponse < CompareToResponseForEqual) {
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
                sizeOfTheTree++;
            }
        }

        return result;
    }

    @Override
    public boolean find(Point point) {
        boolean result = false;
        int dimensionToCompare; // Determines which dimension needs to be compared. i.e. The index of the ArrayList.
        int currentDepth = 0; // Determines the current level of tree while traversing.
        int compareToResponse; // Response from the point.compareTo() function.
        Node currentNode; // currentNode and the parentNode of that Node.


        if (point == null || point.dimension() == 0 || point.dimension() != ExpectedDimensionOfTree) {  // Basic sanity check.
            result = false;
        } else {
            currentNode = root;

            while (currentNode != null) {

                dimensionToCompare = calculateDimensionToCompare(currentDepth, ExpectedDimensionOfTree);

                compareToResponse = point.compareTo(currentNode.point, dimensionToCompare);

                if (compareToResponse == CompareToResponseForEqual) {
                    /*
                    Both the dimensions are equal.
                    If both the points are same then returns false.
                     */
                    if (point.getCoordinates().equals(currentNode.point.getCoordinates())) {
                        result = true;
                    }
                    currentNode = currentNode.left;
                } else if (compareToResponse < CompareToResponseForEqual) {
                    /*
                    Moving to the left node
                     */
                    currentNode = currentNode.left;
                } else {
                    /*
                    Moving to the right node
                     */
                    currentNode = currentNode.right;
                }

                currentDepth++;
            }
        }

        return result;
    }

    @Override
    public int size() {
        return sizeOfTheTree;
    }

    @Override
    public Set<Point> findInRange(Point minCorner, Point maxCorner) {

        Point[] getMinMaxPointsResponse;

        Set<Point> result = new HashSet<>();

        int currentDepthOfTree = 0;
        Node currentNode;
        Node[] nextNodes;
        Node[] nodes = new Node[2];

        int nextNodesIndex;
        boolean nodesRemaining;

        int leftHalfOfNodesIndex = 0;
        int rightHalfOfNodesIndex = 1;

        if (minCorner == null || maxCorner == null || minCorner.dimension() != ExpectedDimensionOfTree || maxCorner.dimension() != ExpectedDimensionOfTree) {
            result = null;
        } else {

            // A function to fix if some coordinates of minCorner is larger than the corresponding coordinates of maxCorner;
            getMinMaxPointsResponse = getMinMaxPoints(minCorner, maxCorner);

            int dimensionToCompare = calculateDimensionToCompare(currentDepthOfTree, ExpectedDimensionOfTree);

            Point minPoint = getMinMaxPointsResponse[0];
            Point maxPoint = getMinMaxPointsResponse[1];

            if (root != null) {


                if (root.point.isInRange(minPoint, maxPoint)) {
                    result.add(root.point);
                    if (root.left != null) {
                        nodes[leftHalfOfNodesIndex] = root.left;
                    }
                    if (root.right != null) {
                        nodes[rightHalfOfNodesIndex] = root.right;
                    }
                } else {
                    if (root.left != null && root.point.compareTo(minPoint, dimensionToCompare) > CompareToResponseForEqual) {
                        nodes[leftHalfOfNodesIndex] = root.left;
                    }
                    if (root.right != null && root.point.compareTo(maxPoint, dimensionToCompare) < CompareToResponseForEqual) {
                        nodes[leftHalfOfNodesIndex] = root.right;
                    }
                }

                currentDepthOfTree++;
            }

            for (int i = 2; ; i = nodes.length * 2) {
                nextNodes = new Node[i * 2];
                nodesRemaining = false;
                nextNodesIndex = 0;
                for (int j = 0; j < nodes.length; j++) {
                    if (nodes[j] != null) {
                        currentNode = nodes[j];
                        if (currentNode.point.isInRange(minPoint, maxPoint)) {
                            result.add(currentNode.point);
                        }
                        if (currentNode.left != null && currentNode.point.compareTo(minPoint, dimensionToCompare) > CompareToResponseForEqual) {
                            nextNodes[nextNodesIndex] = currentNode.left;
                            nodesRemaining = true;
                            nextNodesIndex++;
                        }
                        if (currentNode.right != null && currentNode.point.compareTo(maxPoint, dimensionToCompare) < CompareToResponseForEqual) {
                            nextNodes[nextNodesIndex] = currentNode.right;
                            nodesRemaining = true;
                            nextNodesIndex++;
                        }
                    }
                }

                nodes = Arrays.copyOfRange(nextNodes, 0, nextNodes.length);
                currentDepthOfTree++;
                dimensionToCompare = calculateDimensionToCompare(currentDepthOfTree, ExpectedDimensionOfTree);

                if (!nodesRemaining) {
                    break;
                }
            }
        }

        return result;
    }


    private Point[] getMinMaxPoints(Point point1, Point point2) {
        List coordinatesOfPoint1 = new ArrayList<>(point1.getCoordinates());
        List coordinatesOfPoint2 = new ArrayList<>(point2.getCoordinates());

        List minCoordinates = new ArrayList();
        List maxCoordinates = new ArrayList();

        Point[] result = new Point[2];

        for (int i = 0; i < coordinatesOfPoint1.size(); i++) {
            if (point1.compareTo(point2, i) < CompareToResponseForEqual) {
                minCoordinates.add(coordinatesOfPoint1.get(i));
                maxCoordinates.add(coordinatesOfPoint2.get(i));
            } else {
                minCoordinates.add(coordinatesOfPoint2.get(i));
                maxCoordinates.add(coordinatesOfPoint1.get(i));
            }
        }

        result[0] = new Point<>(minCoordinates);
        result[1] = new Point<>(maxCoordinates);

        return result;
    }

    /**
     * Creates a string with all the nodes in the tree.
     * For each value in the tree (reported in breadth-first order of the tree),
     * print the point’s coordinates separated by space, a space, and then the depth of the node in the tree.
     * Separate each value-depth pair with a carriage return (\n). The root is at depth 1.
     *
     * @return A string of all the nodes and the coordinates with the depth value or null string if any error occurs;
     */
    @Override
    public String printTree() {

        StringBuilder result = new StringBuilder();

        int currentDepthOfTree = 1;
        Node currentNode;
        Node[] nextNodes;
        Node[] nodes = new Node[2];

        int nextNodesIndex;
        boolean nodesRemaining = false;

        int leftHalfOfNodesIndex = 0;
        int rightHalfOfNodesIndex = 1;

        if (root != null) {
            result.append(root.point.getCoordinatesString());
            result.append(currentDepthOfTree);
            result.append("\n");
            currentDepthOfTree++;

            if (root.left != null) {
                nodes[leftHalfOfNodesIndex] = root.left;
            }
            if (root.right != null) {
                nodes[rightHalfOfNodesIndex] = root.right;
            }


            for (int i = 2; ; i = nodes.length * 2) {
                nextNodes = new Node[i * 2];
                nodesRemaining = false;
                nextNodesIndex = 0;
                for (int j = 0; j < nodes.length; j++) {
                    if (nodes[j] != null) {
                        currentNode = nodes[j];
                        result.append(currentNode.point.getCoordinatesString());
                        result.append(currentDepthOfTree);
                        result.append("\n");

                        if (currentNode.left != null) {
                            nextNodes[nextNodesIndex] = currentNode.left;
                            nextNodesIndex++;
                            nodesRemaining = true;
                        }
                        if (currentNode.right != null) {
                            nextNodes[nextNodesIndex] = currentNode.right;
                            nextNodesIndex++;
                            nodesRemaining = true;
                        }
                    }

                }

                nodes = nextNodes;
                currentDepthOfTree++;
                if (!nodesRemaining) {
                    break;
                }
            }
        }


        return result.toString();
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


    /**
     * @param pointsArray -- The array of points to sort.
     * @return -- Returns 2D Array of size 2 with 1st one being the left half of the split array and 2nd one being right of the array.
     */
    private Point[][] sortAndSplitArray(Point[] pointsArray, int dimension) {

        int compareToResponse;
        int pointArraysLength = pointsArray.length;
        int splittingPoint = pointArraysLength / 2;
        int leftHalfOfPointsArrayIndex = 0;
        int rightHalfOfPointsArrayIndex = 1;

        Point temp;  // Temporary variable to use for swap.
        Point[][] result;

        boolean swapped; // A flag used for early breaking of the bubble sort. // Reference taken from https://www.geeksforgeeks.org/bubble-sort/

        // A simple bubble sort to sort the array
        for (int i = 0; i < pointArraysLength - 1; i++) {
            swapped = false;
            for (int j = 0; j < pointArraysLength - i - 1; j++) {
                compareToResponse = pointsArray[j].compareTo(pointsArray[j + 1], dimension);

                if (compareToResponse > CompareToResponseForEqual) {
                    temp = new Point<>(pointsArray[j]);
                    pointsArray[j] = new Point<>(pointsArray[j + 1]);
                    pointsArray[j + 1] = new Point<>(temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        // Splitting the array
        if (pointArraysLength % 2 == 0) {
            result = new Point[2][splittingPoint];
            result[leftHalfOfPointsArrayIndex] = Arrays.copyOfRange(pointsArray, 0, pointArraysLength / 2);
            result[rightHalfOfPointsArrayIndex] = Arrays.copyOfRange(pointsArray, pointArraysLength / 2, pointArraysLength);
        } else {
            result = new Point[2][splittingPoint + 1];
            result[leftHalfOfPointsArrayIndex] = Arrays.copyOfRange(pointsArray, 0, pointArraysLength / 2 + 1);
            result[rightHalfOfPointsArrayIndex] = Arrays.copyOfRange(pointsArray, (pointArraysLength + 1) / 2, pointArraysLength);
        }

        return result;
    }

    /**
     * Removes the last element from the array
     *
     * @param pointsArray -- The Array of points from which the last element is supposed to be removed.
     * @return Array of points with the last point removed.
     */
    private Point[] removeLastElement(Point[] pointsArray) {
        Point[] result;
        int pointArraysLength = pointsArray.length;

        // Copying every element except the last one.
        result = Arrays.copyOfRange(pointsArray, 0, pointArraysLength - 1);

        return result;
    }
}

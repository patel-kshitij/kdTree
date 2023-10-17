import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class sizeTest {
    private static final int TestDimension = 2;

    @Test
    void nullSetTest() {
        kdTree tree = new kdTree(TestDimension );

        assertEquals(0, tree.size( ), "size with empty tree" );
    }

    @Test
    void singleNodeTree() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(0, 0)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertEquals(treePoints.size(), tree.size( ), "size with just one value in the tree" );
    }

    @Test
    void balancedTree() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(3, 4)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(7, 1)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(1, 15)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(6, 13)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(9, 7)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(15, 3)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 11)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(14, 12)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertEquals(treePoints.size(), tree.size( ), "balanced tree size" );
    }

    @Test
    void linkedListTree() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 11)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertEquals(treePoints.size(), tree.size( ), "linked list tree size" );
    }

    @Test
    void sizeBuildTree() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(6, 13)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 11)) ) );

        assertTrue(tree.build(treePoints), "building the tree");
        assertEquals(treePoints.size(), tree.size( ), "tree size build by build()" );
    }

    @Test
    void sizeAddAndBuildTree() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );

        assertTrue(tree.build(treePoints), "building the tree");
        Point newPoint = new Point( new ArrayList<>(Arrays.asList(9, 7)) );
        assertTrue(tree.add(newPoint), "add a new point to the tree");

        assertEquals(treePoints.size()+1, tree.size( ), "tree size built by build() and add()" );
    }

}
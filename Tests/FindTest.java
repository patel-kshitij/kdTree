import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class findTest {
    private static final int TestDimension = 2;

    @Test
    void nullPointTest() {
        kdTree tree = new kdTree(TestDimension );

        assertFalse( tree.find( null ), "find with null point" );
    }

    @Test
    void differentDimensionPoint() {
        kdTree tree = new kdTree(TestDimension );
        Point<Integer> p = new Point( new ArrayList<>(Arrays.asList(1, 2, 3)) );

        assertFalse( tree.find( p ), "find with wrong dimension of point" );
    }

    @Test
    void findRoot() {
        Searchable tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertTrue( tree.find( treePoints.get(0) ), "find root, original point object" );

        Point<Integer> searchPoint = new Point( new ArrayList<>(Arrays.asList(8, 8)) );
        assertTrue( tree.find( searchPoint ), "find root, new point object" );
    }

    @Test
    void findLeaf() {
        Searchable tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertTrue( tree.find( treePoints.get(0) ), "find leftmost leaf, original point object" );
        assertTrue( tree.find( treePoints.get(1) ), "find middle left leaf, original point object" );
        assertTrue( tree.find( treePoints.get(2) ), "find middle right leaf, original point object" );
        assertTrue( tree.find( treePoints.get(3) ), "find rightmost leaf, original point object" );

        Point<Integer> leftSearch = new Point( new ArrayList<>(Arrays.asList(4, 2)) );
        Point<Integer> rightSearch = new Point( new ArrayList<>(Arrays.asList(11, 14)) );
        assertTrue( tree.find( leftSearch ), "find left leaf, new point object" );
        assertTrue( tree.find( rightSearch ), "find right leaf, new point object" );
    }

    @Test
    void findInEmptyTree() {
        Searchable tree = new kdTree( TestDimension );

        Point<Integer> searchPoint = new Point( new ArrayList<>(Arrays.asList(4, 2)) );
        assertFalse( tree.find( searchPoint ), "find in an empty tree" );
    }

    @Test
    void findInLevelsWithDifferentComparisionDimensions() {
        Searchable tree = new kdTree( TestDimension );
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

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        Point<Integer> level2Search = new Point( new ArrayList<>(Arrays.asList(12, 9)) );
        Point<Integer> level3Search = new Point( new ArrayList<>(Arrays.asList(4, 2)) );
        assertTrue( tree.find( level2Search ), "find node at level 2" );
        assertTrue( tree.find( level3Search ), "find node at level 3" );
    }

    @Test
    void findForNotInTree() {
        Searchable tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        Point<Integer> smallerThanTree = new Point( new ArrayList<>(Arrays.asList(0, 0)) );
        Point<Integer> biggerThanTree = new Point( new ArrayList<>(Arrays.asList(100, 100)) );
        Point<Integer> inRangeOfTree = new Point( new ArrayList<>(Arrays.asList(8, 6)) );
        assertFalse( tree.find( smallerThanTree ), "smaller than all in tree" );
        assertFalse( tree.find( biggerThanTree ), "bigger than all in tree" );
        assertFalse( tree.find( inRangeOfTree ), "in the range of the tree" );
    }

    @Test
    void findOneLevelTree() {
        Searchable tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point<Integer> inTree = new Point( new ArrayList<>(Arrays.asList(8, 8)) );
        Point<Integer> smallerThanTree = new Point( new ArrayList<>(Arrays.asList(0, 0)) );
        Point<Integer> biggerThanTree = new Point( new ArrayList<>(Arrays.asList(100, 100)) );
        Point<Integer> inRangeOfTree = new Point( new ArrayList<>(Arrays.asList(8, 6)) );
        assertTrue( tree.find( inTree ), "the only node in the tree" );
        assertFalse( tree.find( smallerThanTree ), "smaller than all in tree" );
        assertFalse( tree.find( biggerThanTree ), "bigger than all in tree" );
        assertFalse( tree.find( inRangeOfTree ), "in the range of the tree" );
    }

    @Test
    void checkXYdimensionChange1Level2() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(0, 0)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point searchPoint =  new Point( new ArrayList<>(Arrays.asList(12, 5)) );
        assertTrue( tree.find( searchPoint ) );
    }

    @Test
    void checkXYdimensionChange2Level2() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 12)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(100, 100)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point searchPoint =  new Point( new ArrayList<>(Arrays.asList(5, 12)) );
        assertTrue( tree.find( searchPoint ) );
    }

    @Test
    void checkXYdimensionChange1Level3() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(0, 0)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point searchPoint =  new Point( new ArrayList<>(Arrays.asList(2, 10)) );
        assertTrue( tree.find( searchPoint ) );
    }

    @Test
    void checkXYdimensionChange2Level3() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(100, 100)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point searchPoint =  new Point( new ArrayList<>(Arrays.asList(13, 5)) );
        assertTrue( tree.find( searchPoint ) );
    }

    @Test
    void searchBuildTree() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );

        assertTrue(tree.build(treePoints), "building the tree");

        Point searchPoint =  new Point( new ArrayList<>(Arrays.asList(8, 8)) );
        assertTrue( tree.find( searchPoint ) );
        searchPoint =  new Point( new ArrayList<>(Arrays.asList(5, 6)) );
        assertTrue( tree.find( searchPoint ) );
        searchPoint =  new Point( new ArrayList<>(Arrays.asList(12, 9)) );
        assertTrue( tree.find( searchPoint ) );
        searchPoint =  new Point( new ArrayList<>(Arrays.asList(13, 5)) );
        assertTrue( tree.find( searchPoint ) );
        searchPoint =  new Point( new ArrayList<>(Arrays.asList(1, 2)) );
        assertFalse( tree.find( searchPoint ) );
    }

    @Test
    void searchAddAndBuildTree() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );

        assertTrue(tree.build(treePoints), "building the tree");
        Point newPoint = new Point( new ArrayList<>(Arrays.asList(9, 7)) );
        assertTrue(tree.add(newPoint), "add a new point to the tree");

        Point searchPoint =  new Point( new ArrayList<>(Arrays.asList(9, 7)) );
        assertTrue( tree.find( searchPoint ) );
        searchPoint =  new Point( new ArrayList<>(Arrays.asList(5, 6)) );
        assertTrue( tree.find( searchPoint ) );
        searchPoint =  new Point( new ArrayList<>(Arrays.asList(1, 2)) );
        assertFalse( tree.find( searchPoint ) );
    }

}
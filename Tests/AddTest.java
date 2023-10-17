import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class addTest {
    private static final int TestDimension = 2;

    @Test
    void nullPointTest() {
        Searchable tree = new kdTree(TestDimension );

        assertFalse( tree.add( null ), "add with null point" );
    }

    @Test
    void differentDimensionPoint() {
        Searchable tree = new kdTree(TestDimension );
        Point<Integer> p = new Point( new ArrayList<>(Arrays.asList(1, 2, 3)) );

        assertFalse( tree.add( p ), "add with wrong dimension of point" );
    }

    @Test
    void addToEmptyTree() {
        TreeDebug tree = new kdTree(TestDimension );
        Point<Integer> p = new Point( new ArrayList<>(Arrays.asList(1, 1)) );

        assertTrue( tree.add( p ), "add to empty tree" );
        assertEquals( "1 1 1\n", tree.printTree( ), "print add to empty tree" );
    }

    @Test
    void addLeftOfRoot() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertEquals( "8 8 1\n5 6 2\n", tree.printTree( ), "add a left child to the root" );
    }

    @Test
    void addRightOfRoot() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertEquals( "8 8 1\n12 9 2\n", tree.printTree( ), "three levels, but missing inside children on one level" );
    }

    @Test
    void addMakesLinkedList() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(6, 13)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 12)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertEquals( "8 8 1\n5 6 2\n2 10 3\n6 13 4\n4 12 5\n", tree.printTree( ), "several levels as linked list" );
    }

    @Test
    void addLeftChildChain() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(3, 4)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertEquals( "8 8 1\n5 6 2\n4 2 3\n3 4 4\n", tree.printTree( ), "left child chain" );
    }

    @Test
    void addRightChildChain() {
        TreeDebug tree = new kdTree( TestDimension );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(14, 12)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }
        assertEquals( "8 8 1\n12 9 2\n11 14 3\n14 12 4\n", tree.printTree( ), "right child chain" );
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
        assertEquals( "8 8 1\n0 0 2\n12 5 2\n", tree.printTree( ), "check XY change" );
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
        assertEquals( "8 8 1\n5 12 2\n100 100 2\n", tree.printTree( ), "check XY change" );
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
        assertEquals( "8 8 1\n5 6 2\n12 9 2\n0 0 3\n2 10 3\n", tree.printTree( ), "check XY change" );
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
        assertEquals( "8 8 1\n5 6 2\n12 9 2\n13 5 3\n100 100 3\n", tree.printTree( ), "check XY change" );
    }

    @Test
    void addAfterBuild() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5)) ) );

        assertTrue(tree.build(treePoints), "building the tree");
        Point newPoint = new Point( new ArrayList<>(Arrays.asList(9, 7)) );
        assertTrue(tree.add(newPoint), "add a new point to the tree");

        assertEquals( "8 8 1\n5 6 2\n13 5 2\n12 9 3\n9 7 4\n", tree.printTree( ), "add after build" );
    }

}
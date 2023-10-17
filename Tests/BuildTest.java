import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class buildTest {

    private static final int TestDimension = 2;

    @Test
    void nullSetTest() {
        kdTree tree = new kdTree(TestDimension );

        assertFalse( tree.build( null ), "build with null set" );
    }

    @Test
    void differentDimensionPoints() {
        kdTree tree = new kdTree(TestDimension );
        Set<Point> pointSet = new HashSet<>();
        Point<Integer> p = new Point( new ArrayList<>(Arrays.asList(1, 2, 3)) );
        pointSet.add( p );

        assertFalse( tree.build( pointSet ), "build with wrong dimension of points" );
    }

    @Test
    void setWithNullPoint() {
        kdTree tree = new kdTree(TestDimension );
        Set<Point> pointSet = new HashSet<>();
        Point<Integer> p = new Point( new ArrayList<>(Arrays.asList(0, 0)) );
        pointSet.add( p );
        pointSet.add( null );

        assertFalse( tree.build( pointSet ), "build with null point" );
    }

    @Test
    void emptySet() {
        kdTree tree = new kdTree(TestDimension );
        Set<Point> pointSet = new HashSet<>();

        assertTrue( tree.build( pointSet ), "build with empty set of points" );
    }

    @Test
    void buildTreeOnePoint() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );

        assertTrue(tree.build(treePoints), "build the tree" );
        assertEquals( "8 8 1\n", tree.printTree( ), "build tree on one point" );
    }

    @Test
    void buildTreeManyPoints() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
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

        assertTrue(tree.build(treePoints), "build the tree" );
        assertEquals( "8 8 1\n5 6 2\n12 9 2\n4 2 3\n2 10 3\n13 5 3\n11 14 3\n3 4 4\n7 1 4\n1 15 4\n6 13 4\n9 7 4\n15 3 4\n10 11 4\n14 12 4\n", tree.printTree( ), "build tree on many points" );
    }

    @Test
    void buildTreeMediansAreOdd() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
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

        assertTrue(tree.build(treePoints), "build the tree" );
        assertEquals( "7 1 1\n5 6 2\n8 8 2\n3 4 3\n2 10 3\n13 5 3\n11 14 3\n4 2 4\n1 15 4\n6 13 4\n9 7 4\n15 3 4\n10 11 4\n12 9 4\n", tree.printTree( ), "build tree on points where median split is not even points" );
    }

    @Test
    void buildTree3Points() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(6, 13)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(9, 7)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(15, 3)) ) );

        assertTrue(tree.build(treePoints), "build the tree" );
        assertEquals( "9 7 1\n6 13 2\n15 3 2\n", tree.printTree( ), "build tree on 3 points" );
    }

    @Test
    void buildQuadrants2And4() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(0, 0)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(-3, 1)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(-2, 3)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(-1, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(1, -2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, -3)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(3, -1)) ) );

        assertTrue(tree.build(treePoints), "build the tree" );
        assertEquals( "0 0 1\n-1 2 2\n1 -2 2\n-3 1 3\n-2 3 3\n2 -3 3\n3 -1 3\n", tree.printTree( ), "build only two quadrants" );
    }

    @Test
    void buildOverABuild() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(20, 1500)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(750, 0)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(1000, -1000)) ) );

        assertTrue(tree.build(treePoints), "build the tree" );

        Set<Point> treePoints2 = new HashSet<>();
        treePoints2.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints2.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints2.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );

        assertTrue(tree.build(treePoints2), "rebuild" );

        assertEquals( "8 8 1\n5 6 2\n12 9 2\n", tree.printTree( ), "build over build" );
        assertEquals( 3, tree.size( ), "build over build size" );
    }

}
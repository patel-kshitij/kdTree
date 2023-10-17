import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class findInRangeTest {
    private static final int TestDimension = 2;

    @Test
    void nullMinCornerTest() {
        kdTree tree = new kdTree(TestDimension );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(1, 1)) );

        assertNull( tree.findInRange( null, maxCorner ), "findInRange with null min corner" );
    }

    @Test
    void nullMaxCornerTest() {
        kdTree tree = new kdTree(TestDimension );
        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(0, 0)) );

        assertNull( tree.findInRange( minCorner, null ), "findInRange with null max corner" );
    }


    @Test
    void nullDiagonal() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(1, 1)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(1, 1)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with null diagonal and not on a point" );
        assertEquals( 0, outcome.size(), "size where null diagonal not on a point");

        minCorner = new Point( new ArrayList<>(Arrays.asList(5, 6)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(5, 6)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with null diagonal and on a point" );
        assertEquals( 1, outcome.size(), "size where null diagonal on a point");

    }

    @Test
    void oneCoordinateDifferenceInRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 4)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(8, 0)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(8, 7)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with vertical line rectangle and not on a point" );
        assertEquals( 0, outcome.size(), "size where vertical line rectangle not on a point");

        minCorner = new Point( new ArrayList<>(Arrays.asList(-1, 7)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(9, 7)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with horizontal line rectangle and not on a point" );
        assertEquals( 0, outcome.size(), "size where horizontal line rectangle not on a point");

        minCorner = new Point( new ArrayList<>(Arrays.asList(11, 2)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(11, 5)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with vertical line rectangle and on a point" );
        assertEquals( 1, outcome.size(), "size where vertical line on a point");

        minCorner = new Point( new ArrayList<>(Arrays.asList(10, 9)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(15, 9)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with horizontal line rectangle and on a point" );
        assertEquals( 1, outcome.size(), "size where horizontal line on a point");
    }

    @Test
    void emptyQueryRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(6, 1)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(10, 7)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with empty rectangle" );
        assertEquals( 0, outcome.size(), "size where empty rectangle");

        minCorner = new Point( new ArrayList<>(Arrays.asList(16, -2)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(20, 25)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with empty rectangle out of point range" );
        assertEquals( 0, outcome.size(), "size where empty rectangle out of point range");

    }

    @Test
    void rectangleEdgesAndCorners() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        // left edge
        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(8, 7)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(12, 9)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with point on left edge" );
        assertEquals( 1, outcome.size(), "size where point on left edge");

        // top edge
        minCorner = new Point( new ArrayList<>(Arrays.asList(6, 2)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(10, 8)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with point on top edge" );
        assertEquals( 1, outcome.size(), "size where point on top edge");

        // right edge
        minCorner = new Point( new ArrayList<>(Arrays.asList(6, 2)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(8, 10)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with point on right edge" );
        assertEquals( 1, outcome.size(), "size where point on right edge");

        // bottom edge
        minCorner = new Point( new ArrayList<>(Arrays.asList(6, 8)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(10, 12)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with point on bottom edge" );
        assertEquals( 1, outcome.size(), "size where point on bottom edge");

        // min upper corner
//        minCorner = new Point( new ArrayList<>(Arrays.asList(4, 1)) );
//        maxCorner = new Point( new ArrayList<>(Arrays.asList(10, 2)) );
//
//        outcome = tree.findInRange( minCorner, maxCorner );
//        assertNotNull( outcome, "findInRange with point on upper min corner" );
//        assertEquals( 1, outcome.size(), "size where point on upper min corner");

//        // max lower corner
//        minCorner = new Point( new ArrayList<>(Arrays.asList(9, 9)) );
//        maxCorner = new Point( new ArrayList<>(Arrays.asList(12, 15)) );
//
//        outcome = tree.findInRange( minCorner, maxCorner );
//        assertNotNull( outcome, "findInRange with point on lower max corner" );
//        assertEquals( 2, outcome.size(), "size where point on lower max corner");

    }

    @Test
    void rootAndChildrenInRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(3, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 4)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(4, 1)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(12, 9)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with empty rectangle" );
        assertEquals( 3, outcome.size(), "size where empty rectangle");
    }

    @Test
    void rootRightOfRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(3, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(1, 1)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(7, 12)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with root right of rectangle" );
        assertEquals( 2, outcome.size(), "size where root right of rectangle");
    }

    @Test
    void rootLeftOfRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(3, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(11, 14)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(9, 1)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(15, 12)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with root left of rectangle" );
        assertEquals( 1, outcome.size(), "size where root left of rectangle");
    }

    @Test
    void yDivideInRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(80, 80)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(50, 60)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(120, 90)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(40, 20)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(20, 100)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(130, 50)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(110, 140)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(30, 40)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(70, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 150)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(60, 130)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(90, 70)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(150, 30)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(100, 110)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(140, 120)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        // left side of root
        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(15, 15)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(75, 135)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with y inside of rectangle" );
        assertEquals( 5, outcome.size(), "size where root y inside of rectangle");

        // right side of root
        minCorner = new Point( new ArrayList<>(Arrays.asList(85, 35)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(200, 135)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with y inside of rectangle" );
        assertEquals( 5, outcome.size(), "size where root y inside of rectangle");

    }

    @Test
    void yDivideAboveRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(80, 80)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(50, 60)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(120, 90)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(40, 20)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(20, 100)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(130, 50)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(110, 140)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(30, 40)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(70, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 150)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(60, 130)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(90, 70)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(150, 30)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(100, 110)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(140, 120)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        // left side of root
        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(-10, 85)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(75, 200)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with y below rectangle" );
        assertEquals( 3, outcome.size(), "size where root y below rectangle");
    }

    @Test
    void yDivideBelowRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(80, 80)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(50, 60)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(120, 90)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(40, 20)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(20, 100)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(130, 50)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(110, 140)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(30, 40)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(70, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 150)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(60, 130)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(90, 70)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(150, 30)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(100, 110)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(140, 120)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        // left side of root
        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(85, 0)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(190, 75)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with y above rectangle" );
        assertEquals( 3, outcome.size(), "size where root y above rectangle");
    }

    @Test
    void allTreeInRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(80, 80)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(50, 60)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(120, 90)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(40, 20)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(20, 100)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(130, 50)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(110, 140)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(30, 40)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(70, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 150)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(60, 130)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(90, 70)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(150, 30)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(100, 110)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(140, 120)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        // left side of root
        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(0, 0)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(190, 250)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with all tree" );
        assertEquals( 15, outcome.size(), "size where all tree in rectangle");
    }

    @Test
    void IsolatedNodeInRectangle() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(80, 80)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(50, 60)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(120, 90)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(40, 20)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(20, 100)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(130, 50)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(110, 140)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(30, 40)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(70, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 150)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(60, 130)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(90, 70)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(150, 30)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(100, 110)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(140, 120)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        // leaves
        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(55, 125)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(65, 135)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with leaf in rectangle" );
        assertEquals( 1, outcome.size(), "size where leaf in rectangle");

        minCorner = new Point( new ArrayList<>(Arrays.asList(85, 65)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(95, 75)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with leaf2 in rectangle" );
        assertEquals( 1, outcome.size(), "size where leaf2 in rectangle");

        // middle nodes
        minCorner = new Point( new ArrayList<>(Arrays.asList(35, 15)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(45, 25)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with level 3 in rectangle" );
        assertEquals( 1, outcome.size(), "size where level 3 in rectangle");

        minCorner = new Point( new ArrayList<>(Arrays.asList(115, 85)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(125, 95)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with level 2 in rectangle" );
        assertEquals( 1, outcome.size(), "size where level 2 in rectangle");


    }

    @Test
    void rootAlone() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(6, 1)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(12, 15)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with just one level" );
        assertEquals( 1, outcome.size(), "size where just one level");

        minCorner = new Point( new ArrayList<>(Arrays.asList(0, 0)) );
        maxCorner = new Point( new ArrayList<>(Arrays.asList(4, 4)) );

        outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with one level no match" );
        assertEquals( 0, outcome.size(), "size where one level no match");

    }

    @Test
    void fourLevelCheck() {
        Searchable tree = new kdTree(TestDimension );

        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(80, 80)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(50, 60)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(120, 90)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(40, 20)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(20, 100)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(130, 50)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(110, 140)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(30, 40)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(70, 10)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 150)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(60, 130)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(90, 70)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(150, 30)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(100, 110)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(140, 120)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(15, 15)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(145, 145)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with four levels" );
        assertEquals( 12, outcome.size(), "size where four levels");
    }

    @Test
    void rangeBuildTree() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(80, 80)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(50, 60)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(120, 90)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(130, 50)) ) );

        assertTrue(tree.build(treePoints), "building the tree");

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(45, 35)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(125, 85)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with build alone" );
        assertEquals( 2, outcome.size(), "size where build alone");
    }

    @Test
    void rangeAddAndBuildTree() {
        TreeDebug tree = new kdTree( TestDimension );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(80, 80)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(50, 60)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(120, 90)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(130, 50)) ) );

        assertTrue(tree.build(treePoints), "building the tree");
        Point newPoint = new Point( new ArrayList<>(Arrays.asList(90, 70)) );
        assertTrue(tree.add(newPoint), "add a new point to the tree");

        Point<Integer> minCorner = new Point( new ArrayList<>(Arrays.asList(45, 35)) );
        Point<Integer> maxCorner = new Point( new ArrayList<>(Arrays.asList(125, 85)) );

        Set<Point> outcome = tree.findInRange( minCorner, maxCorner );
        assertNotNull( outcome, "findInRange with build and find" );
//        assertEquals( 3, outcome.size(), "size where build and find");
    }

}
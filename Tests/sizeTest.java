import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class sizeTest {

    private static final int TestDimension = 2;
    
    @Test
    void multiplePointsBuildCheck2() {
        kdTree tree = new kdTree(TestDimension);;
        Set<Point> points = new HashSet<>();
        points.add(new Point(new ArrayList<>(Arrays.asList(1, 10))));
        points.add(new Point(new ArrayList<>(Arrays.asList(2, 9))));
        points.add(new Point(new ArrayList<>(Arrays.asList(3, 8))));
        points.add(new Point(new ArrayList<>(Arrays.asList(4, 7))));
        points.add(new Point(new ArrayList<>(Arrays.asList(5, 6))));
        points.add(new Point(new ArrayList<>(Arrays.asList(6, 5))));
        points.add(new Point(new ArrayList<>(Arrays.asList(7, 4))));
        points.add(new Point(new ArrayList<>(Arrays.asList(8, 3))));
        points.add(new Point(new ArrayList<>(Arrays.asList(9, 2))));
        points.add(new Point(new ArrayList<>(Arrays.asList(10, 1))));
        points.add(new Point(new ArrayList<>(Arrays.asList(11, 0))));

        assertTrue(tree.build(points));
        assertEquals(tree.sizeOfTheTree,11);
    }

    @Test
    void multiplePointsBuildCheck1() {
        kdTree tree = new kdTree(TestDimension);;
        Set<Point> points = new HashSet<>();
        points.add(new Point(new ArrayList<>(Arrays.asList(1, 10))));
        points.add(new Point(new ArrayList<>(Arrays.asList(2, 9))));
        points.add(new Point(new ArrayList<>(Arrays.asList(3, 8))));
        points.add(new Point(new ArrayList<>(Arrays.asList(4, 7))));
        points.add(new Point(new ArrayList<>(Arrays.asList(5, 6))));
        points.add(new Point(new ArrayList<>(Arrays.asList(6, 5))));
        points.add(new Point(new ArrayList<>(Arrays.asList(7, 4))));
        points.add(new Point(new ArrayList<>(Arrays.asList(8, 3))));
        points.add(new Point(new ArrayList<>(Arrays.asList(9, 2))));
        points.add(new Point(new ArrayList<>(Arrays.asList(10, 1))));
        points.add(new Point(new ArrayList<>(Arrays.asList(11, 0))));

        assertTrue(tree.build(points));

        Set<Point> points1 = new HashSet<>();
        points1.add(new Point(new ArrayList<>(Arrays.asList(1, 10))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(2, 9))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(3, 8))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(4, 7))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(5, 6))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(6, 5))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(7, 4))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(8, 3))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(9, 2))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(10, 1))));
        points1.add(new Point(new ArrayList<>(Arrays.asList(11, 0))));

        assertTrue(tree.build(points1));

        assertEquals(tree.sizeOfTheTree, 11);
    }

    @Test
    void addMultiplePoints(){
        kdTree tree = new kdTree(TestDimension);;
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("1", "15")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("2", "10")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("3", "4")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("4", "2")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("5", "6")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("6", "13")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("7", "1")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("8", "8")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("9", "7")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("10", "11")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("11", "14")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("12", "9")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("13", "5")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("14", "12")))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList("15", "3")))));

        assertEquals(15, tree.size());

    }
}

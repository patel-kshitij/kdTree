import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BuildTest {

    private static final int TestDimension = 2;

    @Test
    void nullSetOfPoints(){
        kdTree tree = new kdTree(TestDimension);
        assertFalse(tree.build(new HashSet<>()));
    }


    @Test
    void singlePointBuild() {
        kdTree tree = new kdTree(TestDimension);
        Set<Point> points = new HashSet<>();
        points.add(new Point(new ArrayList<>(Arrays.asList(1, 10))));

        assertTrue(tree.build(points));
    }

    @Test
    void threePointsBuild(){
        kdTree tree = new kdTree(TestDimension);
        Set<Point> points = new HashSet<>();
        points.add(new Point(new ArrayList<>(Arrays.asList(1, 3))));
        points.add(new Point(new ArrayList<>(Arrays.asList(2, 2))));
        points.add(new Point(new ArrayList<>(Arrays.asList(3, 1))));

        assertTrue(tree.build(points));

    }

    @Test
    void multiplePointsBuildCheck2() {
        kdTree tree = new kdTree(TestDimension);
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

    }


    @Test
    void multiplePointsBuildCheck1(){
        kdTree tree = new kdTree(TestDimension);
        Set<Point> points = new HashSet<>();
        points.add(new Point(new ArrayList<>(Arrays.asList(1, 15))));
        points.add(new Point(new ArrayList<>(Arrays.asList(2, 10))));
        points.add(new Point(new ArrayList<>(Arrays.asList(3, 4))));
        points.add(new Point(new ArrayList<>(Arrays.asList(4, 2))));
        points.add(new Point(new ArrayList<>(Arrays.asList(5, 6))));
        points.add(new Point(new ArrayList<>(Arrays.asList(6, 13))));
        points.add(new Point(new ArrayList<>(Arrays.asList(7, 1))));
        points.add(new Point(new ArrayList<>(Arrays.asList(8, 8))));
        points.add(new Point(new ArrayList<>(Arrays.asList(9, 7))));
        points.add(new Point(new ArrayList<>(Arrays.asList(10, 11))));
        points.add(new Point(new ArrayList<>(Arrays.asList(11, 14))));
        points.add(new Point(new ArrayList<>(Arrays.asList(12, 9))));
        points.add(new Point(new ArrayList<>(Arrays.asList(13, 5))));
        points.add(new Point(new ArrayList<>(Arrays.asList(14, 12))));
        points.add(new Point(new ArrayList<>(Arrays.asList(15, 3))));

        assertTrue(tree.build(points));
    }

}

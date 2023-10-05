import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindInRangeTest {

    private static final int TestDimension = 2;

    @Test
    void multiplePointsCheck(){

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

        Point minCorner = new Point<>(new ArrayList<>(Arrays.asList(1, 1)));
        Point maxCorner = new Point<>(new ArrayList<>(Arrays.asList(15, 14)));

        List response = new ArrayList<>(tree.findInRange(minCorner, maxCorner));

        System.out.println("Something");


    }

}

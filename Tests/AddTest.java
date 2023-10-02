import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AddTest {

    @Test
    void parameterCheck() {

        kdTree tree = new kdTree();

        assertFalse(tree.add(null), "Null point");
        assertFalse(tree.add(new Point<>(new ArrayList<Integer>())), "Empty point");

    }

    @Test
    void addOnePoint() {
        kdTree tree = new kdTree();
        ArrayList<Integer> coordinates = new ArrayList<>() ;
        coordinates.add(3);
        coordinates.add(4);
        Point point = new Point(coordinates);

        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(1, 2)))));

    }

    @Test
    void addMultiplePoints(){
        kdTree tree = new kdTree();
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(1, 15)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(2, 10)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(3, 4)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(4, 2)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(5, 6)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(6, 13)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(7, 1)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(8, 8)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(9, 7)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(10, 11)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(11, 14)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(12, 9)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(13, 5)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(14, 12)))));
        assertTrue(tree.add(new Point(new ArrayList<>(Arrays.asList(15, 3)))));

    }

}
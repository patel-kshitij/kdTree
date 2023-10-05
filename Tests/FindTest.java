import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class FindTest {

    private static final int TestDimension = 2;
    
    @Test
    void parameterCheck(){
        kdTree tree = new kdTree(TestDimension);;

        assertFalse(tree.find(null), "Null point");
        assertFalse(tree.find(new Point<>(new ArrayList<Integer>())), "0 dimension point");
    }

    @Test
    void onlyOneElementCheck(){
        kdTree tree = new kdTree(TestDimension);;

        tree.add(new Point(new ArrayList<>(Arrays.asList("1", "15"))));

        assertTrue(tree.find(new Point(new ArrayList<>(Arrays.asList("1", "15")))), "Point present");
        assertFalse(tree.find(new Point(new ArrayList<>(Arrays.asList("1", "25")))), "Point not present");
    }

    @Test
    void multiplePointsCheck(){

        kdTree tree = new kdTree(TestDimension);

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

        assertTrue(tree.find(new Point(new ArrayList<>(Arrays.asList("11", "14")))));
        assertFalse(tree.find(new Point(new ArrayList<>(Arrays.asList("11", "13")))));

        }
}

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class highDimTest {

    @Test
    void dimension4Build () {
        TreeDebug tree = new kdTree( 4 );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8, 7, 3)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6, 6, 11)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9, 0, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5, 2, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 13, 8, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(9, 7, 12, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10, 3, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(0, 14, 15, 12)) ) );

        assertTrue(tree.build(treePoints), "building the tree");

        assertEquals( "5 6 6 11 1\n4 13 8 2 2\n9 7 12 8 2\n2 10 3 6 3\n0 14 15 12 3\n13 5 2 9 3\n12 9 0 5 3\n8 8 7 3 4\n", tree.printTree( ), "build on 4 dimensions" );
    }

    @Test
    void dimension4Add () {
        TreeDebug tree = new kdTree( 4 );
        List<Point> treePoints = new ArrayList<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(10, 18, 3, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 10, 17, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 15, 12, -1)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(0, 15, 16, 0)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(9, 12, 2, 16)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(3, 16, 9, 20)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 13, 7, 30)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(0, 14, 8, 20)) ) );

        for (int i = 0; i < treePoints.size(); i++) {
            assertTrue(tree.add(treePoints.get(i)), "adding to the tree: " + i);
        }

        assertEquals( "10 18 3 5 1\n4 10 17 9 2\n8 15 12 -1 3\n9 12 2 16 4\n0 15 16 0 4\n3 16 9 20 5\n0 14 8 20 6\n5 13 7 30 6\n", tree.printTree( ), "build on 4 dimensions" );
    }

    @Test
    void dimension4Find () {
        TreeDebug tree = new kdTree( 4 );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8, 7, 3)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6, 6, 11)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9, 0, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5, 2, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 13, 8, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(9, 7, 12, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10, 3, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(0, 14, 15, 12)) ) );

        assertTrue(tree.build(treePoints), "building the tree");

        Point<Integer> searchPoint = new Point( new ArrayList<>(Arrays.asList(0, 7, 15, 12)) );
        assertFalse( tree.find( searchPoint ) ) ;

    }

    @Test
    void dimension4FindInRange () {
        TreeDebug tree = new kdTree( 4 );
        Set<Point> treePoints = new HashSet<>();
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(8, 8, 7, 3)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(5, 6, 6, 11)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(12, 9, 0, 5)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(13, 5, 2, 9)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(4, 13, 8, 2)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(9, 7, 12, 8)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(2, 10, 3, 6)) ) );
        treePoints.add( new Point( new ArrayList<>(Arrays.asList(0, 14, 15, 12)) ) );

        assertTrue(tree.build(treePoints), "building the tree");

        for (Point p : treePoints ) {
            assertTrue( tree.find( p ), p.getCoordinateString(0) ) ;
        }

        Point<Integer> minPoint = new Point( new ArrayList<>(Arrays.asList(6, 0, 0, 0)) );
        Point<Integer> maxPoint = new Point( new ArrayList<>(Arrays.asList(10, 30, 30, 30)) );
        Set<Point> outcome = tree.findInRange( minPoint, maxPoint );
        assertNotNull( outcome );
        assertEquals( 2, outcome.size() );
    }

    @Test
    void bigBuild () {
        TreeDebug tree = new kdTree( 2 );
        Set<Point> treePoints = new HashSet<>();
        {
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(884, 3621))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3525, 4443))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9486, 5417))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7239, 5943))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8033, 6826))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9747, 5971))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7743, 86))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6551, 9387))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2897, 9411))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9178, 5984))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4395, 3848))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2130, 7452))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3928, 2280))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6523, 6785))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(144, 3828))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7114, 6118))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7282, 117))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7441, 1206))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9128, 8145))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2689, 6517))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7041, 8265))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4217, 8021))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3103, 6823))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5985, 7290))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(445, 4678))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(263, 7343))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(948, 4328))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4632, 855))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(339, 9485))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3513, 9827))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6673, 1606))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6447, 2912))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9932, 8196))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1847, 2345))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4081, 3977))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3289, 7269))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4551, 9565))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2026, 1352))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5045, 2522))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(420, 8501))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1004, 483))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4072, 9435))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(878, 8437))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9767, 3849))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4320, 867))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9881, 6933))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7670, 3992))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6016, 4781))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(857, 1165))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(271, 3711))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8430, 2880))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9523, 4617))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(735, 4341))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5701, 1848))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(638, 3343))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4499, 6663))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1968, 4996))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6694, 5897))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6959, 3629))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3921, 4248))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9370, 1628))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9571, 8890))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9123, 4841))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9048, 7387))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9882, 2294))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(288, 1114))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6936, 4879))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8657, 4468))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5373, 6685))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5073, 8182))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(504, 8806))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9483, 1537))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3351, 7566))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7696, 214))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1788, 2664))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4418, 2629))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7671, 3106))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2044, 3141))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1030, 5623))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5551, 96))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(509, 9277))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(653, 1932))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4827, 7141))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6694, 1577))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2575, 7173))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2998, 8881))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2895, 4006))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1203, 2039))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2021, 299))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4980, 250))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9158, 633))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4056, 9612))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2087, 5269))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3479, 1771))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3362, 2849))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8665, 3797))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8753, 6144))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8709, 4870))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3737, 2000))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(860, 4278))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5566, 1883))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5796, 6573))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5036, 1859))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3011, 123))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(739, 6778))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5447, 9972))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(592, 4122))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3274, 2437))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7528, 5499))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(775, 2694))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2606, 7478))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1330, 5945))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5462, 8832))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7198, 9000))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6033, 4191))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(630, 4553))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5377, 4410))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6134, 8384))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(887, 8326))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4235, 9285))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7523, 1467))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(557, 6051))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8943, 3512))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(290, 1427))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5959, 2206))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6425, 1543))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9304, 0))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8896, 2288))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4368, 8573))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3422, 8932))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2529, 3647))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9956, 8082))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5062, 378))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3179, 4676))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9294, 667))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7818, 3664))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5635, 8349))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9441, 1608))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2736, 847))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6242, 1382))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6381, 6167))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2752, 3624))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8014, 9109))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2642, 7869))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2850, 1158))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2716, 3563))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5968, 2861))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2889, 2073))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4537, 6465))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5839, 3215))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5196, 983))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8649, 4578))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6688, 7660))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5723, 483))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3501, 9909))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8912, 3798))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1686, 408))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8967, 4054))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7739, 1832))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7852, 2743))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1088, 2235))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4862, 8012))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8989, 8597))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4789, 6844))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3963, 4906))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8867, 7560))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8437, 1847))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1840, 8604))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6124, 5341))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7012, 6580))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6558, 7172))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9453, 6938))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2832, 3588))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1823, 2110))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3835, 7314))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8977, 7521))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2472, 9000))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8345, 7340))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5454, 847))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5475, 1986))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2512, 644))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5848, 3944))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3316, 6036))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6728, 9344))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4660, 7657))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3529, 688))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9354, 5881))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(728, 4060))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2108, 9864))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(458, 3041))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2190, 6197))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5224, 4661))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(382, 7787))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7270, 6355))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7938, 8828))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5019, 9959))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8849, 3729))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4884, 3931))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3027, 6306))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6903, 4209))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8763, 1371))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9679, 7068))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(605, 2829))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6654, 1789))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4217, 2722))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1088, 8147))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2506, 448))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4881, 929))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1103, 7104))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8831, 394))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3869, 9807))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1999, 3288))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(270, 2607))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6833, 59))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5384, 7983))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(893, 5244))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2721, 816))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8588, 8837))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5148, 4214))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2517, 7240))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1985, 1850))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9215, 6381))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1196, 5949))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7961, 9665))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1683, 728))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3761, 2752))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8184, 9627))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3145, 8186))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3069, 9218))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7991, 6041))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5172, 6535))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3391, 4706))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(602, 9849))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1251, 2679))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6007, 6292))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9775, 1495))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7343, 5860))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2911, 9669))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3310, 2421))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6783, 3776))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8172, 7466))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8190, 6355))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9251, 4010))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1295, 1945))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2008, 8301))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6347, 9182))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9944, 6632))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7097, 4685))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7892, 7643))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7687, 7409))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1212, 3674))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1714, 3252))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2521, 2942))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(521, 5268))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4481, 2742))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1938, 9224))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8389, 2357))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4966, 6467))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7074, 3260))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1324, 9457))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8342, 6849))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1496, 3316))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5756, 57))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5837, 5123))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7824, 6272))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8868, 1359))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4370, 3223))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4769, 7315))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(108, 6148))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2059, 6918))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(655, 8107))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1126, 3363))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9142, 9909))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6595, 1166))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6499, 6232))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4397, 9300))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6730, 177))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(487, 7650))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(343, 2397))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4585, 6541))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1902, 4971))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4255, 2169))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3276, 1017))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(7099, 3772))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9596, 4873))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1248, 2696))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(1799, 5484))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4705, 7475))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6480, 1234))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(440, 5719))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(596, 7768))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(6269, 1203))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(5694, 4234))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(8168, 9962))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(411, 4745))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3083, 594))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(2462, 3240))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(9604, 5667))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(4043, 9718))));
            treePoints.add(new Point(new ArrayList<>(Arrays.asList(3118, 3408))));
        }
        assertTrue(tree.build(treePoints), "building the tree");
        assertEquals( 300, tree.size() );
    }

}
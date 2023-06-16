package exe.ex3.sol;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    /**
     * _m_3_3 =
     * 0,1,0
     * 1,0,1
     * 0,1,0
     *
     * _m0 =
     * 1,1,1,1,1
     * 1,0,1,0,1
     * 1,0,0,0,1
     * 1,0,1,0,1
     * 1,1,1,1,1
     * 1,0,1,0,1
     *
     * 1, 1, 1, 1, 1
     * 1,-1, 1,-1, 1
     * 1,-1,-1,-1, 1
     * 1,-1, 1,-1, 1
     * 1, 1, 1, 1, 1
     * 1,-1, 1,-1, 1
     *
     * m2[3][2] = 0, m2[1][2] = 10, |sp|=11 (isCiclic = false;}
     * =============
     * 7, 8, 9, 1, 7
     * 6,-1,10,-1, 6
     * 5,-1,-1,-1, 5
     * 4,-1, 0,-1, 4
     * 3, 2, 1, 2, 3
     * 4,-1, 2,-1, 4
     *
     * m[3][2] = 0, m2[1][2] = 5, |sp|=5 (isCiclic = true;}
     * 5, 4, 3, 4, 5
     * 6,-1, 4,-1, 6
     * 5,-1,-1,-1, 5
     * 4,-1, 0,-1, 4
     * 3, 2, 1, 2, 3
     * 4,-1, 2,-1, 4
     */
    private int[][] _map = {{1,1,1,1,1}, {1,0,1,0,1}, {1,0,0,0,1},  {1,0,1,0,1},  {1,1,1,1,1}, {1,0,1,0,1}};
    private int[][] _map_3_3 = {{0,1,0}, {1,0,1}, {0,1,0}};
    private Map2D _m0, _m1, _m2, _m3, _m3_3;
    @BeforeEach
    public void setuo() {
        _m0 = new Map(_map);
        _m1 = new Map(_map); _m1.setCyclic(true);
        _m2 = new Map(_map); _m2.setCyclic(false);
        _m3 = new Map(_map);
        _m3_3 = new Map(_map_3_3);
    }
    @Test
    @Timeout(value = 1, unit = SECONDS)
    void init() {
        int[][] bigarr = new int [500][500];
        _m1.init(bigarr);
        assertEquals(bigarr.length, _m1.getWidth());
        assertEquals(bigarr[0].length, _m1.getHeight());
        Pixel2D p1 = new Index2D(3,2);
        _m1.fill(p1,1);
    }

    @Test
    void testInit() {
        _m1.init(_map);
        assertEquals(_m0, _m1);
    }
    @Test
    void testEquals() {
        assertEquals(_m0,_m1);
        assertEquals(_m0,_m3);
        assertNotEquals(_m1,_m2);
        _m3.setPixel(2,2,17);
        assertNotEquals(_m0,_m3);
    }
    @Test
    void getMap() {
        int[][] m0 = _m0.getMap();
        _m1.init(m0);
        assertEquals(_m0,_m1);
    }
    @Test
    void getWidth() {
        assertEquals(_m0.getWidth(),_map.length);
        assertNotEquals(_m0.getHeight(),0);
    }

    @Test
    void getHeight() {
        assertEquals(_m0.getHeight(),_map[0].length);
        assertNotEquals(_m0.getWidth(),0);
    }


    @Test
    void testSetPixel() {
        _m0.setPixel(2,3, 17);
        assertNotEquals(_m0.getPixel(2,3),_map[2][3]);
    }

    @Test
    void testFill0() {
        Pixel2D p1 = new Index2D(0,0);
        int f0 = _m0.fill(p1,2);
        assertEquals(f0,21);
    }
    @Test
    void testFill1() {
        Pixel2D p1 = new Index2D(0,1);
        _m0.setPixel(p1,0);
        int f0 = _m0.fill(p1,2);
        assertEquals(f0,9);
        _m0.setCyclic(false);
        int f2 = _m0.fill(p1,3);
        assertEquals(f2,8);
    }
    @Test
    void testFill2() {
        Pixel2D p0 = new Index2D(0,1);
        Pixel2D p1 = new Index2D(2,1);
        Pixel2D p2 = new Index2D(1,0);
        Pixel2D p3 = new Index2D(1,2);
        int f0 = _m3_3.fill(p1,2);
        assertEquals(f0,2);
        f0 = _m3_3.fill(p0,3);
        assertEquals(f0,2);
        f0 = _m3_3.fill(p2,4);
        assertEquals(f0,2);
        f0 = _m3_3.fill(p3,5);
        assertEquals(f0,2);

        _m3_3.setCyclic(false);
        _m3_3.init(_map_3_3);
        int f2 = _m3_3.fill(p0,3);
        assertEquals(f2,1);
        _m3_3.init(_map_3_3);
        f2 = _m3_3.fill(p1,3);
        assertEquals(f2,1);
        _m3_3.init(_map_3_3);
        f2 = _m3_3.fill(p2,3);
        assertEquals(f2,1);
        _m3_3.init(_map_3_3);
        f2 = _m3_3.fill(p3,3);
        assertEquals(f2,1);
    }
    @Test
    void testShortestPath2() {
        Pixel2D p0 = new Index2D(0,1);
        Pixel2D p1 = new Index2D(2,1);
        Pixel2D p2 = new Index2D(1,0);
        Pixel2D p3 = new Index2D(1,2);
        Pixel2D[] p = _m3_3.shortestPath(p0, p1,0);
        assertEquals(p.length,2);
        p = _m3_3.shortestPath(p1, p0,0);
        assertEquals(p.length,2);
        p = _m3_3.shortestPath(p2, p3,0);
        assertEquals(p.length,2);
        p = _m3_3.shortestPath(p3, p2,0);
        assertEquals(p.length,2);
        p = _m3_3.shortestPath(p2, p2,0);
        assertEquals(p.length,1);

        _m3_3.setCyclic(false);
        _m3_3.init(_map_3_3);
        p = _m3_3.shortestPath(p0, p1,0);
        assertNull(p);
        p = _m3_3.shortestPath(p1, p0,0);
        assertNull(p);
        p = _m3_3.shortestPath(p2, p3,0);
        assertNull(p);
        p = _m3_3.shortestPath(p3, p2,0);
        assertNull(p);
        p = _m3_3.shortestPath(p2, p2,0);
        assertEquals(p.length,1);
    }
    @Test
    void testAllDistance() {
        Pixel2D p1 = new Index2D(3,2);
        Pixel2D p2 = new Index2D(1,0);
        Map2D m00 = _m0.allDistance(p1, 0);
        assertEquals(6, m00.getPixel(p2));
    }

    @Test
    void testShortestPath() {
        Pixel2D p1 = new Index2D(3,2);
        Pixel2D p2 = new Index2D(1,2);
        Pixel2D[] path = _m0.shortestPath(p1, p2, 0);
        assertEquals(5, path.length);
        path = _m2.shortestPath(p1, p2, 0);
        assertEquals(11, path.length);
    }

    @Test
    void testIsInside() {
        assertFalse(_m0.isInside(new Index2D(-1,0)));
        assertFalse(_m0.isInside(new Index2D(0,-1)));
        assertFalse(_m0.isInside(new Index2D(-1,-1)));
        assertTrue(_m0.isInside(new Index2D(0,0)));
        assertFalse(_m0.isInside(new Index2D(_map.length,0)));
        assertFalse(_m0.isInside(new Index2D(0,_map[0].length)));
    }
}
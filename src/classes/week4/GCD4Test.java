package classes.week4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GCD4Test {
    @Test
    public void testGcd2() {
        long x = 31;
        long y =  x*7;
        long g = GCD4.gcd2(x,y);
        assertEquals(x,g);
    }
    @Test
    public void testGcd12() {
        long x = 31;
        long y =  x*7;
        long g1 = GCD4.gcd1(x,y);
        long g2 = GCD4.gcd2(x,y);
        assertEquals(g1,g2);
        assertEquals(g2,x);
        g1 = GCD4.gcd1(x,x);
        g2 = GCD4.gcd2(y,y);
        assertEquals(g1,x);
        assertEquals(g2,y);
    }
    @Test
    public void testGcd1234() {
        long x = 31;
        long y =  x*7;
        long g1 = GCD4.gcd1(x,y);
        long g2 = GCD4.gcd2(x,y);
        long g3 = GCD4.gcd3(x,y);
        long g4 = GCD4.gcd4(x,y);
        assertEquals(x,g1);
        assertEquals(g1,g2);
        assertEquals(g2,g3);
        assertEquals(g3,g4);
    }
    @Test
    public void testGcd234() {
        long g = 2*3*5*7*11*13*17*19*23;
        long x = g*31;
        long y = g*29;
      //  long a1 = GCD4.gcd1(x,y);
        long a2 = GCD4.gcd2(x,y);
        long a3 = GCD4.gcd3(x,y);
        long a4 = GCD4.gcd4(x,y);
      //  assertEquals(a1,g);
        assertEquals(a2,g);
        assertEquals(a3,g);
        assertEquals(a4,g);
    }
    @Test
    public void testGcd_RT() {
        long g = 2*3*5*7*11*13*17;//*19*23;
        long x = g*31;
        long y = g*29;
        long start = System.currentTimeMillis();
        long a1 = GCD4.gcd1(x,y); // this will cause a "time-out" bug
        long e1 = System.currentTimeMillis();
        long a3 = GCD4.gcd3(x,y);
        long e2 = System.currentTimeMillis();
        long a4 = GCD4.gcd4(x,y);
        long e3 = System.currentTimeMillis();
        double dt1 = e1-start;
        double dt2 = e2-e1;
        double dt3 = e3-e2;
        assertTrue(dt1<500);
        assertTrue(dt2<10);
        assertTrue(dt3<10);
        System.out.println("dt1: "+dt1+",  dt2: "+dt2+",  dt3: "+dt3);
    }
    @Test
    @Timeout(value = 10, unit = MILLISECONDS)
    public void testGcd_RT2() {
        long g = 2*3*5*7*11*13*17*19*23;
        long x = g*31;
        long y = g*29;
     //   long a1 = GCD4.gcd1(x,y); // this will cause a "time-out" bug
        long a3 = GCD4.gcd3(x,y);
        long a4 = GCD4.gcd4(x,y);
        assertEquals(a3,g);
        assertEquals(a4,g);
    }
}
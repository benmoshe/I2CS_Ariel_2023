package exe.ex1.sol;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This is a simple JUnit test class for checking that the Ex1 implementation is correct.
 */
class Ex1Test {

    @Test
    void main() {
        String[] args = {"24", "16", "9"};
        Ex1.main(args);
    }

    @Test
    void testMcd2() {
        long n0=24, n1=16, n2=9;
        long m1 = Ex1.mcd2(n0,n1,n2);
        long m2 = Ex1.mcd2(n2,n1,n0);
        assertEquals(m1,m2);
    }

    @Test
    void testMcd() {
        long n0=24, n1=16, n2=9, ans=144;
        long m1 = Ex1.mcd(n0,n1,n2);
        long m2 = Ex1.mcd(n2,n1,n0);
        assertEquals(m1,m2);
        assertEquals(m1,ans);
    }
    @Test
    @Timeout(value = 1000, unit = TimeUnit.MILLISECONDS)
    void testMcd3() {
        long a0=31, a1=331, a2=3331;
        long n0=a0*a2*a2*a1, n1=a2*a2*a1, n2=a0*a0*a2;
        long m1 = Ex1.mcd(n0,n1,n2);
        long m2 = Ex1.mcd2(n2,n1,n0);
        assertEquals(m1,m2);
    }
}
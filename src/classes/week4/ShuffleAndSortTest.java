package classes.week4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
/**
 * This JUnit tester is rather complicated, the main concept is to 
 * test if the shuffle function is computing a random permutation. 
 * The test is performed by computing a large set of "shuffles" and then
 * counting the location of each element in the array. 
 * The counting is being done using a 2D array. The counting of each location
 * is tested to be +-10% of the evarage.
 * 
 * @author boaz.ben-moshe
 *0 | 1006.0,1252.0,1140.0,1143.0,1061.0,980.0,968.0,879.0,784.0,787.0,
 * 1 | 1019.0,956.0,1258.0,1165.0,1105.0,1005.0,934.0,897.0,866.0,795.0,
 * 2 | 971.0,947.0,944.0,1247.0,1120.0,1080.0,1067.0,945.0,847.0,832.0,
 * 3 | 973.0,926.0,945.0,853.0,1183.0,1111.0,1089.0,1036.0,894.0,990.0,
 * 4 | 1001.0,1005.0,877.0,875.0,807.0,1215.0,1124.0,1072.0,1040.0,984.0,
 * 5 | 1007.0,951.0,902.0,911.0,872.0,863.0,1198.0,1141.0,1090.0,1065.0,
 * 6 | 990.0,1057.0,943.0,926.0,859.0,892.0,842.0,1167.0,1176.0,1148.0,
 * 7 | 1021.0,941.0,1024.0,936.0,988.0,918.0,858.0,908.0,1256.0,1150.0,
 * 8 | 1014.0,969.0,980.0,940.0,1015.0,951.0,977.0,907.0,993.0,1254.0,
 * 9 | 998.0,996.0,987.0,1004.0,990.0,985.0,943.0,1048.0,1054.0,995.0,
 *
 *
 */
class ShuffleAndSortTest {
	public static double EPS = 0.1;  // 10%
	@Test
	void testShuffle() {
		int size = 10, norm=1000;
		int iter = size*norm;
		double[][] test2D = new double[size][size];
		for(int i=0;i<iter;i++) {
			String[] s = init(size);
			ShuffleAndSort.shuffle(s);
			update(s, test2D);
		}
		show(test2D);
		boolean b = testUnifiedProb(test2D, norm);
		assertTrue(b);
	}

	//@Test
	//void testSort() {
	//	fail("Not yet implemented");
	//}
	////////////////////////////////////////////
	private static String[] init(int size) {
		String[] s = new String[size];
		for(int i=0;i<size;i=i+1) {s[i] = ""+i;}
		return s;
	}
	private static void update(String[] s, double[][] t) {
		for(int i=0;i<s.length;i=i+1) {
			int ind = Integer.parseInt(s[i]);
			t[i][ind]++;
		}
	}

	private boolean testUnifiedProb(double[][] a, int norm) {
		boolean ok = true;
		for (int j = 0; j < a.length && ok; j++) {
    		for (int i = 0; i < a[0].length; i++) {
    			double d1 = a[j][i];
    			double d = (d1 - norm)/norm;
    			double eps = Math.abs(d);
    			if(eps>EPS) {ok = false;};
    		}
    	}
		return ok;
	}
	private static void show(double[][] a) {
    	System.out.println();
    	for (int j = 0; j < a.length; j++) {
    		System.out.print(j+" | ");
    		for (int i = 0; i < a.length; i++) {
    			System.out.print(a[j][i]+",");
    		}
    		System.out.println();
    	}
    }
}

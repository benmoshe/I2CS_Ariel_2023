package exe.ex3;

/**
 * This interface represents a 2D map as a raster matrix or maze.
 * The data is assumed to be a rectangular 2D matrix (not a ragged array).
 * This interface includes the following functionalities:
 * 1. init, copy
 * 2. set the map to be cyclic or not cyclic.
 * 3. shortest path (obstacle avoiding)
 * 4. shortest distances from a source coordinate to each entry in this map (obstacle avoiding).
 * 5. fill a connected component with a new color
 *
 * If this map is cyclic:
 * 1. the pixel to the left of (0,i) is (getWidth()-1,i).
 * 2. the pixel to the right of (getWidth()-1,i) is (0,i).
 * 3. the pixel above (j,getHeight()-1) is (j,0).
 * 4. the pixel below (j,0) is (j,getHeight()-1).
 * Where 0<=i<getWidth(), 0<=j<getWidth().
 *
 * @author boaz.ben-moshe
 * Do NOT change this interface!!
 */
public interface Map2D {
	/**
	 * Construct a 2D w*h matrix of double.
	 * @param w the width of the underlying 2D array.
	 * @param h the height of the underlying 2D array.
	 * @param v the init value of all the entries in the 2D array.
	 */
	public void init(int w, int h, int v);
	/**
	 * Constructs a 2D raster map from a given 2D int array (deep copy).
	 * @throws RuntimeException if arr == null or if the array is empty or a ragged 2D array.
	 * @param arr a 2D int array.
	 */
	public void init(int[][] arr);

	/**
	 * Computes a deep copy of the underline 2D matrix.
	 * @return a deep copy of the underline matrix.
	 */
	public int[][] getMap();

	/**
	 * @return the width of this 2D map (first coordinate).
	 */
	public int getWidth();

	/**
	 * @return the height of this 2D map (second coordinate).
	 */
	public int getHeight();

	/**
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return the [x][y] (int) value of the map[x][y].
	 */
	public int getPixel(int x, int y);
	/**
	 * @param p the x,y coordinate
	 * @return the [p.x][p.y] (int) value of the map.
	 */
	public int getPixel(Pixel2D p);

	/**
	 * Set the [x][y] coordinate of the map to v.
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param v the value that the entry at the coordinate [x][y] is set to.
	 */
	public void setPixel(int x, int y, int v);
	/**
	 * Set the [x][y] coordinate of the map to v.
	 * @param p the coordinate in the map.
	 * @param v the value that the entry at the coordinate [p.x][p.y] is set to.
	 */
	public void setPixel(Pixel2D p, int v);

	/**
	 * @param p the 2D coordinate.
	 * @return true iff p is with in this map.
	 */
	boolean isInside(Pixel2D p);

	/**
	 * @return true iff this map should be addressed as a cyclic one.
	 */
	boolean isCyclic();

	/**
	 * Set the cyclic flag of this map
	 * @param cy the value of the cyclic flag.
	 */
	void setCyclic(boolean cy);

	///////////////// Algorithms //////////////////
	/**
	 * Fill the connected component of p in the new color (new_v).
	 * Note: the connected component of p are all the pixels in the map with the same "color" of map[p] which are connected to p.
	 * Note: two pixels (p1,p2) are connected if there is a path between p1 and p2 with the same color (of p1 and p2).
	 * @param p
	 * @param new_v
	 * @return
	 */
	public int fill(Pixel2D p, int new_v);

	/**
	 * Compute the shortest valid path between p1 and p2.
	 * A valid path between p1 and p2 is defined as a path between p1 and p2 does NOT contain the absColor.
	 * A path is an ordered set of pixels where each consecutive pixels in the path are neighbors in this map.
	 * Two pixels are neighbors in the map, iff they are a single pixel apart (up,down, left, right).
	 * In case there is no valid path between p1 and p2 should return null;
	 * If this map is cyclic:
	 * 1. the pixel to the left of (0,i) is (getWidth()-1,i).
	 * 2. the pixel to the right of (getWidth()-1,i) is (0,i).
	 * 3. the pixel above (j,getHeight()-1) is (j,0).
	 * 4. the pixel below (j,0) is (j,getHeight()-1).
	 * Where 0<=i<getWidth(), 0<=j<getWidth().
	 *
	 * @param p1 first coordinate (start point).
	 * @param p2 second coordinate (end point).
	 * @param obsColor the color which is addressed as an obstacle.
	 * @return the shortest path as an array of consecutive pixels.
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor);

	/**
	 * Compute a new map (with the same dimension as this map) with the
	 * shortest path distance (obstacle avoiding) from the start point.
	 * None accessible entries should be marked -1.
	 * @param start the source (starting) point
	 * @param obsColor the color representing obstacles
	 * @return a new map with all the shortest path distances from the starting point to each entry in this map.
	 */
	public Map2D allDistance(Pixel2D start, int obsColor);
}

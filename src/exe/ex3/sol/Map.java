package exe.ex3.sol;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class represents a 2D map as a "screen" or a raster matrix or maze over integers.
 * @author boaz.benmoshe
 *
 */
public class Map implements Map2D, Serializable{
	private int[][] _map;
	private boolean _cyclicFlag = true;
	
	/**
	 * Constructs a w*h 2D raster map with an init value v.
	 * @param w
	 * @param h
	 * @param v
	 */
	public Map(int w, int h, int v) {init(w,h, v);}
	/**
	 * Constructs a square map (size*size).
	 * @param size
	 */
	public Map(int size) {this(size,size, 0);}
	
	/**
	 * Constructs a map from a given 2D array.
	 * @param data
	 */
	public Map(int[][] data) {
		init(data);
	}
	@Override
	public void init(int w, int h, int v) {
		_map = new int[w][h];
		for(int x = 0;x<this.getWidth()&& x<getWidth();x++) {
			for(int y=0;y<this.getHeight()&& y<getHeight();y++) {
				this.setPixel(x, y, v);
			}
		}
	}
	@Override
	public void init(int[][] arr) {
		if(!isValidRectArray(arr)) {throw new RuntimeException("ERR: not a valid rectangular 2D array");}
		this._map = new int[arr.length][arr[0].length];
		for(int x = 0;x<arr.length;x++) {
			for(int y=0;y<arr[0].length;y++) {
				this.setPixel(x, y, arr[x][y]);
			}
		}
	}
	@Override
	public int[][] getMap() {
		int[][] ans = new int[this.getWidth()][this.getHeight()];
		for(int x = 0;x<this.getWidth() ;x++) {
			for(int y=0;y<this.getHeight() ;y++) {
				int c = this.getPixel(x,y);
				ans[x][y] = c;
			}
		}
		return ans;
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Pixel2D p) {
		return this.getPixel(p.getX(),p.getY());
	}
	@Override
	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	@Override
	public void setPixel(Pixel2D p, int v) {
		setPixel(p.getX(), p.getY(), v);
	}


	@Override
	/** 
	 * Fills this map with the new color (new_v) starting from p.
	 * https://en.wikipedia.org/wiki/Flood_fill
	 */
	public int fill(Pixel2D xy, int new_v) {
		int x = xy.getX();
		int y = xy.getY();
		int ans=0;
		int old_v = this.getPixel(x, y);
		ArrayList<Pixel2D> front = new ArrayList<Pixel2D>();
		front.add(new Index2D(x,y));
		int w = this.getWidth();
		int h = this.getHeight();
		while(!front.isEmpty()) {
			Pixel2D p = front.remove(0);

			if(isInside(p)) {
				int v = this.getPixel(p);
				if(v==old_v  && v!=new_v) {
					this.setPixel(p, new_v);
					ans++;
					int y1 = (p.getY()+1);
					if(this.isCyclic()) {y1=y1%h;}
					int y0 = (p.getY()-1);
					if(y0==-1 && this.isCyclic()) {y0=h-1;}
					int x1 = (p.getX()+1);
					if(this.isCyclic()) {x1=x1%w;}
					int x0 = (p.getX()-1);
					if(x0==-1 && this.isCyclic()) {x0=w-1;}
					front.add(new Index2D(x1, p.getY()));
					front.add(new Index2D(x0, p.getY()));
					front.add(new Index2D(p.getX(), y0));
					front.add(new Index2D(p.getX(), y1));
				}
			}
		}
		return ans;
	}


	/**
	 * Computes the distance of the shortest path (minimal number of consecutive neighbors) from p1 to p2.
	 * Notes: the distance is using computing the shortest path and returns its length-1, as the distance fro  a point
	 * to itself is 0, while the path contains a single point.
	 
	 */
	public int shortestPathDist(Pixel2D p1, Pixel2D p2, int obsColor) {
		int ans = -1;
		Pixel2D[] path = shortestPath(p1,p2, obsColor);
		if(path!=null) {ans = path.length-1;}
		return ans;
	}
	@Override
	/**
	 * BFS like shortest the computation based on iterative raster implementation of BFS, see:
	 * https://en.wikipedia.org/wiki/Breadth-first_search
	 */
	public Pixel2D[] shortestPath(Pixel2D p1, Pixel2D p2, int obsColor) {
		Pixel2D[] ans = null;  // the result.
		p1 = new Index2D(p1.getX(), p1.getY());
		p2 = new Index2D(p2.getX(), p2.getY()); // copying the data --> to be on the safe side (aka avoid side effects).
		//int c1 = this.getPixel(p1);
		int w = this.getWidth();
		int h = this.getHeight();
			Map map = new Map(this.getWidth(), this.getHeight(),-1); // temporal map for BFS dist computations.
			map.setPixel(p1, 0);
			if(p1.equals(p2)) {  // in case the start and the end points are the same.
				ans = new Pixel2D[1]; ans[0] = p1;
			}
			else {
				boolean found = false;
				ArrayList<Pixel2D> front = new ArrayList<Pixel2D>(); // the BFS front.
				front.add(p1);
				Pixel2D[] ni = new Pixel2D[4];
				// the main loop - while not found & has more (new) cells to explore.
				while(!found && !front.isEmpty()) {  
					// This is the BFS implementation - removes from the beginning of the Queue/
					Pixel2D c = front.remove(0);
					int dist = map.getPixel(c);
					if(c.equals(p2)) {found = true;}
					else {
						int y1 = (c.getY()+1);
						if(this.isCyclic()) {y1=y1%h;}
						int y0 = (c.getY()-1);
						if(y0==-1 && this.isCyclic()) {y0=h-1;}
						int x1 = (c.getX()+1);
						if(this.isCyclic()) {x1=x1%w;}
						int x0 = (c.getX()-1);
						if(x0==-1 && this.isCyclic()) {x0=w-1;}
						ni[0] = new Index2D(c.getX(), y1);
						ni[1] = new Index2D(c.getX(), y0);
						ni[2] = new Index2D(x0, c.getY());
						ni[3] = new Index2D(x1, c.getY());
						for(int i=0;i<4;i++) {
							if(map.isInside(ni[i]) && map.getPixel(ni[i]) ==-1 && getPixel(ni[i])!=obsColor) {
								// add to the end of the Queue
								front.add(ni[i]);
								map.setPixel(ni[i], dist+1);
							}		
						}
					}
				}
				// computes the actual path from the end-point to the starting point.
				if(found) {
					int dist = map.getPixel(p2);
					ans = new Pixel2D[dist+1];
					Pixel2D p = p2;
					ans[dist] = p2;
					while(!p.equals(p1)) {
						p = findLast(map, p);
						dist--;
						ans[dist] = p;
					}
					ans[0] = p1;
				}
			}
		return ans;
	}

	@Override
	public boolean isInside(Pixel2D p) {
		return isInside(p.getX(),p.getY());
	}

	@Override
	public boolean isCyclic() {
		return _cyclicFlag;
	}

	@Override
	public void setCyclic(boolean cy) {
		_cyclicFlag = cy;
	}

	private boolean isInside(int x, int y) {
		return x>=0 && y>=0 && x<this.getWidth() && y<this.getHeight();
	}
	@Override
	public Map2D allDistance(Pixel2D start, int obsColor) {
		Pixel2D[] ans = null;  // the result.
		int h = this.getHeight();
		int w = this.getWidth();
		start = new Index2D(start);
		int c1 = this.getPixel(start);
		Map map = new Map(this.getWidth(), this.getHeight(), -1); // temporal map for BFS dist computations.
		int v = this.getPixel(start);
		if(v!=obsColor) {
			map.setPixel(start, 0);
			boolean found = false;
			ArrayList<Pixel2D> front = new ArrayList<Pixel2D>(); // the BFS front.
			front.add(start);
			Pixel2D[] ni = new Pixel2D[4];
				// the main loop - while not found & has more (new) cells to explore.
			while(!front.isEmpty()) {
					// This is the BFS implementation - removes from the beginning of the Queue/
				Pixel2D c = front.remove(0);
				int dist = map.getPixel(c);
				int y1 = (c.getY()+1);
				if(this.isCyclic()) {y1=y1%h;}
				int y0 = (c.getY()-1);
				if(y0==-1 && this.isCyclic()) {y0=h-1;}
				int x1 = (c.getX()+1);
				if(this.isCyclic()) {x1=x1%w;}
				int x0 = (c.getX()-1);
				if(x0==-1 && this.isCyclic()) {x0=w-1;}
				ni[0] = new Index2D(c.getX(), y1);
				ni[1] = new Index2D(c.getX(), y0);
				ni[2] = new Index2D(x0, c.getY());
				ni[3] = new Index2D(x1, c.getY());
				for(int i=0;i<4;i++) {
					if(map.isInside(ni[i]) && map.getPixel(ni[i]) ==-1 && getPixel(ni[i])!=obsColor) {
								// add to the end of the Queue
						front.add(ni[i]);
						map.setPixel(ni[i], dist+1);
					}
				}
				// computes the actual path from the end-point to the starting point.
			}
		}
		return map;
	}
	@Override
	public boolean equals(Object ob) {
		boolean ans = false;
		if(ob instanceof Map2D) {
			Map2D ot = (Map2D)ob;
			if(ot.getHeight() == this.getHeight() && ot.getWidth() == this.getWidth() && ot.isCyclic() == this.isCyclic()) {
				ans = true;
				for(int i=0;i<this.getWidth() && ans;i++) {
					for(int j=0;j<this.getHeight();j++) {
						Pixel2D p = new Index2D(i,j);
						if(this.getPixel(p) != ot.getPixel(i,j)) {ans = false;}
					}
				}
			}
		}
		return ans;
	}
	////////////////////// Private ///////////////////////
	/**
	 * This function finds the "previous" neighbor in terms of distance.
	 * PRIVATE - should only be used for shortest-path (end to start) back-tracking.
	 * @param map
	 * @param c
	 * @return
	 */
	private Pixel2D findLast(Map map, Pixel2D c) {
		Pixel2D ans = null;
		int d = map.getPixel(c);
		int w = this.getWidth();
		int h = this.getHeight();
		if(d<=0) {throw new RuntimeException("ERR: internal error =- shortest path #2340938");}
		Pixel2D[] ni = new Pixel2D[4];
		int y1 = (c.getY()+1);
		if(this.isCyclic()) {y1=y1%h;}
		int y0 = (c.getY()-1);
		if(y0==-1 && this.isCyclic()) {y0=h-1;}
		int x1 = (c.getX()+1);
		if(this.isCyclic()) {x1=x1%w;}
		int x0 = (c.getX()-1);
		if(x0==-1 && this.isCyclic()) {x0=w-1;}
		ni[0] = new Index2D(c.getX(), y1);
		ni[1] = new Index2D(c.getX(), y0);
		ni[2] = new Index2D(x0, c.getY());
		ni[3] = new Index2D(x1, c.getY());
		for(int i=0;i<4;i++) {
			if(map.isInside(ni[i]) && map.getPixel(ni[i]) ==d-1) {
				ans = ni[i];
			}		
		}
		return ans;
	}
	private boolean isValidRectArray(int[][] arr) {
		boolean ans = true;
		if(arr==null || arr.length==0) {ans = false;}
		else {
			for(int i=0;i<arr.length && ans;i++) {
				if(arr[i]==null || arr[i].length==0|| arr[i].length!=arr[0].length) {ans = false;}
			}
		}
		return ans;
	}
}

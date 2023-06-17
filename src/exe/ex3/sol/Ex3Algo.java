package exe.ex3.sol;

import exe.ex3.game.Game;
import exe.ex3.game.GhostCL;
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

import java.awt.*;

/**
 * This is the major algorithmic class for Ex3 - the PacMan game:
 *
 * This code is a relatively simple "state machine" algorithm with 3 main states:
 * 1. PINK - goto the closest pink.
 * 2. GREEN - go to the closest ghost
 * 3. BLACK - run from the closest ghost/
 * Your task is to implement (here) your PacMan algorithm.
 */
public class Ex3Algo implements PacManAlgo{
	private static final int BLUE = Game.getIntColor(Color.BLUE, 0);
	private static final int PINK = Game.getIntColor(Color.PINK, 0);
	private static final int BLACK = Game.getIntColor(Color.BLACK, 0);
	private static final int GREEN = Game.getIntColor(Color.GREEN, 0);
	public static final double MIN_TIME = 0.5;
	public static final int GOTO_DIST = 10, MAX_RADIUS = 20;
	public static final double RUN_FROM_DIST = 5;
	public Ex3Algo() {;}
	@Override
	/**
	 *  Add a short description for the algorithm as a String.
	 */
	public String getInfo() {
		return "I2CS, Ex3 PacMan algo";
	}
	@Override
	/**
	 * This ia the main Algorithm for the Pacman, it uses a simple state-machine of 3 states (PINK,GREEN, BLACK).
	 */
	public int move(PacmanGame game) {
		int code = 0;
		int[][] board = game.getGame(0);
		Map map = new Map(board);
		map.setCyclic(game.isCyclic());
		Pixel2D pos = new Index2D( game.getPos(code));
		GhostCL[] ghosts = game.getGhosts(code);
		int dir = -1;//randomDir();
		Map2D m = distsFromGhosts(ghosts, map, MAX_RADIUS); // the combined minimal distances matrix - over all active ghosts.
		int mode = mode(map, pos, ghosts, m);  // maim "mode" selection.
		if(mode == PINK) {dir = closestPink(map, pos);}
		if(mode == GREEN) {
			dir = gotoClosestGhost(m, pos);}
		if(mode == BLACK) {
			dir = runFromClosestGhost(m, pos);}
		return dir;
	}
	private static int mode(Map2D map, Pixel2D pos, GhostCL[] gh, Map2D dists) {
		int c = 0;
		int ans = PINK;
		double time = 0;
		int dist = dists.getPixel(pos);
		for(int i=0;i<gh.length;i++) {
 			if(gh[i].getStatus()==GhostCL.PLAY) {c++; time = gh[i].remainTimeAsEatable(0);}
		}
		if(c>0) {
			if(time>MIN_TIME && dist<GOTO_DIST) {
				ans = GREEN;
			} // run after the closest ghost
			else {
				if(dist<RUN_FROM_DIST) {
					ans = BLACK;
				}// run from the closest ghost
			}
		}
		return ans;
	}
	private static Map2D distsFromGhosts(GhostCL[] gh, Map orig, int maxRad) {
		Map ans = null;
		if(gh!=null) {
			ans = new Map(orig.getWidth(),orig.getHeight(),maxRad); // loys
			for (int i = 0; i <gh.length;i++) {
				GhostCL g = gh[i];
				if(g!=null && isChasable(g)) {
					Index2D pos = new Index2D(g.getPos(0));
					Map2D m = orig.allDistance(pos, BLUE);
					ans = min(ans, m, maxRad);
				}
			}
		}
		return ans;
	}
	private static boolean isChasable(GhostCL g) {
		Pixel2D p = new Index2D(g.getPos(0));
		int dx = Math.abs(11- p.getX());  // this is an ugly hard codded "workaround: to avoid the pacman from running into the ghosts "yard".
		int dy = Math.abs(11-p.getY());
		return dx+dy>2;
	}
	private static void printBoard(int[][] b) {
		for(int y =0;y<b[0].length;y++){
			for(int x =0;x<b.length;x++){
				int v = b[x][y];
				System.out.print(v+"\t");
			}
			System.out.println();
		}
	}
	private static void printGhosts(GhostCL[] gs) {
		for(int i=0;i<gs.length;i++){
			GhostCL g = gs[i];
			System.out.println(i+") status: "+g.getStatus()+",  type: "+g.getType()+",  pos: "+g.getPos(0)+",  time: "+g.remainTimeAsEatable(0));
		}
	}
	private static int randomDir() {
		int[] dirs = {Game.UP, Game.LEFT, Game.DOWN, Game.RIGHT};
		int ind = (int)(Math.random()*dirs.length);
		return dirs[ind];
	}
	private static int up(Map2D map, Pixel2D pos) {
		int ans = -1;
		int w = map.getWidth(), h= map.getHeight();
		int x = pos.getX(), y = pos.getY();
		if(map.isCyclic()) {
			int y1 = (y+1)%h;
			ans = map.getPixel(x,y1);
		}
		else {
			int y1 = (y+1);
			if(y1<h) {ans = map.getPixel(x,y1);}
		}
		return ans;
	}
	private static int down(Map2D map, Pixel2D pos) {
		int ans = -1;
		int w = map.getWidth(), h= map.getHeight();
		int x = pos.getX(), y = pos.getY();
		if(map.isCyclic()) {
			int y1 = (y-1);
			if(y==0) {y1 = h-1;}
			ans = map.getPixel(x,y1);
		}
		else {
			int y1 = (y-1);
			if(y1>=0) {ans = map.getPixel(x,y1);}
		}
		return ans;
	}
	private static int right(Map2D map, Pixel2D pos) {
		int ans = -1;
		int w = map.getWidth(), h= map.getHeight();
		int x = pos.getX(), y = pos.getY();
		if(map.isCyclic()) {
			int x1 = (x+1);
			if(x1==w) {x1 = 0;}
			ans = map.getPixel(x1,y);
		}
		else {
			int x1 = (x+1);
			if(x1<w) {ans = map.getPixel(x1,y);}
		}
		return ans;
	}
	private static int left(Map2D map, Pixel2D pos) {
		int ans = -1;
		int w = map.getWidth(), h= map.getHeight();
		int x = pos.getX(), y = pos.getY();
		if(map.isCyclic()) {
			int x1 = (x-1);
			if(x==0) {x1 = w-1;}
			ans = map.getPixel(x1,y);
		}
		else {
			int x1 = (x-1);
			if(x1>=0) {ans = map.getPixel(x1,y);}
		}
		return ans;
	}
	/** Green state */
	private static int gotoClosestGhost(Map2D dists, Pixel2D pos) {
		int ans = -1;
		int dist = dists.getPixel(pos);
		int up = up(dists, pos);
		if(up>=0 && up==dist-1) {return Game.UP;}
		int down = down(dists, pos);
		if(down>=0 && down==dist-1) {return Game.DOWN;}
		int left = left(dists, pos);
		if(left>=0 && left==dist-1) {return Game.LEFT;}
		int right = right(dists, pos);
		if(right>=0 && right==dist-1) {return Game.RIGHT;}
		return ans;
	}

	//
	/** Black state - run away from the closest ghost.*/
	private static int runFromClosestGhost(Map2D dists, Pixel2D pos) {
		int ans = -1;
		int dist = dists.getPixel(pos);
		int up = up(dists, pos);
		if(up>0 && up==dist+1) {return Game.UP;}
		int down = down(dists, pos);
		if(down>0 && down==dist+1) {return Game.DOWN;}
		int left = left(dists, pos);
		if(left>0 && left==dist+1) {return Game.LEFT;}
		int right = right(dists, pos);
		if(right>0 && right==dist+1) {return Game.RIGHT;}
		return ans;
	}
	/** Pink state: goto the closest pink dot. */
	private static int closestPink(Map2D map, Pixel2D pos) {
		int ans = -1;
		Map2D dists = map.allDistance(pos,BLUE);
		Pixel2D pink = closestPink(map, dists);
		ans = moveTo(map, pos, pink);
		return ans;
	}
	private static int moveTo(Map2D map, Pixel2D pos, Pixel2D target) {
		int ans = -1;
		Pixel2D[] path = map.shortestPath(pos,target, BLUE);
		if(path!=null && path.length>1) {
			ans = step(map, path[0], path[1]);
		}
		return ans;
	}
	private static int step(Map2D map, Pixel2D pos, Pixel2D target) {
		int ans = -1;
		int x0 = pos.getX(), y0 = pos.getY();
		int x1 = target.getX(), y1 = target.getY();
		int w = map.getWidth(), h = map.getHeight();
		boolean cy = map.isCyclic();
		if(x0==x1) {
			if(y1==y0+1 || (cy && y1==0 && y0 == h-1)) {
				ans = Game.UP;
			}
			if(y1==y0-1 || (cy && y0==0 && y1 == h-1)) {
				ans = Game.DOWN;
			}
		}
		else {
			if(y0==y1) {
				if(x1==x0+1 || (cy && x1==0 && x0 == w-1)) {
					ans = Game.RIGHT;
				}
				if(x1==x0-1 || (cy && x0==0 && x1 == w-1)) {
					ans = Game.LEFT;
				}
			}
		}
		return ans;
	}
	private static Pixel2D closestPink(Map2D map, Map2D dists) {
		int minPink = Integer.MAX_VALUE;
		int pink = 3;//Game.getIntColor(Color.PINK);
		Index2D ans = null;
		for(int x = 0;x<map.getWidth();x++) {
			for (int y=0;y<map.getHeight();y++) {
				int d = dists.getPixel(x,y);
				int v = map.getPixel(x,y);
				if(d!=-1 && d<minPink && v == pink) {minPink = d; ans = new Index2D(x,y);}
			}
		}
		return ans;
	}
	private static Map min(Map2D orig, Map2D m2, int maxV) {
		Map ans = null;
		if(orig!=null && m2 !=null) {
			int[][] a1 = orig.getMap();
			int[][] a2 = m2.getMap();
			for(int i=0;i<a1.length;i++) {
				for(int j=0;j<a1[0].length;j++) {
					int c1 = a1[i][j];
					int c2 = a2[i][j];
					if(c2>=0 && c2<c1 && c2<maxV) {a1[i][j] = c2;}
				}
			}
			ans = new Map(a1);
		}
		return ans;
	}
}
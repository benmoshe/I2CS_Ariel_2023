package exe.ex3.sol;

import exe.ex3.game.Game;
import exe.ex3.game.PacManAlgo;
import exe.ex3.game.PacmanGame;

/**
 * Ex3, School of Computer Science, Ariel University.
 *
 * This is the "main" class for Ex3.
 * Do NOT change this class!!
 *
 * Basic roles:
 * 1. Space bar starts the game (and pause it).
 * 2. 'c' changes the cyclic mode (default is true).
 * 3. In manual mode: 'w'-up, 'a'-left, 'x'-down, 'd'-right.
 * 4. The Game (and the Gamer) parameters are defined in the Info class.
 * 4. Your are asked to implement the following classes: Index2D, Map, Ex3Algo.
 * 5. Keep in mind that in order to implement this assignment - you might want to implement few additional classes (on top of adding JUnit classes).
 * 6. The dame has 5 main "levels" ([0,4]). You are request to run&test them all.
 * 7. After each run, the system prints (in the terminal, in red) a String with your game results -
 * you are asked to upload your results (at least one for each level), part of your grade will be based on those results.
 *
 */
public class Ex3Main {
    private static Character _cmd;
    public static void main(String[] args) {
        play1();
    }
    public static void play1() {
    	Game ex3 = new Game();//new Game(level);
    	ex3.init(GameInfo.CASE_SCENARIO, GameInfo.MY_ID, GameInfo.CYCLIC_MODE, GameInfo.RANDOM_SEED, GameInfo.RESOLUTION_NORM, GameInfo.DT, -1);
        PacManAlgo man = GameInfo.ALGO;
        while(ex3.getStatus()!=PacmanGame.DONE) {
            _cmd = ex3.getKeyChar();
            if(_cmd !=null && _cmd == ' ') {ex3.play();}
            if (_cmd != null && _cmd == 'h') {
            	System.out.println("Pacman help: keys: ' '-start, 'w,a,x,d'-directions, all other parameters should be configured via GameInfo.java, ");
            }
            int  dir = man.move(ex3);
            ex3.move(dir);
        }
        ex3.end(-1);
    }
    public static Character getCMD() {return _cmd;}
}

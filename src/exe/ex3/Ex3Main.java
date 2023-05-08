package exe.ex3;
import java.awt.*;

import exe.ex3.Game;
import exe.ex3.Info;
import exe.ex3.PacManGame;

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
    public static final Color[] COLORS = {Color.BLACK, Color.BLUE, Color.YELLOW, Color.PINK, Color.RED, Color.GREEN, Color.WHITE,Color.GRAY};

    public static void main(String[] args) {
       // int level = Info.CASE_SCENARIO;
        PacManGame ex3 = new Game();//new Game(level);
        ex3.login(""+Info.MY_ID);
        play(ex3);
    }
    public static void play(PacManGame ex3) {
        while(ex3.getDotsLeft()>0 && ex3.getStatus()!=PacManGame.Status.DONE) {//ex3.getStatus()== PacManGame.Status.PLAY) {
            _cmd = ex3.getKeyChar();
            PacManGame.Status st = ex3.getStatus();
            if (_cmd != null && _cmd == 'c') {ex3.setCyclicMode(!ex3.isCyclic());}
            if(_cmd !=null && _cmd == ' ') {
                if (st == PacManGame.Status.INIT || st == PacManGame.Status.PAUSE) {
                    ex3.play();}
                else {if (st == PacManGame.Status.PLAY) {ex3.pause();}}
            }
            PacManGame.Dir dir = null;
            dir = Info.ALGO.move(ex3);
            ex3.move(dir);
        }
        ex3.end();
    }
    public static Character getCMD() {return _cmd;}
}

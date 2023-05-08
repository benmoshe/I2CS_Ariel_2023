package exe.ex3;

import exe.ex3.Ex3Main;
import exe.ex3.PacManAlgo;
import exe.ex3.PacManGame;

/**
 * Ex3, School of Computer Science, I2CS, Ariel University.
 * Simple manual method for controlling the PacMan.
 */
public class ManualAlgo implements PacManAlgo{
    public ManualAlgo() {;}
    @Override
    public String getInfo() {
        return "This is a manual algorithm for manual controlling the PacMan using w,a,x,d (up,left,down,right).";
    }

    @Override
    public PacManGame.Dir move(PacManGame game) {
        PacManGame.Dir ans = null;
        Character cmd = Ex3Main.getCMD();
            if (cmd != null) {
                if (cmd == 'w') {ans = PacManGame.Dir.UP;}
                if (cmd == 'x') {ans = PacManGame.Dir.DOWN;}
                if (cmd == 'a') {ans = PacManGame.Dir.LEFT;}
                if (cmd == 'd') {ans = PacManGame.Dir.RIGHT;}
            }
            return  ans;
    }
}

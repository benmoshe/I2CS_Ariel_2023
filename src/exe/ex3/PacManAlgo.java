package exe.ex3;

import exe.ex3.PacManGame;
import exe.ex3.PacManGame.Dir;

/**
 * This interface represents the general functionality of a PacMan.
 */
public interface PacManAlgo {
    /**
     * This method returns a short description of this PacMan algorithm.
     * @return a String as a short description this algo.
     */
    public String getInfo();

    /**
     * This is the main algorithm for the PacMan
     * @param game the PacMan game object.
     * @return a direction to witch the PacMan needs to go to.
     */
    public Dir move(PacManGame game);
}

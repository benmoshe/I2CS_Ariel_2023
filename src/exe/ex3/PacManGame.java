package exe.ex3;

/**
 * This interface represents the interface to the PacMan game.
 */
public interface PacManGame {
    public static enum Status {
        INIT, PLAY, PAUSE, DONE
    };
    public static enum Dir {
        STAY, LEFT, RIGHT, UP, DOWN;
    };
    Character getKeyChar();
    public Pixel2D getPos();
    public double getAngDeg();
    public GhostCL[] getGhosts();
    public int getScore();
    public int getLevel();
    public int[][] getGame();
    int getDotsLeft();
    public Pixel2D move(Dir dir);

    int getSteps();

    void play();

    void pause();

    void end();

    double getTimeFromStart();

    public void login(String name);
    // return a String with the results of this game (time, user, result, etc');
    public String log();

    String getData();

    public Status getStatus();
    void setCyclicMode(boolean b);
    public boolean isCyclic();
}

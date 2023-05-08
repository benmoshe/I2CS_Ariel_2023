package exe.ex3;

/**
 * Ex3, School of Computer Science, Ariel University.
 * This class represents a general API to a PacMan Ghost.
 */
public interface GhostCL {
	 public static final int INIT = 0, PLAY=1, PAUSE=2, RANDOM_WALK0=10,RANDOM_WALK1=11, GREEDY_SP=12;
    /**
     * @return the type of this Ghost (RandomWalk, Greedy Shortest Path).
     */
    public int getType();

    /**
     * @return the position (the Pixel in which this Ghost is located at).
     */
    public Pixel2D getPos();

    /**
     * @return general info (as String) describing the general movement logic of this Ghost.
     */
    public String getInfo();

    /**
     * This method return the remain time (in seconds) till this Ghost stops being "eatable". to be "
     * @return the time in seconds until this Ghost stops being "eatable" (by the PacMan).
     */
    public double remainTimeAsEatable();
    /**
     * @return the status of this Ghost (INIT, PLAY..)
     */
    public int getStatus();
}

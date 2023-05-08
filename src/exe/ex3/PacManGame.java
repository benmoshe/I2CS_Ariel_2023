package exe.ex3;

/**
 * Ex3, School of Computer Science, I2CS, Ariel University.
 * This interface represents the main API for the PacMan game.
 */
public interface PacManGame {
	/**
	 * The game states.
	 */
	public static enum Status {
		INIT, PLAY, PAUSE, DONE
	};
	/** 
	 * The movement options of the Pacman (can be null).
	 */
	public static enum Dir {
		STAY, LEFT, RIGHT, UP, DOWN;
	};
	/**
	 * @return the last Char that was pressed (and was not yet sent read). 
	 * If none - return null. 
	 */
	Character getKeyChar();
	/**
	 * @return the position (Pixel2D) of the Pacman.
	 */
	public Pixel2D getPos();
	/**
	 * Note: you do NOT need to use this method, used (indirectly) by the PacmanGame interface.
	 * @return the direction (up, down, left, right) of the Pacman.
	 */
	public double getAngDeg();
	/**
	 * Note: only ghosts with status "PLAY" should be addressed (an INIT ghost is not harmful).
	 * @return an array of all the ghosts.
	 */
	public GhostCL[] getGhosts();
	/**
	 * @return the current score (grade) of the game.
	 */
	public int getScore();
	/**
	 * @return the level of the game [0,1,2,3,4]
	 */
	public int getLevel();
	/**
	 * @return the game board (deep copy).
	 */
	public int[][] getGame();
	/**
	 * @return the number of (pink) dots remained in this game.
	 */
	int getDotsLeft();
	/**
	 * Note: you do NOT need to use this method, used (indirectly) by the PacmanGame interface.
	 * @param dir
	 * @return
	 */
	public Pixel2D move(Dir dir);
	/**
	 * @return the number of fteps taken by the Pacman.
	 */
	int getSteps();

	/**
	 * Note: you do NOT need to use this method, used (indirectly) by the PacmanGame interface.
	 * Starts (or pause-->play) this game.
	 */
	void play();
	/**
	 * Note: you do NOT need to use this method, used (indirectly) by the PacmanGame interface.
	 * Pauses this game.
	 */
	void pause();
	/**
	 * Note: you do NOT need to use this method, used (indirectly) by the PacmanGame interface.
	 * Ends this game.
	 */
	void end();
	/**
	 * @return the time in seconds since this game has started.
	 */
	double getTimeFromStart();
	/**
	 * Note: you do NOT need to use this method, used (indirectly) by the PacmanGame interface.
	 * @param name
	 */
	public void login(String name);
	/**
	 * Note: this String should be up uploaded to the Google form. 
	 * @return a String with the results of this game (time, user, result, etc'), only works after the game ends.
	 */

	public String log();
	/**
	 * @return a String with the current game parameters, can only works if the gane is still played.
	 */
	String getData();

	public Status getStatus();
	/**
	Note: you do NOT need to use this method, used (indirectly) by the PacmanGame interface.
	 * @param b the new cyclic mode = can be changed only before the game starts.
	 */
	void setCyclicMode(boolean b);
	/**
	 * @return the Cyclic mode of the underlying 2D game board .
	 */
	public boolean isCyclic();
}

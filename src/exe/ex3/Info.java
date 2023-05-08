package exe.ex3;

import exe.ex3.Ex3Algo;
import exe.ex3.ManualAlgo;
import exe.ex3.PacManAlgo;

/**
 * This class represents a simple set of parameters regarding Ex3 (all static, data only on logic).
 * Make sure to update your ID below
 */
public class Info {
    public static final long MY_ID = 1234;
    public static final int CASE_SCENARIO = 1; // [0,4]
    public static final long RANDOM_SEED = 31;
    public static final boolean CYCLIC_MODE = true;
    private static PacManAlgo _manualAlgo = new ManualAlgo();
    private static PacManAlgo _myAlgo = new Ex3Algo();
    public static final PacManAlgo ALGO = _manualAlgo;
    //public static final PacManAlgo ALGO = _myAlgo;
}

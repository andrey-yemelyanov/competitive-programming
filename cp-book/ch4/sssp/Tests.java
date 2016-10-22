package helvidios.cp.ch4.sssp;

import java.util.*;
import org.junit.Test;
import static org.junit.Assert.*;
import static java.lang.Math.*;
import static helvidios.cp.ch4.sssp._00314_Robot.*;

public class Tests {
    @Test
    public void testRobot(){
        int[][] grid = new int[][]{
                {1,1,1,1,1,1,1,1,1,1,1},
                {1,0,0,0,0,0,1,1,1,1,1},
                {1,0,0,1,1,0,0,0,1,1,1},
                {1,0,1,1,1,0,0,0,0,0,1},
                {1,0,1,1,0,0,1,1,0,0,1},
                {1,0,0,0,0,1,1,1,0,0,1},
                {1,0,0,1,1,1,1,0,0,0,1},
                {1,0,0,1,1,1,0,0,0,0,1},
                {1,1,0,0,0,0,0,0,1,1,1},
                {1,1,1,1,1,1,1,1,1,1,1}
        };

    }
}

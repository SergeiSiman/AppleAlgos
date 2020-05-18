package com.apple.algos.tests;

import com.apple.algos.DestinationOptimizer;
import org.junit.Test;

import static org.junit.Assert.*;

public class DestinationOptimizerTest {

    @Test
    public void getShortestPathBlocksNumberByGoingLeft()
    {
        int retVal = DestinationOptimizer.getShortestPathBlocksNumber("L2,L2,L2,L2");
        assertEquals(retVal, 0);
    }
    @Test
    public void getShortestPathBlocksNumberByGoingRight()
    {
        int retVal = DestinationOptimizer.getShortestPathBlocksNumber("R2,R2,R2,R2");
        assertEquals(retVal, 0);
    }
    @Test
    public void getShortestPathBlocksNumberByGoingNorth()
    {
        int retVal = DestinationOptimizer.getShortestPathBlocksNumber("R2,L2,R2,L2");
        assertEquals(retVal, 8);
    }
    @Test
    public void getShortestPathBlocksNumberByGoingSouth()
    {
        int retVal = DestinationOptimizer.getShortestPathBlocksNumber("L2,L2,R2,L2");
        assertEquals(retVal, 8);
    }
    @Test
    public void getShortestPathBlocksNumber()
    {
        // Input was given by Advent
        String input = "R4, R3, L3, L2, L1, R1, L1, R2, R3, L5, L5, R4, L4, R2, R4, L3, R3, L3, R3, R4, R2, L1, R2, L3, L2, L1, R3, R5, L1, L4, R2, L4, R3, R1, R2, L5, R2, L189, R5, L5, R52, R3, L1, R4, R5, R1, R4, L1, L3, R2, L2, L3, R4, R3, L2, L5, R4, R5, L2, R2, L1, L3, R3, L4, R4, R5, L1, L1, R3, L5, L2, R76, R2, R2, L1, L3, R189, L3, L4, L1, L3, R5, R4, L1, R1, L1, L1, R2, L4, R2, L5, L5, L5, R2, L4, L5, R4, R4, R5, L5, R3, L1, L3, L1, L1, L3, L4, R5, L3, R5, R3, R3, L5, L5, R3, R4, L3, R3, R1, R3, R2, R2, L1, R1, L3, L3, L3, L1, R2, L1, R4, R4, L1, L1, R3, R3, R4, R1, L5, L2, R2, R3, R2, L3, R4, L5, R1, R4, R5, R4, L4, R1, L3, R1, R3, L2, L3, R1, L2, R3, L3, L1, L3, R4, L4, L5, R3, R5, R4, R1, L2, R3, R5, L5, L4, L1, L1";
        int retVal = DestinationOptimizer.getShortestPathBlocksNumber(input);
        assertEquals(retVal, 288);
    }


}
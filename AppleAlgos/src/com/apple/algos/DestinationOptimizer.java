package com.apple.algos;

/*
https://adventofcode.com/2016/day/1
*/
public class DestinationOptimizer {
    private enum FacingDirection {NORTH, SOUTH, EAST,WEST};
    private enum Turn { LEFT, RIGHT};

    private static FacingDirection getNextDestination(FacingDirection currDestination, Turn nextTurn)
    {
        switch(currDestination)
        {
            case NORTH:
                if (nextTurn == Turn.LEFT)
                    return FacingDirection.WEST;
                else
                    return FacingDirection.EAST;
            case SOUTH:
                if (nextTurn == Turn.LEFT)
                    return FacingDirection.EAST;
                else
                    return FacingDirection.WEST;
            case EAST:
                if (nextTurn == Turn.LEFT)
                    return FacingDirection.NORTH;
                else
                    return FacingDirection.SOUTH;
            case WEST:
                if (nextTurn == Turn.LEFT)
                    return FacingDirection.SOUTH;
                else
                    return FacingDirection.NORTH;
            default:
                return currDestination;
        }
    }

    public static int getShortestPathBlocksNumber(String directions)
    {
        String[] inputDirections = directions.replaceAll(" ","").split(",");
        // Initial coordinates and facing direction
        int x = 0, y = 0;
        FacingDirection facingDirection = FacingDirection.NORTH;
        Turn nextTurn;

        for (String direction: inputDirections)
        {
            // Get next turn direction...
            if (direction.substring(0,1).toUpperCase().equals("R"))
                nextTurn = Turn.RIGHT;
            else if (direction.substring(0,1).toUpperCase().equals("L"))
                nextTurn = Turn.LEFT;
            else
                throw new IllegalArgumentException("Wrong direction was specified: " + direction);

            // Parse numeric number of blocks from direction
            int numBlocks = 0;
            try {
                numBlocks = Integer.parseInt(direction.substring(1));
            } catch (NumberFormatException e)
            {
                e.printStackTrace();
                throw new IllegalArgumentException("Non numeric number of blocks was specified in direction: " + direction);
            }

            switch (facingDirection)
            {
                case NORTH:
                    if (nextTurn == Turn.LEFT)
                        x -= numBlocks;
                    else
                        x += numBlocks;
                    break;
                case SOUTH:
                    if (nextTurn == Turn.LEFT)
                        x += numBlocks;
                    else
                        x -= numBlocks;
                    break;
                case EAST:
                    if (nextTurn == Turn.LEFT)
                        y += numBlocks;
                    else
                        y -= numBlocks;
                    break;
                case WEST:
                    if (nextTurn == Turn.LEFT)
                        y -= numBlocks;
                    else
                        y += numBlocks;
                    break;
                default:
                    break;
            }
            // Update facing direction
            facingDirection = getNextDestination(facingDirection,nextTurn);
        }

        return Math.abs(x) + Math.abs(y);
    }
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("Usage: DestinationOptimizer [inputDirections]");
            return;
        }

        int numBlocks = DestinationOptimizer.getShortestPathBlocksNumber(args[0]);
        System.out.println("Number of blocks away is: " + numBlocks);
    }
}

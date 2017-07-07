/*
Eurolfan, Jan Ellis D.
2010 - 29160
CMSC 170 U-7L
Exer 2 - Lights Out Solver using Tree Search Algorithms

State.java
    A class representing a state
*/

import java.util.LinkedList;

public class State
{
    int[][] board;
    LinkedList <Coord> actionsDone;

    // for creating the initial state
    public State (int [][] board)
    {
        this.board = board;
        this.actionsDone = new LinkedList <> ();
    }

    // for creating result states
    public State (int [][] board, LinkedList <Coord> actionsDone)
    {
        this.board = copyBoard (board);
        this.actionsDone = copyActionsDone (actionsDone);
    }

    // getter methods
    public int [][] getBoard ()
    {
        return this.board;
    }

    public LinkedList <Coord> getActionsDone ()
    {
        return this.actionsDone;
    }

    // setter method for board
    public void setBoard (int [][] board)
    {
        this.board = board;
    }

    // copies the content of the parameter and returns a new one
    private int [][] copyBoard (int [][] board)
    {
        int [][] array = new int [5][5];

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                array [i][j] = board [i][j];

        return (array);
    }

    // for each Coord, get the x and y and create a new Coord object before adding it to the clone list
    private LinkedList <Coord> copyActionsDone (LinkedList <Coord> actionsDone)
    {
        LinkedList <Coord> list = new LinkedList <> ();
        for (Coord coord : actionsDone)
            list.add (new Coord (coord.getX (), coord.getY ()));

        return (list);
    }
}

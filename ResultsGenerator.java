/*
Eurolfan, Jan Ellis D.
2010 - 29160
CMSC 170 U-7L
Exer 2 - Lights Out Solver using Tree Search Algorithms

ResultsGenerator.java
    A class that generates the next state from current state and action
*/

import java.util.LinkedList;

public class ResultsGenerator
{
    State state;
    Coord coord;

    // initialize the needed values
    public ResultsGenerator (State state, Coord coord)
    {
        this.state = copyState (state);
        this.coord = new Coord (coord.getX (), coord.getY ());
    }

    // create the result state
    public State getResultState ()
    {
        // apply action on intended tile
        alterTile (this.coord.getX (), this.coord.getY ());
        // apply the after-effect of said action
        alterOthers (this.coord.getX (), this.coord.getY ());
        // prepare the actions list
        LinkedList <Coord> appendedActionsList = this.state.getActionsDone ();
        // append the recently executed action
        appendedActionsList.add (new Coord (this.coord.getX (), this.coord.getY ()));
        // return a new state carrying the changes done
        return (new State (this.state.getBoard (), appendedActionsList));
    }

    // applies the intended action on the board
    private void alterTile (int i, int j)
    {
        int [][] board = state.getBoard ();

        if (board [i][j] == 0)
            board [i][j] = 1;
        else
            board [i][j] = 0;

        this.state.setBoard (board);
    }

    // applies the after-effects from a certain action
    private void alterOthers (int i, int j)
    {
        if (i == 0 && j == 0) // ok
		{
			alterTile (i+1, j);
			alterTile (i, j+1);
		}
		else if (i == 0 && j == 4) // ok
		{
			alterTile (i+1, j);
			alterTile (i, j-1);
		}
		else if (i == 4 && j == 0) // ok
		{
			alterTile (i-1, j);
			alterTile (i, j+1);
		}
		else if (i == 4 && j == 4) // ok
		{
			alterTile (i-1, j);
			alterTile (i, j-1);
		}
		else if (i == 0) // ok
		{
			alterTile (i, j-1);
			alterTile (i, j+1);
			alterTile (i+1, j);
		}
		else if (i == 4) // ok
		{
			alterTile (i, j-1);
			alterTile (i, j+1);
			alterTile (i-1, j);
		}
		else if (j == 0) // ok
		{
			alterTile (i+1, j);
			alterTile (i-1, j);
			alterTile (i, j+1);
		}
		else if (j == 4) // ok
		{
			alterTile (i+1, j);
			alterTile (i-1, j);
			alterTile (i, j-1);
		}
		else // ok
		{
			alterTile (i+1, j);
			alterTile (i-1, j);
			alterTile (i, j+1);
			alterTile (i, j-1);
		}
    }

    // copies one state into a new state
    private State copyState (State state)
    {
        // copy the actions done
        LinkedList <Coord> actionsDone = new LinkedList <> ();
        for (Coord coord : state.getActionsDone ())
            actionsDone.add (new Coord (coord.getX (), coord.getY ()));

        // copy the current board
        int [][] board = new int [5][5];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                board [i][j] = state.getBoard () [i][j];

        // return copy of the state
        return (new State (board, actionsDone));
    }
}

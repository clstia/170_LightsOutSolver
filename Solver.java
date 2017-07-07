/*
Eurolfan, Jan Ellis D.
2010 - 29160
CMSC 170 U-7L
Exer 2 - Lights Out Solver using Tree Search Algorithms

Solver.java
    The main class that attempts to solve a Lights Out Puzzle using either BFS or DFS Tree Search Algorithm
*/

import java.util.LinkedList;

public class Solver
{
    public static void main (String[] args)
    {
        // simple prompt for what algorithm to use
        System.out.println ("[1] Using BFS");
        System.out.println ("[2] Using DFS");
        System.out.print ("Enter choice: ");
        int choice = new java.util.Scanner (System.in).nextInt ();

        switch (choice)
        {
            case 1:
                System.out.println ("Solving using BFS");
                BFS ();
            break;
            case 2:
                System.out.println ("Solving using DFS");
                DFS ();
            break;
        }
    }

    // implementation of the depth first search algorithm
    private static void DFS ()
    {
        // create the initial state
        State initState = FileOp.readFile ();

        // set up the frontier stack
        LinkedList <State> frontier = new LinkedList <> ();
        frontier.push (initState);

        // implementation of DFS
        while (!frontier.isEmpty ())
        {
            // pop TOS containing the current state
            State curr = frontier.pop ();
            // if state is already the goal, write the results in the file and break loop
            if (goalTest (curr))
            {
                FileOp.writeFile (curr.getActionsDone ());
                break;
            }
            // else, push all the available actions for this state.
            else
            {
                for (Coord coord : generateActions (curr))
                {
                    frontier.push (new ResultsGenerator (curr, coord).getResultState ());
                }
            }
        }
    }

    // implementation of breadth first search algorithm
    private static void BFS ()
    {
        // create the initial state
        State initState = FileOp.readFile ();
        // prepare the frontier queue
        LinkedList <State> frontier = new LinkedList <> ();
        frontier.add (initState);

        // implementation of DFS
        while (!frontier.isEmpty ())
        {
            // dequeue the needed state
            State curr = frontier.removeFirst ();
            // if dequeued state is already the final state, write the actions to file and break
            if (goalTest (curr))
            {
                FileOp.writeFile (curr.getActionsDone ());
                break;
            }
            // else, enqueue the resulting states based on the current array's available actions
            else
            {
                for (Coord coord : generateActions (curr))
                {
                    frontier.add (new ResultsGenerator (curr, coord).getResultState ());
                }
            }
        }
    }

    // return true if said state is already the goal state. else, return false
    private static boolean goalTest (State state)
    {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (state.getBoard () [i][j] != 0)
                    return (false);

        return (true);
    }

    // generates a list of actions available to a state
    private static LinkedList <Coord> generateActions (State state)
    {
        // create a list
        LinkedList <Coord> appendedActionsList = new LinkedList <> ();

        // if the current state's actions list is empty, append all available actions
        if (state.getActionsDone ().isEmpty ())
        {
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    appendedActionsList.add (new Coord (i, j));
        }
        // else, append the action done for the current state
        else
        {
            for (Coord coord : state.getActionsDone ())
                appendedActionsList.add (new Coord (coord.getX (), coord.getY ()));

            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    int flag = 0;
                    for (Coord coord : appendedActionsList)
                    {
                        if (coord.compareCoord (new Coord (i, j)))
                        {
                            flag = 1;
                            break;
                        }
                    }
                    if (flag == 0)
                        appendedActionsList.add (new Coord (i, j));
                }
            }
        }

        return (appendedActionsList);
    }
}

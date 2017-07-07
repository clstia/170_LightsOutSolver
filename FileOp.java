/*
Eurolfan, Jan Ellis D.
2010 - 29160
CMSC 170 U-7L
Exer 2 - Lights Out Solver using Tree Search Algorithms

FileOp.java
    A class containing all the needed file operations
*/

public class FileOp
{
    // reads a file 'lightsout.in' and then creates an initial state out of it
    public static State readFile ()
    {
        // define an initial value for the state variable. this will be overwritten later
        State state = new State (new int [5][5]);
        try
        {
            // create a variable for the board
            int [][] board = new int [5][5];
            // create a stream reader
            java.io.BufferedReader br = new java.io.BufferedReader (new java.io.FileReader (new java.io.File ("lightsout.in")));
            // scan the first line
            String line = br.readLine ();
            // prepare counter for column
            int i = 0;

            while (line != null)
            {
                // prepare counter for row
                int j = 0;
                // tokenize the line. add input to array after parsing
                for (String fragment : line.split (" "))
                {
                    board [i][j] = Integer.parseInt (fragment);
                    j++;
                }
                i++;
                // get next line
                line = br.readLine ();
            }
            // close stream reader
            br.close ();
            // create new state
            state = new State (board);
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace ();
        }
        // return state
        return (state);
    }

    // writes a file 'lightsout.out' that contains the location of the lights needed to be turned on in order to solve the puzzle
    public static void writeFile (java.util.LinkedList <Coord> actionsDone)
    {
        try
        {
            // create stream writer
            java.io.BufferedWriter bw = new java.io.BufferedWriter (new java.io.FileWriter (new java.io.File ("lightsout.out")));

            // create a temporary board
            int [][] board = new int [5][5];

            // pre-fill it with zeros
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 5; j++)
                    board [i][j] = 0;

            // plot the actions needed
            for (Coord coord : actionsDone)
                board [coord.getX ()][coord.getY ()] = 1;

            // write the array into the file
            for (int i = 0; i < 5; i++)
            {
                for (int j = 0; j < 5; j++)
                {
                    bw.write (new String (board [i][j] + " "));
                }
                bw.newLine ();
            }

            // append one last line break
            bw.newLine ();
            // close stream writer
            bw.close ();
        }
        catch (java.io.IOException ioe)
        {
            ioe.printStackTrace ();
        }
    }
}

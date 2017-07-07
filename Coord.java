/*
Eurolfan, Jan Ellis D.
2010 - 29160
CMSC 170 U-7L
Exer 2 - Lights Out Solver using Tree Search Algorithms

Coord.java
    A class representing a coordinate in the board
*/

public class Coord
{
    int x;
    int y;

    public Coord (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX ()
    {
        return this.x;
    }

    public int getY ()
    {
        return this.y;
    }

    public boolean compareCoord (Coord coord)
    {
        return (this.x == coord.getX () && this.y == coord.getY ());
    }
}

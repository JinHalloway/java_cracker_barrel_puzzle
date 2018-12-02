import java.io.*;
import java.util.*;

public class PuzzleSolver
{
    //List of all possible moves
    public static int[][] moves = {
        {1,2,4}, {1,3,6},
        {2,4,7}, {2,5,9},
        {3,5,8}, {3,6,10},
        {4,2,1}, {4,5,6}, {4,7,11}, {4,8,13},
        {5,8,12}, {5,9,14},
        {6,3,1}, {6,5,4}, {6,9,13}, {6,10,15},
        {7,4,2}, {7,8,9},
        {8,5,3}, {8,9,10},
        {9,5,2}, {9,8,7},
        {10,9,8},
        {11,12,13},
        {12,8,5}, {12,13,14},
        {13,8,4}, {13,9,6}, {13,12,11}, {13,14,15},
        {14,9,5}, {14,13,12},
        {15,10,6}, {15,14,13}
    };
    
    public static ArrayList<Integer> solution = new ArrayList<Integer>();
    
    //Removes peg n from the board
    public static void removePeg(ArrayList<Integer> board, int n)
    {
        board.remove(Integer.valueOf(n));
    }
    
    //Adds peg n to the board
    public static void addPeg(ArrayList<Integer> board, int n)
    {
        board.add(Integer.valueOf(n));
    }
    
    //If peg n is on the board, retun true. Otherwise return false
    public static boolean isPeg(ArrayList<Integer> board, int n)
    {
        if(board.contains(n))
            return true;
        else
            return false;
    }

    //Prints out the board
    public static void drawBoard(ArrayList<Integer> board)
    {
        char peg[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=1; i<16; i++)
        {
            peg[i] = '.';
            if(board.contains(i))
                peg[i] = 'x';
        }
        System.out.println("     " + peg[1]);
        System.out.println("    " + peg[2] + " " + peg[3]);
        System.out.println("   " + peg[4] + " " + peg[5] + " " + peg[6]);
        System.out.println("  " + peg[7] + " " + peg[8] + " " + peg[9] + " " + peg[10]);
        System.out.println(" " + peg[11] + " " + peg[12] + " " + peg[13] + " " + peg[14] + " " + peg[15]);
    }
    
    public static ArrayList<Integer> solve(ArrayList<Integer> board)
    {
        drawBoard(board);
        if(board.size() == 1)
            return board;
        for(int i=1; i<16; i++)
        {
            if(isPeg(board, i)) //If peg is on board
            {
                for(int j=0; j<34; j++)
                {
                    if(moves[j][0] == i)
                    {
                        int over = moves[j][1];
                        int to = moves[j][2];
                        if(isPeg(board, over) == true && isPeg(board, to) == false) //If there is a peg to jump over and empty space to jump to
                        {
                            ArrayList<Integer> saveBoard = new ArrayList<Integer>(board); //Save a copy of the board to backtrack to
                            removePeg(board, i); //Remove jumping peg
                            removePeg(board, over); //Remove peg jumped over
                            addPeg(board, to); //Move jumping peg to empty space
                        
                            solution.add(i);
                            solution.add(over);
                            solution.add(to);
                        
                            board = solve(board);
                            if(board.size() == 1)
                                return board;
                                
                            //Backtrack
                            ArrayList<Integer> newBoard = new ArrayList<Integer>(saveBoard);
                            board = newBoard;
                            solution.remove(solution.size() - 1);
                            solution.remove(solution.size() - 1);
                            solution.remove(solution.size() - 1);
                        }    
                    }
                }
            }
        }
        return board;
    }
    
    public static void initSolve(int startPeg)
    {
        ArrayList<Integer> board = new ArrayList<Integer>();
        for(int i=1; i<16; i++)
            board.add(i);
        removePeg(board, startPeg);
        solve(board);
    }
    
    public static void main(String[] args)
    {
        int startPeg = 1;
        initSolve(startPeg);
 /*
        ArrayList<Integer> boardDraw = new ArrayList<Integer>();
        for(int i=1; i<16; i++)
            boardDraw.add(i);
        removePeg(boardDraw, startPeg);
        for(int j=0; j<solution.size(); j+=3)
        {
            removePeg(boardDraw, j);
            removePeg(boardDraw, j+1);
            addPeg(boardDraw, j+2);
            drawBoard(boardDraw);
        }*/
    }
}
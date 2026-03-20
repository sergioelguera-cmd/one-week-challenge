package com.codefactor.challenge.one_week.daythree;

public class Challenge02_Sol {

    /*
     * Complete the 'towerBreakers' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. INTEGER m
     */

    public static int towerBreakers(int n, int m) {
        // If the height of all towers is 1, Player 1 loses
        if (m == 1) {
            return 2;
        }
        // If the number of towers is even, Player 2 can always mirror Player 1's moves
        if (n % 2 == 0) {
            return 2;
        }
        // If the number of towers is odd, Player 1 can win
        return 1;
    }
}

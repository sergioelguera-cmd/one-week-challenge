package com.codefactor.challenge.one_week.daytwo;

import java.util.*;

import static java.util.stream.Collectors.joining;

class DayTwo_Challenge02 {

    /*
     * Complete the 'diagonalDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts 2D_INTEGER_ARRAY arr as parameter.
     */

    public static int diagonalDifference(List<List<Integer>> arr) {
        // Write your code here
        System.out.println("Array Size:" + arr.size());
       int x = arr.size();

        int sum =0;
        int sum2=0;
        for (int i = 0; i < x; i++){

                    sum += arr.get(i).get(i);
                    sum2 += arr.get(i).get(x-1-i);

            }



        System.out.println(sum);
        System.out.println(sum2);
        return Math.abs(sum-sum2);
    }

}



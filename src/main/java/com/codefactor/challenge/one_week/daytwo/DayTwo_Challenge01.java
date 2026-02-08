package com.codefactor.challenge.one_week.daytwo;

import java.util.*;

import static java.util.stream.Collectors.joining;


class DayTwo_Challenge01 {

    /*
     * Complete the 'lonelyinteger' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static int lonelyinteger(List<Integer> a) {
        // Write your code here
        //int noccur=0;
        ArrayList<Integer> in = new ArrayList<>(a);
        int res =0;
        for (int num: in){
            res = res ^ num;
        }
        return res;

    }

}




package com.codefactor.challenge.one_week.daytwo;

import java.util.List;
import java.util.*;

import static java.util.stream.Collectors.joining;

class DayTwo_Challenge03 {

    /*
     * Complete the 'countingSort' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> countingSort(List<Integer> arr) {
        // Write your code here
        int[] eval = new int[100];

        for (int i=0; i<arr.size(); i++) {
            eval[arr.get(i)] = eval[arr.get(i)] +1;
        }
        List<Integer> res = new ArrayList<>(eval.length);

        for (int i = 0; i < eval.length ; i++){
            res.add(eval[i]);
        }
        return res;
    }

}

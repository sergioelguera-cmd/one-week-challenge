package com.codefactor.challenge.one_week.daytwo;

import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;



public class DayTwo_Challenge03_Sol {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String  outputPath = System.getenv("OUTPUT_PATH");

        Writer writer = (outputPath != null)
                ? new FileWriter(outputPath)
                : new OutputStreamWriter(System.out);

        BufferedWriter bufferedWriter = new BufferedWriter(writer);

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine()
                        .replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

        List<Integer> result = DayTwo_Challenge03.countingSort(arr);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining(" "))
                        + "\n"
        );
        for (int i=0; i < result.size(); i++){
            if (result.get(i) > 0 ){
                if (result.get(i) ==1 )
                    System.out.print( i + " ");
                else if (result.get(i)>1){
                    int ter = result.get(i);
                    for (int j=0; j < ter; j++) {
                        System.out.print( i + " ");
                    }
                }
            }
        }
        System.out.println("-----");

        bufferedReader.close();
        bufferedWriter.close();
    }
}

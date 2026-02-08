package com.codefactor.challenge.one_week.daytwo;


import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DayTwo_Challenge01_Sol {
    public static void main(String[] args) throws IOException {
        String  outputPath = System.getenv("OUTPUT_PATH");
        Writer writer = (outputPath != null)
                ? new FileWriter(outputPath)
                : new OutputStreamWriter(System.out);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        System.out.println("enter size of numbers to evaluate:");
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> a = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int result = DayTwo_Challenge01.lonelyinteger(a);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

package com.codefactor.challenge.one_week.day0;
import java.io.*;
import java.util.*;
public class ConsoleReader {

        public static void main(String[] args) {
            /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
            System.out.println("Hello, World.");
            System.out.println();
            StringTokenizer st = null;
            String inpuString="";
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try{
                inpuString = br.readLine();
            }catch (IOException i){

            }



            System.out.println(inpuString);
        }


}

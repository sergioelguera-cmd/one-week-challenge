package com.codefactor.challenge.one_week.daythree;

import java.util.ArrayList;

public class Challenge03_Sol {

    public static String caesarCipher(String message, int offset){
        StringBuffer result= new StringBuffer();


        for (int i=0; i<message.length(); i++)
        {
            if (Character.isUpperCase(message.charAt(i)))
            {
                char ch = (char)(((int)message.charAt(i) + offset - 65) % 26 + 65);
                result.append(ch);
            }
            else if (Character.isLowerCase(message.charAt(i)))
            {
                char ch = (char)(((int)message.charAt(i) + offset - 97) % 26 + 97);
                result.append(ch);
            }else {
                char ch = (char)(int)message.charAt(i);
                result.append(ch);

            }

        }
        return result.toString();
    }

}

package com.andrew;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
  Created by Andrey on 25.11.2016.
 */

public class Lab7 {
    public static void main(String[] args) throws IOException {
        List<String> list = Reader.readFile("Lab7.txt");
        List<String> tokens = findTokens(list);
        List<String> tokensNoDigits = findTokensNoDigit(tokens);
        List<String> tokensWithDigits = findTokensWithDigit(tokens);
        sortAndWrite(tokensNoDigits, tokensWithDigits);
    }

    private static List<String> findTokens(List<String> list) throws IOException {
        List<String> tokens = new ArrayList<>();
        for (String s : list) {
            StringTokenizer st = new StringTokenizer(s, " .,");
            while (st.hasMoreTokens()) {
                tokens.add(st.nextToken());
            }
        }
        return tokens;
    }

    private static List<String> findTokensWithDigit(List<String> tokens) throws IOException {
        List<String> tokensWithDigits = new ArrayList<>();
        for (String s : tokens) {
            if (isDigit(s)) {
                tokensWithDigits.add(s);
            }
        }
        return tokensWithDigits;
    }

    private static List<String> findTokensNoDigit(List<String> tokens) throws IOException {
        List<String> tokensNoDigits = new ArrayList<>();
        for (String s : tokens) {
            if (notDigit(s)) {
                tokensNoDigits.add(s);
            }
        }
        return tokensNoDigits;
    }

    private static void sortAndWrite(List<String> tokensNoDigits, List<String> tokensWithDigits) throws IOException {
        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };

        Collections.sort(tokensNoDigits, comparator);
        Collections.sort(tokensWithDigits, comparator);

        int countDigit = tokensWithDigits.size();
        int countNoDigit = tokensNoDigits.size();

        System.out.println("Words \"digits\" " + countDigit);
        System.out.println("Words \"no digits\" " + countNoDigit);

        Reader.writeFileInOne("output1.txt", tokensWithDigits);
        Reader.writeFileInOne("output2.txt", tokensNoDigits);
    }

    private static boolean isDigit(String s) {
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(s);
        return m.find();
    }

    private static boolean notDigit(String s) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(s);
        return !m.find();
    }

}

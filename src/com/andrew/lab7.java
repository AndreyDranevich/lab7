package com.andrew;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Andrey on 25.11.2016.
 */

public class lab7 {
    public static void main(String[] args) throws IOException {
        findTokens();
    }

    static void findTokens() throws IOException {
        List<String> list = Reader.readFile("lab7.txt");
        List<String> lexem = new ArrayList<>();
        List<String> lexemNoDigits = new ArrayList<>();
        List<String> lexemWithDigits = new ArrayList<>();
        int countDigit = 0;
        int countNoDigit = 0;

        for (String s : list) {
            StringTokenizer st = new StringTokenizer(s, " .,");
            while (st.hasMoreTokens()) {
                lexem.add(st.nextToken());
            }
        }
        for (String s : lexem) {
            if (isDigit(s)) {
                lexemWithDigits.add(s);
                countDigit++;
            }
            if (notDigit(s)) {
                lexemNoDigits.add(s);
                countNoDigit++;
            }
        }

        Comparator<String> comparator = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };

        Collections.sort(lexemNoDigits, comparator);
        Collections.sort(lexemWithDigits, comparator);

        System.out.println("Words \"digits\" " + countDigit);
        System.out.println("Words \"no digits\" " + countNoDigit);

        Reader.writeFileInOne("output1.txt", lexemWithDigits);
        Reader.writeFileInOne("output2.txt", lexemNoDigits);

    }

    static boolean isDigit(String s) {
        Pattern p = Pattern.compile("^[0-9]+$");
        Matcher m = p.matcher(s);
        return m.find();
    }

    static boolean notDigit(String s) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(s);
        return !m.find();
    }

}

package org.cba.loan;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class KeyValuesFromFile {
    final static String filePath = "src/main/java/org/cba/loan/TestFile.txt";

    public ArrayList < ListOfPairs > addToListOfPairs() {

        ArrayList < ListOfPairs > listOfPairs = new ArrayList<>();

        BufferedReader br = null;
        try {
            File file = new File(filePath);

            br = new BufferedReader(new FileReader(file));

            String line = null;
            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                String keyWord = "[record";
                String[] parts = new String[0];

                if (line.contains(keyWord) && line.contains("\"")) {
                    parts = line.split("\"");
                    String key = parts[0].trim();
                    Pattern p = Pattern.compile(".*\" *(.*) *\"].*");
                    Matcher m = p.matcher(line);
                    m.find();
                    String value = m.group(1);

                    //    String value = line.substring(line.indexOf('\"') + 1, line.indexOf(']'));
                if (!key.equals(""))

                    listOfPairs.add(new ListOfPairs(key, value));
                } else if (!line.contains(keyWord) && line.contains("[")) {
                    parts = line.split("\\[");
                    String key = parts[0].trim();
                    String value = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
                    if (!key.equals(""))

                    listOfPairs.add(new ListOfPairs(key, value));
                } else if (!line.contains(keyWord)) {
                    parts = line.split("\"");
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    if (!key.equals(""))

                    listOfPairs.add(new ListOfPairs(key, value));
                }
                lineCount++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {};
            }
        }
        return listOfPairs;
    }

}
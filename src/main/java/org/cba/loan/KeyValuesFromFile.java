package org.cba.loan;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


class KeyValuesFromFile {
    final static String filePath = "src/main/java/org/cba/loan/TestFile.txt";

    public ArrayList < ListOfPairs > addToListOfPairs() {

        ArrayList < ListOfPairs > listOfPairs = new ArrayList < > ();
        WriteToExcelFile writeToExcelFile = new WriteToExcelFile();
        BufferedReader br = null;
        try {
            File file = new File(filePath);

            br = new BufferedReader(new FileReader(file));

            String line;
            String record_key = "[record";
            String[] kv_parts;

            while ((line = br.readLine()) != null) {

                if (line.contains("]]") && line.contains("\"")) { //For the lines contains [record and key/values pair. E.g. Line # 29
                    kv_parts = line.split("\"");
                    String key = kv_parts[0].trim();
                    Pattern p = Pattern.compile(".*\" *(.*) *\"].*");
                    Matcher m = p.matcher(line);
                    m.find();
                    String value = m.group(1);
                    //    String value = line.substring(line.indexOf('\"') + 1, line.indexOf(']'));
                    if (!key.equals(""))
                        listOfPairs.add(new ListOfPairs(key, value));
                    writeToExcelFile.writeToExcelFile(listOfPairs);
                    Thread.sleep(1000);
                    listOfPairs.clear();
                } else if (!line.contains(record_key) && line.contains("[")) { //This is to parse the lines that contains values in []. E.g. Line # 27 & 28
                    kv_parts = line.split("\\[");
                    String key = kv_parts[0].trim();
                    String value = line.substring(line.indexOf('[') + 1, line.indexOf(']'));
                    if (!key.equals(""))
                        listOfPairs.add(new ListOfPairs(key, value));
                } else if (!line.contains(record_key)) { //This is to parse the lines that contain Key / value pair only without "[record" or "SZ[record" key. E.g. Line # 4 & 5
                    kv_parts = line.split("\"");
                    String key = kv_parts[0].trim();
                    String value = kv_parts[1].trim();
                    if (!key.equals(""))
                        listOfPairs.add(new ListOfPairs(key, value));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return listOfPairs;
    }

}
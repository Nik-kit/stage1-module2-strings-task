package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.StringTokenizer;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {

        for (String symbol : delimiters){
            source = source.replaceAll(symbol,",");
        }

        while (source.contains(",,")){
            source = source.replace(",,",",");
        }

        List<String> delimitersString = new ArrayList<>();

        StringTokenizer stringTokenizer = new StringTokenizer(source, ",");

        while(stringTokenizer.hasMoreTokens()){
            delimitersString.add(stringTokenizer.nextToken());
        }

        if(delimitersString != null){
            return delimitersString;
        } else {
            throw new UnsupportedOperationException("You should implement this method.");
        }
    }
}

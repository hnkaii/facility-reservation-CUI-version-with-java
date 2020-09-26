package io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public final class CSVFileReader {

    private static final String COMMA_STRING = ",";

    private File csvFile = null;

    private BufferedReader reader = null;

    private boolean quoteRemoving = false;

    private char[] quoteChars = {'\'', '"'};

    public CSVFileReader(String path) throws FileNotFoundException {
        csvFile = new File(path);
    }

    public List<String[]> read() throws IOException {
        List<String[]> columnsList = null;
        try {
            initReader();
            columnsList = readFromCsv();
        } finally {
            closeReaderQuietly();
        }
        return columnsList;
    }

    private void initReader() throws IOException {
        reader = new BufferedReader(new FileReader(csvFile));
    }

    private void closeReaderQuietly() {
        if (reader == null) {
            return;
        }

        try {
            reader.close();
        } catch (IOException e) {

        } finally {
            reader = null;
        }
    }

    private List<String[]> readFromCsv() throws IOException {
        List<String[]> columnsList = new ArrayList<String[]>();

        String line = null;
        while ((line = reader.readLine()) != null) {
            columnsList.add(makeColumnArray(line));
        }
        return columnsList;
    }

    private String[] makeColumnArray(String line) {
        if (quoteRemoving) {
            line = removeQuoteChar(line);
        }
        return line.split(COMMA_STRING, -1);
    }

    private String removeQuoteChar(String source) {
        for (char quote : quoteChars) {
            source = source.replaceAll(Character.toString(quote), "");
        }
        return source;
    }

    public void setQuoteRemoving(boolean isRemoved) {
        quoteRemoving = isRemoved;
    }

    public void setQuoteChars(char[] strs) {
        if (strs == null) {
            quoteChars = new char[0];
            return;
        }
        quoteChars = strs;
    }

    public void setQuoteChars(String str) {
        if (str == null) {
            quoteChars = new char[0];
            return;
        }
        quoteChars = str.toCharArray();
    }
}

package io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class CSVFileWriter {

    private static final String COMMA_STRING = ",";

    private File csvFile = null;

    private BufferedWriter writer = null;

    private boolean quoteAdding = false;

    private char quoteChar = '"';

    public CSVFileWriter(String path) throws FileNotFoundException {
        csvFile = new File(path);
    }

    public void overwrite(List<String[]> columnsList) throws IOException {
        try {
            initWriter(false);
            writeToCsv(columnsList);
            flush();
        } finally {
            closeQuietly();
        }
    }

    public void append(String[] columns) throws IOException {
        try {
            initWriter(true);
            writeToCsv(columns);
            flush();
        } finally {
            closeQuietly();
        }
    }

    private void initWriter(boolean append) throws IOException {
        writer = new BufferedWriter(new FileWriter(csvFile, append));
    }

    private void flush() throws IOException {
        writer.flush();
    }

    private void closeQuietly() {
        if (writer == null) {
            return;
        }

        try {
            writer.close();
        } catch (IOException e) {
        } finally {
            writer = null;
        }
    }

    private void writeToCsv(List<String[]> columnsList) throws IOException {
        for (String[] columns : columnsList) {
            writeToCsv(columns);
        }
    }

    private void writeToCsv(String[] columns) throws IOException {
        String line = makeLine(columns);
        writer.write(line);
        writer.newLine();
    }

    private String makeLine(String[] columns) {
        if (columns.length == 0) {
            return "";
        }
        StringBuffer lineSb = new StringBuffer();
        for (int i = 0; i < columns.length - 1; i++) {
            lineSb.append(makeQuoteLineIfRequired(columns[i]));
            lineSb.append(COMMA_STRING);
        }
        lineSb.append(makeQuoteLineIfRequired(columns[columns.length - 1]));
        return lineSb.toString();
    }

    private String makeQuoteLineIfRequired(String column) {
        if (!quoteAdding) {
            return column;
        }
        return quoteChar + column + quoteChar;
    }

    public void setQuoteAdding(boolean isAdded) {
        quoteAdding = isAdded;
    }

    public void setQuoteChar(char quoteChar) {
        this.quoteChar = quoteChar;
    }
}

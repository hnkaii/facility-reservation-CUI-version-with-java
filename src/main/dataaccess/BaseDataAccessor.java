package main.dataaccess;

import io.CSVFileReader;
import io.CSVFileWriter;

import java.io.IOException;
import java.util.List;

class BaseDataAccessor {

    private String filePath;

    BaseDataAccessor(String csvFilePath) {
        setFilePath(csvFilePath);
    }

    String getFilePath() {
        return filePath;
    }

    void setFilePath(String csvFilePath) {
        filePath = csvFilePath;
    }

    int load(List<String[]> dataList) throws IOException {
        CSVFileReader fileReader = new CSVFileReader(filePath);
        List<String[]> readDataList = fileReader.read();
        dataList.addAll(readDataList);
        return readDataList.size();
    }

    void save(String[] targetData) throws IOException {
        CSVFileWriter writer =new CSVFileWriter(filePath);
        writer.append(targetData);
    }

}

package main.dataaccess;

import exception.DataFormatException;
import exception.DataIOException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class FacilityDataAccessor {

    private static final String DEFAULT_FILE_PATH = "csv/facility.csv";

    private BaseDataAccessor baseAccessor;

    private List<String[]> equipmentDataList;

    public FacilityDataAccessor() {
        this(DEFAULT_FILE_PATH);
    }

    public FacilityDataAccessor(String filePath) {
        baseAccessor = new BaseDataAccessor(filePath);
    }

    private List<String[]> load() throws DataIOException {

        try {
            List<String[]> equipmentDataList = new ArrayList<String[]>();
            baseAccessor.load(equipmentDataList);
            return equipmentDataList;
        } catch (IOException e) {
            throw new DataIOException("Reservation Management File cannot be read", e);
        }
    }

    public List<String[]> getAllEquipmentData() throws DataIOException {
        equipmentDataList = load();
        if (equipmentDataList != null) {
            return equipmentDataList;
        }
        return null;
    }

    public String[] findEquipment(int targetEquipmentNumber)
            throws DataIOException, DataFormatException {

        try {
            List<String[]> equipmentDataList = load();
            for (String[] equipmentData : equipmentDataList) {
                int equipmentNumber = Integer.parseInt(equipmentData[0]);

                if (targetEquipmentNumber == equipmentNumber) {
                    return equipmentData;
                }
            }
            return null;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException(
                    "Invalid Data (Invalid Facility Number)", e);
        } catch (NumberFormatException e) {
            throw new DataFormatException(
                    "Facility Number should be numeric", e);
        }
    }

    public String[] findEquipment(String targetEquipmentName)
            throws DataIOException, DataFormatException {
        try {
            equipmentDataList = load();
            for (String[] equipmentData : equipmentDataList) {
                if (targetEquipmentName.equals(equipmentData[1])) {
                    return equipmentData;
                }
            }
            return null;
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException("Invalid Data (Invalid Facility Number)", e);
        }
    }

    public int getNextSeq() throws DataIOException {
        equipmentDataList= load();
        int numberOfEquipment = equipmentDataList.size();
        return numberOfEquipment + 1;
    }

    private void save(String[] equipmentData) throws DataIOException {
        try {
            baseAccessor.save(equipmentData);
        } catch (IOException e) {
            throw new DataIOException("Failed to write to Reservation Management File", e);
        }
    }

    public void addEquipment(String[] equipmentData) throws DataIOException {
        save(equipmentData);
    }
}

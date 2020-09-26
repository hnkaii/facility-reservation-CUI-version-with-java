package main.business;

import exception.DataFormatException;
import exception.DataIOException;
import main.dataaccess.FacilityDataAccessor;

import java.util.ArrayList;
import java.util.List;


public class FacilityMaster {

    private FacilityDataAccessor facilityDataAccessor;

    public FacilityMaster() {
        facilityDataAccessor = new FacilityDataAccessor();
    }

    public FacilityMaster(FacilityDataAccessor dataAccessor) {
        facilityDataAccessor = dataAccessor;
    }

    private Facility convertToEquipment(String[] equipmentData)
            throws DataFormatException {
        try {
            int number = Integer.parseInt(equipmentData[0]);
            String equipmentName = equipmentData[1];
            int capacity = Integer.parseInt(equipmentData[2]);
            String extensionNumber = equipmentData[3];
            Facility anFacility = new Facility(number, equipmentName,
                    capacity, extensionNumber);

            return anFacility;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException("The configuration of the facility management file is invalid", e);

        } catch (NumberFormatException e) {
            throw new DataFormatException(
                    "Failed to convert the data (facility number or capacity) in the facility management file to a numerical value", e);
        }
    }

    public List<Facility> getAllEquipments() throws DataIOException, DataFormatException {
        List<Facility> facilities = new ArrayList<Facility>();
        List<String[]> equipmentDataList = facilityDataAccessor.getAllEquipmentData();
        for (String[] equipmentData : equipmentDataList) {
            Facility anFacility = convertToEquipment(equipmentData);
            facilities.add(anFacility);

        }

        return facilities;

    }

    public Facility findEquipment(int targetEquipmentNumber)
            throws DataIOException, DataFormatException {
        String[] equipmentData = facilityDataAccessor.findEquipment(targetEquipmentNumber);

        if (equipmentData != null) {
            Facility foundFacility = convertToEquipment(equipmentData);

            return foundFacility;

        } else {
            return null;
        }
    }

    public Facility findEquipment(String newEquipmentName)
            throws DataIOException, DataFormatException {

        String[] equipmentData = facilityDataAccessor.findEquipment(newEquipmentName);
        if (equipmentData != null) {
            Facility foundFacility = convertToEquipment(equipmentData);
            return foundFacility;
        } else {
            return null;
        }
    }

    public Facility addEquipment(String equipmentName,
                                 int capacity, String extensionNumber)
            throws DataFormatException, DataIOException {

        int newEquipmentNumber = facilityDataAccessor.getNextSeq();
        String[] newEquipmentData = new String[4];
        newEquipmentData[0] = String.valueOf(newEquipmentNumber);
        newEquipmentData[1] = String.valueOf(equipmentName);
        newEquipmentData[2] = String.valueOf(capacity);
        newEquipmentData[3] = String.valueOf(extensionNumber);

        facilityDataAccessor.addEquipment(newEquipmentData);
        Facility newFacility = findEquipment(newEquipmentNumber);
        return newFacility;

    }
}

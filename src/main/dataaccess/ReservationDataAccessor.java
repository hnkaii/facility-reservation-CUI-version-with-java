package main.dataaccess;

import exception.DataFormatException;
import exception.DataIOException;
import utility.DateUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDataAccessor {

    private static final String DEFAULT_FILE_PATH = "csv/reservation.csv";

    private BaseDataAccessor baseAccessor;

    private List<String[]> reservationDataList;

    public ReservationDataAccessor() {
        this(DEFAULT_FILE_PATH);
    }

    public ReservationDataAccessor(String filePath) {
        baseAccessor = new BaseDataAccessor(filePath);
    }

    private List<String[]> load() throws DataIOException {
        try {
            List<String[]> reservationDataList = new ArrayList<String[]>();
            baseAccessor.load(reservationDataList);
            return reservationDataList;
        } catch (IOException e) {
            throw new DataIOException("Reservation Management File cannot be read", e);
        }
    }

    public List<String[]> findReservation(int targetEquipmentNumber, LocalDate targetDate)
            throws DataIOException, DataFormatException {

        try {
            List<String[]> foundReservationList = new ArrayList<>();
            reservationDataList = load();
            for (String[] reservationData : reservationDataList) {
                LocalDateTime startDateTime = DateUtils.convertStringToDateTime(reservationData[1]);
                LocalDate startDate = startDateTime.toLocalDate();
                int equipmentNumber = Integer.parseInt(reservationData[4]);
                if (startDate.equals(targetDate)
                        && equipmentNumber == targetEquipmentNumber) {
                    foundReservationList.add(reservationData);
                }
            }
            return foundReservationList;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException("--- NO RESERVATION FOUND ON THE DAY INQUIRED ---", e);
        } catch (DateTimeParseException e) {
            throw new DataFormatException ("Date format should be YYYY/MM/DD", e);
        } catch (NumberFormatException e) {
            throw new DataFormatException ("Facility Number should be numeric", e);
        }
    }


    public String[] findReservation(int targetEquipmentNumber,
            LocalDateTime targetStartDateTime, LocalDateTime targetEndDateTime)
             throws DataIOException, DataFormatException {

        try {
            List<String[]> reservationDataList = load();
            for (String[] reservationData : reservationDataList) {
                int equipmentNumber = Integer.parseInt(reservationData[4]);
                if (equipmentNumber==targetEquipmentNumber) {
                    LocalDateTime startDateTime =
                            DateUtils.convertStringToDateTime(reservationData[1]);
                    LocalDateTime endDateTime =
                            DateUtils.convertStringToDateTime(reservationData[2]);
                    boolean isOverlapped =DateUtils.isOverlappedTerm(
                            startDateTime, endDateTime, targetStartDateTime, targetEndDateTime);
                    if (isOverlapped) {
                        return reservationData;
                    }
                }
            }
            return null;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException(
                        "The data in the reservation management file " +
                                "(start date / time, end date / time or facility number position) " +
                                "is invalid", e);
        } catch ( DateTimeParseException e) {
            throw new DataFormatException(
                        "Failed to convert the date and time of the reservation management file data " +
                                "(start date and time or end date and time)", e);

        } catch (NumberFormatException e) {
            throw new DataFormatException(
                        "Failed to convert the data (facility number) in the reservation management file to a numerical value", e);

        }
    }


    public String[] findReservation(int targetReservationNumber)
            throws DataIOException, DataFormatException {

        try {
            List<String[]> reservationDataList = load();
            for (String[] reservationData : reservationDataList) {
                int reservationNumber = Integer.parseInt(reservationData[0]);
                if (targetReservationNumber == reservationNumber) {
                    return reservationData;
                }

            }
            return null;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException(
                        "The location of the data (reservation number) in the reservation management file is invalid", e);
        } catch (NumberFormatException e) {
            throw new DataFormatException(
                        "Failed to convert the data (reservation number) in the reservation management file to a numerical value", e);
        }
    }


    public int getNextSeq() throws DataIOException {
        List<String[]> reservationDataList = load();
        int numberOfReservation =reservationDataList.size();
        return numberOfReservation + 1;
    }


    private void save(String[] reservationData) throws DataIOException {
        try {
            baseAccessor.save(reservationData);
        } catch (IOException e) {
            throw new DataIOException("Failed to write to Reservation Management File", e);
        }
    }


    public void addReservation(String[] newReservationData)
            throws DataIOException {
        save(newReservationData);
    }

}

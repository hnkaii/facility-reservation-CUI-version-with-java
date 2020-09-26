package main.business;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import exception.DataFormatException;
import exception.DataIOException;
import main.dataaccess.ReservationDataAccessor;
import utility.DateUtils;

public class ReservationMaster {

    private ReservationDataAccessor reservationDataAccessor;

    public ReservationMaster() {
        reservationDataAccessor = new ReservationDataAccessor();
    }

    public ReservationMaster(ReservationDataAccessor dataAccessor) {
        reservationDataAccessor = dataAccessor;
    }

    private Reservation convertToReservation(String[] reservationData)
            throws DataFormatException {

        try {
            int reservationNumber = Integer.parseInt(reservationData[0]);
            LocalDateTime startDateTime = DateUtils.convertStringToDateTime(reservationData[1]);
            LocalDateTime endDateTime = DateUtils.convertStringToDateTime(reservationData[2]);
            String purpose = reservationData[3];
            int equipmentNumber = Integer.parseInt(reservationData[4]);
            int userAccountNumber = Integer.parseInt(reservationData[5]);
            Reservation foundReservation
                            = new Reservation(reservationNumber, startDateTime, endDateTime, purpose, equipmentNumber, userAccountNumber);
            return foundReservation;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException("The configuration of the reservation management file is invalid", e);

        } catch (NumberFormatException e) {
            throw new DataFormatException("Failed to convert the data in the reservation management file (reservation number, facility number or user account number) to a numerical value", e);

        } catch (DateTimeParseException e) {
            throw new DataFormatException("Failed to convert the date of the reservation management file data (start date and time or end date and time)", e);
        }
    }


    public List<Reservation> findReservation(int targetEquipmentNumber, LocalDate targetDate)
            throws DataIOException, DataFormatException {
        List<String[]> reservationDataList = reservationDataAccessor.findReservation(
                                                    targetEquipmentNumber, targetDate);
        List<Reservation> foundReservationList = new ArrayList<Reservation>();
        for (String[] reservationData : reservationDataList) {

            Reservation foundReservation = convertToReservation(reservationData);
            foundReservationList.add(foundReservation);
        }

        return foundReservationList;
    }

    public Reservation findReservation(int targetEquipmentNumber,
            LocalDateTime targetStartDateTime, LocalDateTime targetEndDateTime)
            throws DataIOException, DataFormatException {
        String [] reservationData = reservationDataAccessor.findReservation(targetEquipmentNumber, targetStartDateTime, targetEndDateTime);
        if (reservationData != null) {
            Reservation foundReservation = convertToReservation(reservationData);
            return foundReservation;

        } else {
            return null;
        }
    }

    public Reservation findReservation(int targetReservationNumber)
            throws DataIOException, DataFormatException {
        String[] reservationData
                    = reservationDataAccessor.findReservation(targetReservationNumber);

        if (reservationData != null) {
            Reservation foundReservation = convertToReservation(reservationData);
            return foundReservation;

        } else {
            return null;
        }

    }


    public Reservation addReservation(int userAccountNumber, int equipmentNumber,
                                        LocalDateTime startDateTime, LocalDateTime endDateTime,
                                        String purpose)
                                        throws DataFormatException, DataIOException {
        int newReservationNumber = reservationDataAccessor.getNextSeq();
        String startDateTimeStr = DateUtils.convertDateTimeToString(startDateTime);
        String endDateTimeStr = DateUtils.convertDateTimeToString(endDateTime);
        String[] newReservationData = new String[6];
        newReservationData[0] = String.valueOf(newReservationNumber);
        newReservationData[1] = String.valueOf(startDateTimeStr);
        newReservationData[2] = String.valueOf(endDateTimeStr);
        newReservationData[3] = String.valueOf(purpose);
        newReservationData[4] = String.valueOf(equipmentNumber);
        newReservationData[5] = String.valueOf(userAccountNumber);
        reservationDataAccessor.addReservation(newReservationData);
        Reservation newReservation = findReservation(newReservationNumber);

        return newReservation;
    }
}

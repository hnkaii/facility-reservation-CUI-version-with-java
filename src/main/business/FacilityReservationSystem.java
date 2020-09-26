package main.business;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import exception.DataFormatException;
import exception.DataIOException;
import exception.DataValueException;

public class FacilityReservationSystem {

    private FacilityMaster facilityMaster;

    private ReservationMaster reservationMaster;

    private UserAccountMaster userAccountMaster;

    public FacilityReservationSystem() {
        setupDefaultMaster();
    }

    public FacilityReservationSystem(
            FacilityMaster facilityMaster,
            ReservationMaster reservationMaster,
            UserAccountMaster userAccountMaster) {
        this.facilityMaster = facilityMaster;
        this.reservationMaster = reservationMaster;
        this.userAccountMaster = userAccountMaster;

        setupDefaultMaster();
    }

    private void setupDefaultMaster() {
        if (this.userAccountMaster == null) {
            this.userAccountMaster = new UserAccountMaster();
        }
        if (this.facilityMaster == null) {
            this.facilityMaster = new FacilityMaster();
        }
        if (this.reservationMaster == null) {
            this.reservationMaster = new ReservationMaster();
        }
    }

    public UserAccount findUserAccount(String loginName, String password)
            throws DataIOException, DataValueException, DataFormatException {
        UserAccount foundUserAccount = userAccountMaster.findUserAccount(loginName, password);

        if (foundUserAccount != null) {
            return foundUserAccount;

        } else {
            throw new DataValueException("--- LOGIN NAME OR PASSWORD IS INVALID ---");
        }
    }

    public UserAccount findUserAccount(int targetUserAccountNumber)
            throws DataIOException, DataFormatException {
        UserAccount foundAccount = userAccountMaster.findUserAccount(targetUserAccountNumber);
        return foundAccount;
    }

    public List<Reservation> findReservation(int targetEquipmentNumber, LocalDate targetDate)
            throws DataIOException, DataValueException, DataFormatException {
        List<Reservation> foundReservationList = reservationMaster.findReservation(targetEquipmentNumber, targetDate);
        for (Reservation foundReservation : foundReservationList) {
            int foundEquipmentNumber = foundReservation.getEquipmentNumber();
            Facility foundFacility = findEquipment(foundEquipmentNumber);
            foundReservation.setEquipment(foundFacility);
            int foundUserAccountNumber = foundReservation.getUserAccountNumber();
            UserAccount foundReservedUser = userAccountMaster.findUserAccount(foundUserAccountNumber);
            foundReservation.setUserAccount(foundReservedUser);
        }

        if (foundReservationList.size() > 0) {
            return foundReservationList;

        } else {
            throw new DataValueException("--- NO RESERVATION FOUND ON THE DAY INQUIRED ---");

        }
    }

    public List<Facility> getAllEquipments() throws DataIOException, DataFormatException {
        List<Facility> facilities = facilityMaster.getAllEquipments();
        return facilities;
    }

    public Facility findEquipment(int targetEquipmentNumber)
            throws DataIOException, DataFormatException {
        Facility foundFacility = facilityMaster.findEquipment(targetEquipmentNumber);
        return foundFacility;
    }

    public Reservation addReservation(int equipmentNumber,
            LocalDateTime startDateTime, LocalDateTime endDateTime,
            String purpose, UserAccount subscriber)
            throws DataIOException, DataValueException, DataFormatException {
        Facility foundFacility = facilityMaster.findEquipment(equipmentNumber);
        if (foundFacility != null) {
            Reservation foundReservation = reservationMaster.findReservation(
                    equipmentNumber, startDateTime, endDateTime);

            if (foundReservation == null) {
                int loginUserNumber = subscriber.getNumber();
                Reservation newReservation = reservationMaster.addReservation(loginUserNumber, equipmentNumber,
                        startDateTime, endDateTime, purpose);
                newReservation.setEquipment(foundFacility);
                newReservation.setUserAccount(subscriber);
                return newReservation;

            } else {
                throw new DataValueException("--- ALREADY RESERVED ---");
            }
        } else {
            throw new DataValueException("The corresponding facility number does not exist");
        }
    }

    public Facility addEquipment(
            String newEquipmentName, int newCapacity, String newExtensionNumber)
            throws DataIOException, DataValueException, DataFormatException {
        Facility foundFacility = facilityMaster.findEquipment(newEquipmentName);
        if (foundFacility == null) {
            Facility newFacility = facilityMaster.addEquipment(newEquipmentName, newCapacity, newExtensionNumber);
            foundFacility = newFacility;
        } else {
            throw new DataValueException("The facility you entered is already registered");
        }
        return foundFacility;

    }

    public UserAccount addUserAccount(
                        String loginName, String password,
                        String realName, String extensionNumber,
                        String divisionName, int authority)
                                throws DataIOException, DataValueException, DataFormatException, IOException {
        UserAccount existingUserAccount = userAccountMaster.findUserAccount(loginName);
        if (existingUserAccount == null) {
            UserAccount newUserAccount = userAccountMaster.addUserAccount(loginName, password, realName, extensionNumber, divisionName, authority);
            existingUserAccount = newUserAccount;
            return newUserAccount;
        } else {
            throw new DataValueException ("The login name is already registered");
        }

    }
}

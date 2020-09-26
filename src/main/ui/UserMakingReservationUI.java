package main.ui;

import exception.DataFormatException;
import exception.DataIOException;
import exception.DataValueException;
import main.business.FacilityReservationSystem;
import main.business.Facility;
import main.business.Reservation;
import main.business.UserAccount;
import utility.AppValidator;
import utility.DateUtils;
import utility.UIUtility;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;


public class UserMakingReservationUI {

    private FacilityReservationSystem equipReservationSystem;

    public UserMakingReservationUI(FacilityReservationSystem system) {
        equipReservationSystem = system;
    }

    public void reserveEquipment(UserAccount loginUser) {
        String loginUserName = loginUser.getRealName();
        while (true) {
            try {
                UIUtility.showHeader("FACILITY RESERVATION", loginUserName, true);
                String msg = "Please enter the facility number, start date&time, end date&time, and purpose of use\r\n"
                           + "Press -1 to quit";
                UIUtility.printMessage(msg);
                showEquipmentList();
                int inputEquipmentNumber = 0;

                try {
                    inputEquipmentNumber = UIUtility.getInputAsInt("Facility Number: ");
                    if (inputEquipmentNumber == -1) {
                        break;
                    }

                } catch (NumberFormatException e) {
                    UIUtility.printMessage("Facility Number should be numeric");
                    continue;
                }
                LocalDateTime startDateTime = null;
                LocalDateTime endDateTime = null;

                try {
                    String inputStartDateTime = UIUtility.getInputAsString(
                                                    "Start date & time format（Eg:2002/10/31 10:00): ");
                    startDateTime = DateUtils.convertStringToDateTime(inputStartDateTime);
                    String inputEndDateTime = UIUtility.getInputAsString(
                                                    "End date & time format（Eg:2002/10/31 10:00): ");
                    endDateTime = DateUtils.convertStringToDateTime(inputEndDateTime);

                } catch (DateTimeParseException e) {
                    UIUtility.printMessage("Enter the date and time in YYYY / MM / DD hh: mm");
                    continue;
                }
                String inputPurpose = UIUtility.getInputAsString("Purpose of use (Optional): ");
                System.out.println();
                if (!AppValidator.isFutureDateTime(startDateTime)) {
                    continue;
                }
                if (!AppValidator.isFutureDateTime(endDateTime)) {
                    continue;
                }
                if (!AppValidator.isValidTerm(startDateTime, endDateTime)) {
                    continue;
                }
                System.out.println();
                Reservation newReservation = equipReservationSystem.addReservation(
                                                inputEquipmentNumber,
                                                startDateTime, endDateTime, inputPurpose, loginUser);
                showReservationInfo(newReservation);
                break;

            } catch (DataValueException e) {
                UIUtility.printMessage(e.getMessage());

            } catch (DataFormatException e) {
                UIUtility.printMessage(e.getMessage());

            } catch (DataIOException e) {
                UIUtility.printMessage(e.getMessage());
            }
        }
    }

    private void showReservationInfo(Reservation reservation) {
        UserAccount subscriber = reservation.getUserAccount();
        String subscriberName = subscriber.getRealName();
        UIUtility.showHeader("FACILITY RESERVATION COMPLETED", subscriberName, true);
        UIUtility.printMessage("The following reservation has been confirmed.");
        int reservationNumber = reservation.getNumber();
        Facility facility = reservation.getEquipment();
        String equipmentName = facility.getName();
        LocalDateTime startDateTime = reservation.getStartDateTime();
        String startDateTimeStr = DateUtils.convertDateTimeToString(startDateTime);

        LocalDateTime endDateTime = reservation.getEndDateTime();
        String endDateTimeStr = DateUtils.convertDateTimeToString(endDateTime);
        String purpose = reservation.getPurpose();
        System.out.println("・Reservation No: " + reservationNumber
                + "\n"
                + "・Facility: " + equipmentName
                + "\n"
                + "・Reserved Time: " + startDateTimeStr + " ー " + endDateTimeStr
                + "\n"
                + "・Purpose: " + purpose);
        System.out.println();
        UIUtility.getInputAsString("Press Enter");
    }

    private void showEquipmentList() {
        try {
            List<Facility> facilities = equipReservationSystem.getAllEquipments();
            for (Facility facility : facilities) {
                int equipmentNumber = facility.getNumber();
                String equipmentName = facility.getName();
                int capacity = facility.getCapacity();
                String extensionNumber = facility.getExtensionNumber();
                System.out.println("[" + equipmentNumber + "]" + equipmentName + " || "
                        + "Capacity: " + capacity + ", " + " Extension Number: " + extensionNumber);
                System.out.println();
            }
            System.out.println();
        } catch (DataFormatException e) {
            UIUtility.printMessage(e.getMessage());
        } catch (DataIOException e) {
            UIUtility.printMessage(e.getMessage());
        }
    }
}

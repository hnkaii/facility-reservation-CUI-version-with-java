package main.ui;

import exception.DataFormatException;
import exception.DataIOException;
import exception.DataValueException;
import main.business.FacilityReservationSystem;
import main.business.Facility;
import main.business.Reservation;
import main.business.UserAccount;
import utility.DateUtils;
import utility.UIUtility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;


public class UserReservationInquiryUI {

    private FacilityReservationSystem equipReservationSystem;

    public UserReservationInquiryUI(FacilityReservationSystem system) {
        equipReservationSystem = system;
    }

    public void findReservation(UserAccount loginUser) {
        String realName = loginUser.getRealName();
        List<Reservation> reservationList = null;
        int inputEquipmentNumber = 0;
        String inputDate = null;
        while (true) {
            try {
                showEquipmentList(realName);

                try {
                    inputEquipmentNumber = UIUtility.getInputAsInt("Facility Number：");

                    if (inputEquipmentNumber == -1) {
                        return;
                    }

                } catch (NumberFormatException e) {
                    UIUtility.printMessage("Facility Number should be numeric");
                    continue;
                }
                inputDate = UIUtility.getInputAsString("Date Format（Eg:－2002/10/31）：");
                System.out.println();
                LocalDate formattedInputDate = null;

                try {
                    formattedInputDate = DateUtils.convertStringToDate(inputDate);

                } catch (DateTimeParseException e) {
                    UIUtility.printMessage("Enter the date in YYYY / MM / DD");
                    continue;
                }
                reservationList = equipReservationSystem.findReservation(
                        inputEquipmentNumber, formattedInputDate);
                break;

            } catch (DataValueException e) {
                UIUtility.printMessage(e.getMessage());
            } catch (DataFormatException e) {
                UIUtility.printMessage(e.getMessage());
            } catch (DataIOException e) {
                UIUtility.printMessage(e.getMessage());
            }
            System.out.println();
        }
        showReservationList(
                realName, reservationList, inputEquipmentNumber, inputDate);

    }

    private void showEquipmentList(String realName) {
        UIUtility.showHeader("RESERVATION INQUIRY", realName, true);
        UIUtility.printMessage("Please enter the facility number and date\r\n"
                + "Press -1 to quit");

        try {
            List<Facility> facilities = equipReservationSystem.getAllEquipments();
            for (Facility facility : facilities) {
                int equipmentNumber = facility.getNumber();
                String equipmentName = facility.getName();
                int capacity = facility.getCapacity();
                String extensionNumber = facility.getExtensionNumber();
                System.out.println("[" + equipmentNumber + "]" + equipmentName + " || "
                        + "Capacity: " + capacity + ", "+ " Extension Number:" + extensionNumber);
            }
            System.out.println();

        } catch (DataFormatException e) {
            UIUtility.printMessage(e.getMessage());

        } catch (DataIOException e) {
            UIUtility.printMessage(e.getMessage());
        }
    }

    private void showReservationList(String realName, List<Reservation> reserves,
            int equipmentNumber, String dateOfUse) {
        UIUtility.showHeader("RESERVATION LIST", realName, true);
        System.out.println();
        System.out.println("Facility Number: " + equipmentNumber);
        System.out.println("Date: " + dateOfUse);
        System.out.println();
        for (Reservation foundReservation : reserves) {
            int reservationNumber = foundReservation.getNumber();

            LocalDateTime startDateTime = foundReservation.getStartDateTime();
            String startDateTimeStr = DateUtils.convertDateTimeToString(startDateTime);

            LocalDateTime endDateTime = foundReservation.getEndDateTime();
            String endDateTimeStr = DateUtils.convertDateTimeToString(endDateTime);

            String purpose = foundReservation.getPurpose();

            Facility facility = foundReservation.getEquipment();

            String equipmentName = facility.getName();

            UserAccount subscriber = foundReservation.getUserAccount();
            String realNameOfSubscriber = subscriber.getRealName();
            String divisionName = subscriber.getDivisionName();
            String extensionNumber = subscriber.getExtensionNumber();

            System.out.println("・Reservation No: " + reservationNumber
                    + "\n"
                    + "・Facility Name: " + equipmentName
                    + "\n"
                    + "・Reserved time: " + startDateTimeStr + " ー " + endDateTimeStr
                    + "\n"
                    + "・Reserved by: " + realNameOfSubscriber
                    + "\n"
                    + "・Department: " + divisionName
                    + "\n"
                    + "・Extension Number: " + extensionNumber
                    + "\n"
                    + "・Used for: " + purpose);
            System.out.println("--------------------------------------------------------");
        }
        System.out.println();
        UIUtility.getInputAsString("Press Enter");
    }
}

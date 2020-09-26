package main.ui;

import exception.DataFormatException;
import exception.DataIOException;
import exception.DataValueException;
import main.business.FacilityReservationSystem;
import main.business.Facility;
import main.business.UserAccount;
import utility.AppValidator;
import utility.UIUtility;

import java.io.IOException;


public class AdminFacilityUI {
    private FacilityReservationSystem equipReservationSystem;

    public AdminFacilityUI(FacilityReservationSystem system) {
        equipReservationSystem = system;
    }

    public void addEquipment(UserAccount adminUser)
            throws DataIOException, DataValueException, DataFormatException, IOException {
        String inputEquipmentName;
        int inputCapacity = 0;
        String inputExtensionNumber = null;

        while (true) {
            UIUtility.showHeader("FACILITY REGISTRATION", "", false);
            UIUtility.printMessage("Please enter the facility name, number of people, and extension number."
                    +
                    "\n"
                    +
                    "Press -1 to quit");
            inputEquipmentName = UIUtility.getInputAsString("Facility Name: ");
            if (inputEquipmentName.equals("-1")) {
                break;
            }

            if (!AppValidator.validateRequiredItem(inputEquipmentName, "Facility Name: ")) {
                continue;
            }
            try {
                inputCapacity = UIUtility.getInputAsInt("Capacity: ");
                if (!AppValidator.validateCapacity(inputCapacity)) {

                }
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Please enter the number of people as a numerical value");
                System.out.println();
                continue;

            }
            inputExtensionNumber = UIUtility.getInputAsString("Extension Number (Optional): ");
            if (!AppValidator.validateExtensionNumber(inputExtensionNumber)) {
                continue;
            }

            try {
                Facility newFacility = equipReservationSystem.addEquipment(inputEquipmentName,
                                                inputCapacity, inputExtensionNumber);

                showEquipmentInfo(adminUser, newFacility);
                break;
            } catch (DataValueException e) {
                UIUtility.printMessage(e.getMessage());
            }
        }

    }

    private void showEquipmentInfo(UserAccount adminUser, Facility newFacility) {
        UIUtility.showHeader("FACILITY REGISTRATION COMPLETED", "", false);
        UIUtility.printMessage("The following facilities have been registered");
        int equipmentNumber = newFacility.getNumber();
        String equipmentName = newFacility.getName();
        int capacity = newFacility.getCapacity();
        String extensionNumber = newFacility.getExtensionNumber();
        System.out.println("・Facility Number: " + equipmentNumber
                + "\n"
                + "・Facility Name: " + equipmentName
                + "\n"
                + "・Capacity: " + capacity
                + "\n"
                + "・Extension Number: " + extensionNumber);
        System.out.println();
        UIUtility.getInputAsString("Press Enter");

    }
}

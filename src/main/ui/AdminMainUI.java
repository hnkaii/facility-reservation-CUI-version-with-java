package main.ui;

import exception.DataFormatException;
import exception.DataIOException;
import exception.DataValueException;
import main.business.FacilityReservationSystem;
import main.business.UserAccount;
import utility.UIUtility;

import java.io.IOException;


public class AdminMainUI {

    private FacilityReservationSystem equipReservationSystem;

    public AdminMainUI(FacilityReservationSystem system) {
        equipReservationSystem = system;
    }

    public void show(UserAccount adminUser) throws DataFormatException, IOException, DataIOException, DataValueException {
        while (true) {

            int selectedNumber = -1;
            printMenu(adminUser);
            try {
                selectedNumber = UIUtility.getInputAsInt("Select number: ");
            } catch (NumberFormatException e) {
                UIUtility.printMessage("Select number should be numeric");
                continue;
            }
            switch (selectedNumber) {
            case 1:
                AdminFacilityUI equipmentScreen
                                    = new AdminFacilityUI(equipReservationSystem);
                equipmentScreen.addEquipment(adminUser);
                break;

            case 2:
                AdminAccountUI accountScreen
                                = new AdminAccountUI(equipReservationSystem);
                accountScreen.show(adminUser);
                break;

            case 3:
                System.exit(0);
                break;
            default:
                break;
            }
        }
    }


    private void printMenu(UserAccount adminUser) {
        UIUtility.showHeader("ADMIN MENU", "", false);
        UIUtility.printMessage("Press number to be directed to the specific menu");
        System.out.println("[1]Facility Registration");
        System.out.println("[2]User Registration");
        System.out.println("[3]Quit");
        System.out.println();

    }
}

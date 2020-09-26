package main.ui;

import main.business.FacilityReservationSystem;
import main.business.UserAccount;
import utility.UIUtility;

public class UserMainUI {
    private FacilityReservationSystem equipReservationSystem;

    public UserMainUI(FacilityReservationSystem system) {
        equipReservationSystem = system;
    }

    public void show(UserAccount loginUser) {

        while (true) {
            int selectedNumber = -1;
            try {
                printMenu(loginUser);
                selectedNumber = UIUtility.getInputAsInt("Select number：");
            } catch (NumberFormatException e) {
                UIUtility.printMessage("Select number should be numeric");
                continue;
            }
            switch (selectedNumber) {
            case 1:
                UserReservationInquiryUI enquiryScreen = new UserReservationInquiryUI(equipReservationSystem);
                enquiryScreen.findReservation(loginUser);
                break;

            case 2:
                UserMakingReservationUI reservationScreen = new UserMakingReservationUI(equipReservationSystem);
                reservationScreen.reserveEquipment(loginUser);
                break;

            case 3:
                System.exit(0);
                break;
            default:
                break;
            }
        }
    }

    private void printMenu(UserAccount loginUser) {
        String loginUserName = loginUser.getRealName();
        UIUtility.showHeader("USER MENU", loginUserName, true);
        UIUtility.printMessage("Press number to be directed to the specific menu");
        System.out.println("[1]Reservation Inquiry");
        System.out.println("[2]Make Reservation");
        System.out.println("[3]Quit");
        System.out.println();
    }

}

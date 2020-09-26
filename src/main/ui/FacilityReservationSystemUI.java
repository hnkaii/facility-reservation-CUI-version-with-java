package main.ui;

import exception.DataFormatException;
import exception.DataIOException;
import exception.DataValueException;
import main.business.FacilityReservationSystem;
import main.business.UserAccount;
import utility.AppValidator;
import utility.UIUtility;

import java.io.IOException;

public class FacilityReservationSystemUI {

    private static FacilityReservationSystem equipReservationSystem;

    public FacilityReservationSystemUI() {
        super();
    }

    public static void main(String[] args) throws DataIOException, DataValueException, DataFormatException, IOException {
        equipReservationSystem = new FacilityReservationSystem();
        UserAccount loginUser = login();
        if (loginUser == null) {
            System.exit(0);
        } else if (AppValidator.isAdmin(loginUser)) {
            AdminMainUI adminUI = new AdminMainUI(equipReservationSystem);
            adminUI.show(loginUser);
        } else if (AppValidator.isReserveUser(loginUser)) {
            UserMainUI userUI = new UserMainUI(equipReservationSystem);
            userUI.show(loginUser);
        } else {
            UIUtility.printMessage("There is an error in the user authority of the User Management File.");
        }
    }

    private static UserAccount login() {
        while (true) {
            try {
                UIUtility.showHeader("AUTHORIZATION", "", false);
                UIUtility.printMessage("Input Login Name and Password\r\n"
                                     + "Press -1 to quit");
                String loginName = UIUtility.getInputAsString("Login Name: ");
                if (loginName != null && loginName.matches("-1")) {
                    return null;
                }
                String password = UIUtility.getInputAsString("Password: ");
                if (!AppValidator.validateLoginName(loginName)) {
                    continue;
                }
                if (!AppValidator.validatePassword(password)) {
                    continue;
                }
                UserAccount loginUser = equipReservationSystem.findUserAccount(loginName, password);
                return loginUser;
            } catch (DataValueException e) {
                UIUtility.printMessage(e.getMessage());
            } catch (DataFormatException e) {
                UIUtility.printMessage(e.getMessage());
            } catch (DataIOException e) {
                UIUtility.printMessage(e.getMessage());
            }
        }
    }

}

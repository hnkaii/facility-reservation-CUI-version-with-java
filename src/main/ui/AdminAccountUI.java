package main.ui;

import exception.DataFormatException;
import exception.DataIOException;
import exception.DataValueException;
import main.business.FacilityReservationSystem;
import main.business.UserAccount;
import utility.AppValidator;
import utility.UIUtility;

import java.io.IOException;


public class AdminAccountUI {

    private FacilityReservationSystem equipReservationSystem;

    public AdminAccountUI(FacilityReservationSystem system) {
        equipReservationSystem = system;
    }

    public void show(UserAccount adminUser) throws DataFormatException, IOException {
        String loginName;
        String password;
        String realName;
        String extensionNumber;
        String divisionName;
        int authority;
        boolean isValid;
        String adminRealName = adminUser.getRealName();

        while (true) {
            try {
                UIUtility.showHeader("USER REGISTRATION", adminRealName, false);
                UIUtility.printMessage("Please enter the login name, password, user's real name, extension number, department name, and authority"
                        + "\n" + "Press -1 to quit");
                loginName = UIUtility.getInputAsString("Login Name: ");
                if (loginName.equals("-1")) {
                    break;
                }
                isValid = AppValidator.validateLoginName(loginName);
                if (!isValid) {
                    continue;
                }
                password = UIUtility.getInputAsString("Password: ");
                isValid = AppValidator.validatePassword(password);
                if (!isValid) {
                    continue;
                }
                realName = UIUtility.getInputAsString("User Name: ");
                isValid = AppValidator.validateRequiredItem(realName, "User Name");
                if (!isValid) {
                    continue;
                }
                extensionNumber = UIUtility.getInputAsString("Extension Number (Optional): ");
                isValid = AppValidator.validateExtensionNumber(extensionNumber);
                if (!isValid) {
                    continue;
                }
                divisionName = UIUtility.getInputAsString("Department (Optional): ");
                authority = UIUtility.getInputAsInt("Authority (0: Administrator, 1: User):");

                isValid = AppValidator.validateAuthority(authority);

                if (!isValid) {
                    continue;
                }
                System.out.println();
                try {
                    UserAccount newUserAccount = equipReservationSystem.addUserAccount(loginName, password, realName,
                            extensionNumber, divisionName, authority);
                    showUserAccountInfo(adminUser, newUserAccount);

                    break;
                } catch (DataValueException e) {
                    UIUtility.printMessage(e.getMessage());
                } catch (DataFormatException e) {
                    UIUtility.printMessage(e.getMessage());
                } catch (DataIOException e) {
                    UIUtility.printMessage(e.getMessage());
                }
            } catch (NumberFormatException e) {
                UIUtility.printMessage("Enter the authority as 0 or 1");
                continue;
            }
        }

    }

    private void showUserAccountInfo(UserAccount adminUser,
            UserAccount newUserAccount) {
        UIUtility.showHeader("USER REGISTRATION COMPLETED", "", false);
        UIUtility.printMessage("The following user has been registered");
        int accountNumber = newUserAccount.getNumber();
        System.out.println("・User Number: " + accountNumber);
        String loginName = newUserAccount.getLoginName();
        System.out.println("・Login Name: " + loginName);
        String password = newUserAccount.getPassword();
        System.out.println("・Password: " + password);
        String realName = newUserAccount.getRealName();
        System.out.println("・User Name: " + realName);
        String extensionNumber = newUserAccount.getExtensionNumber();
        System.out.println("・Extension Number: " + extensionNumber);
        String divisionName = newUserAccount.getDivisionName();
        System.out.println("・Department: " + divisionName);
        int authority = newUserAccount.getAuthority();
        System.out.println("・Authority (0: Administrator, 1: User):" + authority);
        System.out.println();
        UIUtility.getInputAsString("Press Enter");
    }

}

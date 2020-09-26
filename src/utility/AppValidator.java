package utility;

import main.business.UserAccount;

import java.time.LocalDateTime;


public class AppValidator {

    private AppValidator() {
        super();
    }

    public static boolean isAdmin(UserAccount userAccount) {
        if (userAccount == null) {
            return false;
        }
        return userAccount.getAuthority() == UserAccount.ADMINISTRATOR;
    }

    public static boolean isReserveUser(UserAccount userAccount) {
        if (userAccount == null) {
            return false;
        }
        return userAccount.getAuthority() == UserAccount.RESERVER;
    }

    public static boolean validateRequiredItem(String targetStr, String itemName) {
        if (!StringUtils.isNotEmpty(targetStr)) {
            UIUtility.printMessage(itemName + "is required");
            return false;
        }
        return true;
    }

    public static boolean validateLoginName(String loginName) {
        if (!validateRequiredItem(loginName, "Login Name")) {
            return false;
        }
        return withinRange(loginName, "Login Name", 5, 10);
    }

    public static boolean validatePassword(String password) {
        if (!validateRequiredItem(password, "Password")) {
            return false;
        }
        return withinRange(password, "Password", 5, 10);
    }

    private static boolean withinRange(String targetStr, String itemName,int min, int max) {
        if (!StringUtils.checkRangeOfLength(min, max, targetStr)) {
            UIUtility.printMessage(itemName + " should be within " + min + " and " + max + " characters");
            return false;
        }
        return true;
    }

    public static boolean validateExtensionNumber(String extensionNumber) {
        if (StringUtils.checkLength(0, extensionNumber)) {
            return true;
        }
        if (!StringUtils.checkLength(4, extensionNumber)) {
            UIUtility.printMessage("Enter the extension number as a 4-digit number");
            return false;
        }
        if (!StringUtils.isNumeric(extensionNumber)) {
            UIUtility.printMessage("Enter the extension number as a 4-digit number");
            return false;
        }

        return true;
    }

    public static boolean isFutureDateTime(LocalDateTime dateTime) {
        if (!dateTime.isAfter(LocalDateTime.now())) {
            UIUtility.printMessage("Enter a date in the future");
            return false;
        }
        return true;
    }

    public static boolean isValidTerm(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (!startDateTime.isBefore(endDateTime)) {
            UIUtility.printMessage("Please enter the correct Start date&time and End date&time");
            return false;
        }
        return true;
    }

    public static boolean validateAuthority(int authority) {
        if (!isAuthority(authority)) {
            UIUtility.printMessage("Enter authority as 0 or 1");
            return false;
        }
        return true;
    }

    private static boolean isAuthority(int authority) {
        return authority == UserAccount.ADMINISTRATOR || authority == UserAccount.RESERVER;
    }

    public static boolean validateCapacity(int capacity) {
        if (capacity <= 0) {
            UIUtility.printMessage("Please enter at least one person");
            return false;
        }
        return true;
    }
}

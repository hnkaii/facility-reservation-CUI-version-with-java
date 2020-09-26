package main.business;

import exception.DataFormatException;
import exception.DataIOException;
import main.dataaccess.UserAccountDataAccessor;

import java.io.IOException;


public class UserAccountMaster {

    private UserAccountDataAccessor userAccountDataAccessor;

    public UserAccountMaster() {
        userAccountDataAccessor = new UserAccountDataAccessor();
    }

    public UserAccountMaster(UserAccountDataAccessor dataAccessor) {
        userAccountDataAccessor = dataAccessor;
    }

    private UserAccount convertToUserAccount(String[] userAccountData)
            throws DataFormatException {
        try {
            int number = Integer.parseInt(userAccountData[0]);
            String loginName = userAccountData[1];
            String password = userAccountData[2];
            String realName = userAccountData[3];
            String extensionNumber = userAccountData[4];
            String divisionName = userAccountData[5];
            int authority = Integer.parseInt(userAccountData[6]);
            UserAccount account = new UserAccount(number, loginName, password, realName,
                    extensionNumber, divisionName, authority);
            return account;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException(
                    "User Account Management file configuration is invalid", e);

        } catch (NumberFormatException e) {
            throw new DataFormatException(
                    "Failed to convert the data (user account number or authority) " +
                            "in the user account management file to a numerical value", e);
        }
    }

    public UserAccount findUserAccount(String loginName, String password)
            throws DataIOException, DataFormatException {
        String[] foundData = userAccountDataAccessor.findUserAccount(loginName, password);

        if (foundData != null) {
            UserAccount foundAccount = convertToUserAccount(foundData);
            return foundAccount;
        } else {
            return null;

        }
    }

    public UserAccount findUserAccount(int targetUserAccountNumber)
            throws DataIOException, DataFormatException {
        String[] foundData = userAccountDataAccessor.findUserAccount(targetUserAccountNumber);

        if (foundData != null) {
            UserAccount foundAccount = convertToUserAccount(foundData);
            return foundAccount;

        } else {
            return null;

        }
    }

    public UserAccount findUserAccount(String loginName)
            throws DataIOException, DataFormatException {
        String[] foundAccountData = userAccountDataAccessor.findUserAccount(loginName);

        if (foundAccountData != null) {
            UserAccount foundAccount = convertToUserAccount(foundAccountData);
            return foundAccount;
        } else {
            return null;
        }

    }

    public UserAccount addUserAccount(String loginName, String password,
            String realName, String extensionNumber, String divisionName,
            int authority) throws DataIOException, DataFormatException, IOException {

        int newUserAccountNumber = userAccountDataAccessor.getNextSeq();

        String[] newUserAccountData = new String[7];
        newUserAccountData[0] = String.valueOf(newUserAccountNumber);
        newUserAccountData[1] = String.valueOf(loginName);
        newUserAccountData[2] = String.valueOf(password);
        newUserAccountData[3] = String.valueOf(realName);
        newUserAccountData[4] = String.valueOf(extensionNumber);
        newUserAccountData[5] = String.valueOf(divisionName);
        newUserAccountData[6] = String.valueOf(authority);

        userAccountDataAccessor.addUserAccount(newUserAccountData);
        UserAccount newUserAccount = findUserAccount(newUserAccountNumber);
        return newUserAccount;
    }

}

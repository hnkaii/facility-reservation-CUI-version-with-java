package main.dataaccess;

import exception.DataFormatException;
import exception.DataIOException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class UserAccountDataAccessor {

    private static final String DEFAULT_FILE_PATH = "csv/userAccount.csv";

    private BaseDataAccessor baseAccessor;

    private List<String[]> accountDataList;

    public UserAccountDataAccessor() {
        this(DEFAULT_FILE_PATH);
    }

    public UserAccountDataAccessor(String filePath) {
        baseAccessor = new BaseDataAccessor(filePath);
    }

    private List<String[]> load() throws DataIOException {
        try {
            List<String[]> accountDataList = new ArrayList<String[]>();
            baseAccessor.load(accountDataList);
            return accountDataList;
        } catch (IOException e) {
            throw new DataIOException(
                        "User Account Management File cannot be read", e);
        }
    }


    public String[] findUserAccount(String targetLoginName,
            String targetPassword) throws DataIOException, DataFormatException {

        try {
            List<String[]> accountDataList = load();
            for (String[] accountData : accountDataList) {
                String loginName = accountData[1];
                String password = accountData[2];
                if (targetLoginName.equals(loginName)
                        && targetPassword.equals(password)) {
                    return accountData;
                }
            }
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException(
                        "The location of the data (login name or password) in the user account management file is incorrect", e);
        }
    }


    public String[] findUserAccount(int targetUserNumber)
            throws DataIOException,DataFormatException {

        try {
            accountDataList = load();
            for (String[] userAccountData : accountDataList) {
                int userNumber = Integer.parseInt(userAccountData[0]);
                if (userNumber == targetUserNumber) {
                    return userAccountData;

                }

            }
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DataFormatException(
                        "The data in the user account management file (the location of the user account number) is invalid", e);
        } catch (NumberFormatException e) {
            throw new DataFormatException(
                        "Failed to convert the data (user account number) in the user account management file to a numerical value", e);
        }
    }


    public String[] findUserAccount(String targetLoginName)
            throws DataIOException, DataFormatException {
        accountDataList = load();
        for (String[] accountData : accountDataList) {
            if (targetLoginName.equals(accountData[1])) {
                return accountData;
            }
        }
        return null;
    }


    private void save(String[] userAccountData) throws DataIOException, IOException {
        baseAccessor.save(userAccountData);
    }


    public void addUserAccount(String[] userAccountData) throws DataIOException, IOException {
        save(userAccountData);
    }


    public int getNextSeq() throws DataIOException {
        List<String[]> userAccountDataList = load();
        int numberOfUserAccount = userAccountDataList.size();
        return numberOfUserAccount + 1;
    }
}

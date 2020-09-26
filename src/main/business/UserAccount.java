package main.business;

public class UserAccount {
    public static final int RESERVER = 1;

    public static final int ADMINISTRATOR = 0;
    private int number;

    private String loginName;

    private String password;

    private String realName;

    private String extensionNumber;

    private String divisionName;

    private int authority;

    public UserAccount() {
        this(0, "", "", "", "", "", RESERVER);
    }

    public UserAccount(int number, String loginName, String password, String realName,
            String extensionNumber, String divisionName, int authority) {

        setNumber(number);
        setLoginName(loginName);
        setPassword(password);
        setRealName(realName);
        setExtensionNumber(extensionNumber);
        setDivisionName(divisionName);
        setAuthority(authority);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRealName() {
        return this.realName;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public String getExtensionNumber() {
        return this.extensionNumber;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public String getDivisionName() {
        return this.divisionName;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public int getAuthority() {
        return this.authority;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + authority;
        result = prime * result
                + ((divisionName == null) ? 0 : divisionName.hashCode());
        result = prime * result
                + ((extensionNumber == null) ? 0 : extensionNumber.hashCode());
        result = prime * result
                + ((loginName == null) ? 0 : loginName.hashCode());
        result = prime * result + number;
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        result = prime * result
                + ((realName == null) ? 0 : realName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) obj;
        if (authority != other.authority) {
            return false;
        }
        if (divisionName == null) {
            if (other.divisionName != null) {
                return false;
            }
        } else if (!divisionName.equals(other.divisionName)) {
            return false;
        }
        if (extensionNumber == null) {
            if (other.extensionNumber != null) {
                return false;
            }
        } else if (!extensionNumber.equals(other.extensionNumber)) {
            return false;
        }
        if (loginName == null) {
            if (other.loginName != null) {
                return false;
            }
        } else if (!loginName.equals(other.loginName)) {
            return false;
        }
        if (number != other.number) {
            return false;
        }
        if (password == null) {
            if (other.password != null) {
                return false;
            }
        } else if (!password.equals(other.password)) {
            return false;
        }
        if (realName == null) {
            if (other.realName != null) {
                return false;
            }
        } else if (!realName.equals(other.realName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("UserAccount [authority=").append(authority).append(
                ", divisionName=").append(divisionName).append(
                ", extensionNumber=").append(extensionNumber).append(
                ", loginName=").append(loginName).append(", number=").append(
                number).append(", password=").append(password).append(
                ", realName=").append(realName).append("]");
        return builder.toString();
    }
}

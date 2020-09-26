package main.business;

import java.time.LocalDateTime;

public class Reservation {

    private int number;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String purpose;

    private int equipmentNumber;

    private int userAccountNumber;

    private UserAccount reserveUser;

    private Facility facility;

    public Reservation() {
        super();
    }

    public Reservation(int number, LocalDateTime startDateTime, LocalDateTime endDateTime,
            String purpose, int equipmentNumber, int userAccountNumber) {

        setNumber(number);
        setStartDateTime(startDateTime);
        setEndDateTime(endDateTime);
        setPurpose(purpose);
        setEquipmentNumber(equipmentNumber);
        setUserAccountNumber(userAccountNumber);

    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getStartDateTime() {
        return this.startDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setEquipmentNumber(int equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public int getEquipmentNumber() {
        return this.equipmentNumber;
    }

    public void setUserAccountNumber(int userAccountNumber) {
        this.userAccountNumber = userAccountNumber;
    }

    public int getUserAccountNumber() {
        return this.userAccountNumber;
    }

    public void setEquipment(Facility facility) {
        this.facility = facility;
    }

    public Facility getEquipment() {
        return this.facility;
    }

    public void setUserAccount(UserAccount reserveUser) {
        this.reserveUser = reserveUser;
    }

    public UserAccount getUserAccount() {
        return this.reserveUser;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((endDateTime == null) ? 0 : endDateTime.hashCode());
        result = prime * result
                + ((facility == null) ? 0 : facility.hashCode());
        result = prime * result + equipmentNumber;
        result = prime * result + number;
        result = prime * result + ((purpose == null) ? 0 : purpose.hashCode());
        result = prime * result
                + ((reserveUser == null) ? 0 : reserveUser.hashCode());
        result = prime * result
                + ((startDateTime == null) ? 0 : startDateTime.hashCode());
        result = prime * result + userAccountNumber;
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
        if (!(obj instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) obj;
        if (endDateTime == null) {
            if (other.endDateTime != null) {
                return false;
            }
        } else if (!endDateTime.equals(other.endDateTime)) {
            return false;
        }
        if (facility == null) {
            if (other.facility != null) {
                return false;
            }
        } else if (!facility.equals(other.facility)) {
            return false;
        }
        if (equipmentNumber != other.equipmentNumber) {
            return false;
        }
        if (number != other.number) {
            return false;
        }
        if (purpose == null) {
            if (other.purpose != null) {
                return false;
            }
        } else if (!purpose.equals(other.purpose)) {
            return false;
        }
        if (reserveUser == null) {
            if (other.reserveUser != null) {
                return false;
            }
        } else if (!reserveUser.equals(other.reserveUser)) {
            return false;
        }
        if (startDateTime == null) {
            if (other.startDateTime != null) {
                return false;
            }
        } else if (!startDateTime.equals(other.startDateTime)) {
            return false;
        }
        if (userAccountNumber != other.userAccountNumber) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Reservation [endDateTime=").append(endDateTime).append(
                ", facility=").append(facility).append(", equipmentNumber=")
                .append(equipmentNumber).append(", number=").append(number)
                .append(", purpose=").append(purpose).append(", reserveUser=")
                .append(reserveUser).append(", startDateTime=").append(startDateTime)
                .append(", userAccountNumber=").append(userAccountNumber)
                .append("]");
        return builder.toString();
    }


}

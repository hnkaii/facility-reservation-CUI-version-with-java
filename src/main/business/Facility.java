package main.business;

public class Facility {

    private int number;

    private String name;

    private int capacity;

    private String extensionNumber;

    public Facility() {
    }

    public Facility(int number, String name, int capacity, String extensionNumber) {
        setNumber(number);
        setName(name);
        setCapacity(capacity);
        setExtensionNumber(extensionNumber);
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return this.number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setExtensionNumber(String extensionNumber) {
        this.extensionNumber = extensionNumber;
    }

    public String getExtensionNumber() {
        return this.extensionNumber;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + capacity;
        result = prime * result
                + ((extensionNumber == null) ? 0 : extensionNumber.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + number;
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
        if (!(obj instanceof Facility)) {
            return false;
        }
        Facility other = (Facility) obj;
        if (capacity != other.capacity) {
            return false;
        }
        if (extensionNumber == null) {
            if (other.extensionNumber != null) {
                return false;
            }
        } else if (!extensionNumber.equals(other.extensionNumber)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (number != other.number) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Facility [capacity=").append(capacity).append(
                ", extensionNumber=").append(extensionNumber).append(", name=")
                .append(name).append(", number=").append(number).append("]");
        return builder.toString();
    }


}

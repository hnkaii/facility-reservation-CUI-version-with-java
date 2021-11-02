# Facility-Reservation-CUI-Version

This is a program I've created after 3 week Java training. A program run on console, purely written in Java.
This program allow user to reserve facility (facility means such as meeting room, or public space etc...)

Program flow: Login → Menu (displays feature)　→ Perform → Quit.

Login Authority: as ADMIN and as USER
  * ADMIN: is able to register new facilities and new users
  * USER: is able to reserve facilities and check the status of reservation.
  (Facilities in this program are spaces/meeting rooms etc)
  
 Data is stored in CSV files.

== PROGRAM STRUCTURE == 
```
Facility-Resevation-CUI-Version
 └──csv/
 |    ├── facility.csv
 |    ├── reservation.csv
 |    └── userAccount.csv
 |      
 └── scr/
      ├── main/
      |   └── dataaccess/
      |   |       ├── BaseDataAccessor.java
      |   |       ├── UserAccountDataAccessor.java
      |   |       ├── FacilityDataAccessor.java 
      |   |       └── ReservationDataAccessor.java 
      |   |
      |   └──business/
      |   |       ├── FacilityReservationSystem.java      
      |   |       ├── UserAccount.java
      |   |       ├── UserAccountMaster.java
      |   |       ├── Facility.java
      |   |       ├── FacilityMaster.java
      |   |       ├── Reservation.java
      |   |       └── ReservationMaster.java      
      |   |
      |   └──ui/
      |   |       ├── FacilityReservationSystemUI.java      
      |   |       ├── UserMainUI.java
      |   |       ├── UserMakingReservationUI.java
      |   |       ├── UserReservationInquiryUI.java
      |   |       ├── AdminMainUI.java
      |   |       ├── AdminFacilityUI.java
      |   |       └── AdminAccountUI.java   
      |   
      ├── io/
      |   ├── CSVFileReader.java
      |   ├── CSVFileWriter.java
      |   └── StandardInputReader.java
      |
      ├── utility/
      |   ├── AppValidator.java
      |   ├── DataUtils.java
      |   ├── StringUtils.java      
      |   └── UIUtility.java
      |
      └── exception/
          ├── DataFormatException.java
          ├── DataIOException.java
          └── DataValueExceptiom.java
```
 
## How it looks
### Login as Admin

- Register new facilities
- Register users.


![ezgif com-gif-maker](https://user-images.githubusercontent.com/45198696/139791584-fba444f4-7c50-4ab9-87db-46138e23a5f5.gif)


### Login as User

- Make inquiry on the schedule of any facility

![ezgif com-gif-maker (1)](https://user-images.githubusercontent.com/45198696/139792476-9d748c23-9e91-4bae-be1a-8c3b0c78a506.gif)


- Make a reservation

![ezgif com-gif-maker (2)](https://user-images.githubusercontent.com/45198696/139792512-6759f922-8f77-43c1-ae62-c32fc09d321d.gif)

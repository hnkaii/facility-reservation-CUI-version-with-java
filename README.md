# Facility-Reservation-CUI-Version

A program run on console, written in Java.

Program flow: Login → Menu (displays feature)　→ Perform → Quit.

Login Authority: as ADMIN and as USER
  * ADMIN: is able to register new facilities and new users
  * USER: is able to reserve facilities and check the status of reservation.
  (Facilities in this program are spaces/meeting rooms etc)
  
 Data is stored in CSV files.

== PROGRAM STRUCTURE == 

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

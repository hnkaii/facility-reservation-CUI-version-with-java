# Facility-Reservation-CUI-Version

A program run on console, purely written in Java.
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

1. Register new facilities

<img width="1001" alt="Screen Shot 2021-10-22 at 23 44 31" src="https://user-images.githubusercontent.com/45198696/138576395-1bc4fad8-8f91-41e5-97b6-169a223f2f7d.png">


<img width="872" alt="Screen Shot 2021-10-22 at 23 44 46" src="https://user-images.githubusercontent.com/45198696/138576414-ba94662f-3030-44e6-a0c1-c84f99105acb.png">



<img width="1115" alt="Screen Shot 2021-10-22 at 23 45 59" src="https://user-images.githubusercontent.com/45198696/138576415-c7ca6c33-a0bb-4f86-812f-e976aaba4d72.png">



2. Register new users

<img width="1054" alt="Screen Shot 2021-10-24 at 10 33 37" src="https://user-images.githubusercontent.com/45198696/138576453-8f30feca-1146-4d75-82a5-bfa82ef5d3c8.png">

<img width="1163" alt="Screen Shot 2021-10-24 at 10 33 17" src="https://user-images.githubusercontent.com/45198696/138576454-0c4ca47d-e5a6-46ce-8159-c2d5698195d2.png">


### Login as User

1. Make inquiry on the schedule of any facility
<img width="958" alt="Screen Shot 2021-10-24 at 10 37 08" src="https://user-images.githubusercontent.com/45198696/138576619-7178b755-4f2b-4906-bd5c-f2910a873d4e.png">

<img width="911" alt="Screen Shot 2021-10-24 at 10 37 28" src="https://user-images.githubusercontent.com/45198696/138576625-bb6f1641-79b6-431d-a6bb-c80af302b7df.png">

<img width="1098" alt="Screen Shot 2021-10-24 at 10 38 52" src="https://user-images.githubusercontent.com/45198696/138576629-3ca879a0-d80e-42db-a156-9d5388cb51b7.png">

2. Make a reservation

<img width="897" alt="Screen Shot 2021-10-24 at 10 39 36" src="https://user-images.githubusercontent.com/45198696/138576635-1a9e5e94-799e-47be-a71e-0ee304e65581.png">

<img width="1017" alt="Screen Shot 2021-10-24 at 10 40 47" src="https://user-images.githubusercontent.com/45198696/138576640-16c22a65-3ebb-4831-8063-6b9e7beafc51.png">

<img width="788" alt="Screen Shot 2021-10-24 at 10 41 02" src="https://user-images.githubusercontent.com/45198696/138576659-c2d78567-b983-4059-8c6a-f055f7996678.png">







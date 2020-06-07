-- Created by Simon Ibssa, Louise Ibuna
-- CSC 365, Andrew Migler

-- DDL for lab7 rooms
create table lab7_rooms(
    char(5) RoomCode
    varchar(30) RoomName,
    int(11) Beds,
    varchar(8) bedType,
    int(11) maxOcc,
    float basePrice,
    varchar(20) decor,
    PRIMARY KEY(RoomCode),
    UNIQUE(RoomName)
);

-- DDL for lab7 reservations
create table lab_7reservations(
    int(11) CODE,
    char(5) Room,
    DATE CheckIn,
    DATE CheckOut,
    float Rate,
    varchar(15) LastName,
    varchar(15) FirstName,
    int(11) Adults,
    int(11) Kids,
    PRIMARY KEY(CODE),
    FOREIGN KEY(Room) references lab7_rooms(RoomCode)
);



-- DML for lab7_rooms


-- DML for lab7_reservations
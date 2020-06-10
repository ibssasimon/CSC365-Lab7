-- Created by Simon Ibssa, Louise Ibuna
-- CSC 365, Andrew Migler

drop table if exists lab7_rooms;
drop table if exists lab7_reservations;
-- DDL for lab7 rooms
create table if not exists lab7_rooms(
    char(5) RoomCode,
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
create table if not exists lab_7reservations(
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

-- populating some test data for our program

INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('AOB', 'Abscond or bolster', 2, 'Queen', 4, 175, 'traditional');
INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('CAS', 'Convoke and sanguine', 2, 'King', 4, 175, 'traditional');
INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('FNA', 'Frugal not apropos', 2, 'King', 4, 250, 'traditional');
INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('HBB', 'Harbinger but bequest', 1, 'Queen', 2, 100, 'modern');
INSERT INTO lab7_rooms (RoomCode, RoomName, Beds, bedType, maxOcc, basePrice, decor) VALUES('IBD', 'Immutable before decorum', 2, 'Queen', 4, 150, 'rustic');


-- below doesn't work. We need to hand input INSERT statements into these tables
insert into lab7_rooms select * from INN.rooms;
INSERT INTO lab7_reservations SELECT CODE, Room,
   DATE_ADD(CheckIn, INTERVAL 9 YEAR),
   DATE_ADD(Checkout, INTERVAL 9 YEAR),
   Rate, LastName, FirstName, Adults, Kids FROM INN.reservations;


DROP DATABASE IF EXISTS HotelReservation;

CREATE DATABASE HotelReservation;

USE HotelReservation;

CREATE TABLE Customer (
	CustomerID INT NOT NULL auto_increment,
    Phone VARCHAR(15) NOT NULL,
    Email CHAR NOT NULL,
    ReservationID INT NOT NULL,
    PRIMARY KEY (CustomerID)
);

CREATE TABLE Reservation (
	ReservationID INT NOT NULL auto_increment,
    FromDate DATETIME NOT NULL,
    ToDate DATETIME NOT NULL,
    PromotionID INT NOT NULL,
    CustomerID INT NOT NULL,
    PRIMARY KEY (ReservationID)
);

ALTER TABLE Reservation
ADD CONSTRAINT fk_HotelReservation_Customer
FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID);

ALTER TABLE Customer
ADD CONSTRAINT fk_HotelReservation_ReservationCustomer
FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID);

CREATE TABLE Guests (
	GuestID INT NOT NULL auto_increment,
    GuestName CHAR NOT NULL,
    Age INT NOT NULL,
    PRIMARY KEY (GuestID)
);

CREATE TABLE Room (
	RoomID INT NOT NULL auto_increment,
    RoomType VARCHAR(30) NOT NULL,
    ReservationID INT NOT NULL,
    PRIMARY KEY (RoomID)
);

ALTER TABLE Room
ADD CONSTRAINT fk_HotelReservation_ReservationRoom
FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID);

CREATE TABLE RoomAmentities(
	RoomID INT NOT NULL,
    AmenityID INT NOT NULL
);

CREATE TABLE Amenity(
	AmenityID INT NOT NULL auto_increment,
    AmenityName VARCHAR(30) NOT NULL,
    PRIMARY KEY (AmenityID)
);

ALTER TABLE RoomAmentities
ADD CONSTRAINT fk_HotelReservation_RoomID
FOREIGN KEY (RoomID) REFERENCES Room(RoomID);

ALTER TABLE RoomAmentities
ADD CONSTRAINT fk_HotelReservation_AmenityID
FOREIGN KEY (AmenityID) REFERENCES Amenity(AmenityID);

CREATE TABLE Bill (
	BillID INT NOT NULL auto_increment,
    SubTotal DECIMAL(10,2) NOT NULL,
    Taxes DECIMAL(10,2) NOT NULL,
	Total DECIMAL(10,2) NOT NULL,
    ReservationID INT NOT NULL,
    PRIMARY KEY (BillID)
);

ALTER TABLE Bill
ADD CONSTRAINT fk_HotelReservation_ReservationID
FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID);

CREATE TABLE BillDetails(
	BillDetailsID INT NOT NULL auto_increment,
    ChargeType VARCHAR(30) NOT NULL,
    Cost DECIMAL(10,2) NOT NULL,
    BillID INT NOT NULL,
    PRIMARY KEY (BillDetailsID)
);

ALTER TABLE BillDetails
ADD CONSTRAINT fk_HotelReservation_BillIDDetails
FOREIGN KEY (BillID) REFERENCES Bill(BillID);

CREATE TABLE AddOns (
	AddOnID INT NOT NULL auto_increment,
    AddOnType VARCHAR(30) NOT NULL,
    AddOnCost DECIMAL(10,2) NOT NULL,
    ReservationID INT NOT NULL,
    PRIMARY KEY (AddOnID)
);

ALTER TABLE AddOns
ADD CONSTRAINT fk_HotelReservation_ReservationIDAddOns
FOREIGN KEY (ReservationID) REFERENCES Reservation(ReservationID);

CREATE TABLE Promotion (
	PromotionID INT NOT NULL auto_increment,
    Discount DECIMAL(10,2) NOT NULL,
    PRIMARY KEY (PromotionID)
);

ALTER TABLE Reservation
ADD CONSTRAINT fk_HotelReservation_Promotion
FOREIGN KEY (PromotionID) REFERENCES Promotion(PromotionID);

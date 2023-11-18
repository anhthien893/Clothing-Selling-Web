CREATE DATABASE PRJ_SHOPPING

USE PRJ_SHOPPING
CREATE TABLE [User](
	userID char(10) primary key,
	name nvarchar(50) not null,
	email nvarchar(100) unique,
	roleID nvarchar(3) not null,
	[password]  char(50) not null,
)

CREATE TABLE [Order](
	ordID int identity (1,1) primary key,
	userID char(10) not null,
	[date] datetime not null, 
	[total] DECIMAL(10,2) not null,
	FOREIGN KEY(userID) REFERENCES [User](userID),
)
CREATE TABLE [Product](
	proID char(10) primary key,
	proName nvarchar(50) not null,
	price DECIMAL(10,2) not null,
	quantity int not null,
)

CREATE TABLE [OrdDetail](
	ordDetailID int identity (1,1) primary key,
	ordID int not null,
	productID char(10) not null,
	price DECIMAL(10,2) not null,
	quantity int not null,
	FOREIGN KEY(ordID) REFERENCES [Order](ordID),
)


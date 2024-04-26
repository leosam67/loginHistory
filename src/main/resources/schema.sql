CREATE TABLE IF NOT EXISTS Users (
    id serial PRIMARY KEY,
    UserName varchar(50) NOT NULL,
    FIO varchar(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Logins (
    id serial PRIMARY KEY,
    Access_Date varchar(50) NOT NULL,
    User_ID serial NOT NULL,
	Application varchar(50)
);

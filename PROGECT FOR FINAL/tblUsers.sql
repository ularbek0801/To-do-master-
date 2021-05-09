USE master
GO
IF NOT EXISTS (
    SELECT name
        FROM sys.databases
        WHERE name = N'dbToDo'
)
CREATE DATABASE dbToDo
GO
USE dbToDo;
GO
CREATE TABLE users
(
    taskId INT IDENTITY(1,1) PRIMARY KEY,
    first_name [NVARCHAR](50) NOT NULL,
    last_name [NVARCHAR](50) NOT NULL,
    user_login [NVARCHAR](20) NOT NULL,
    user_pass [NVARCHAR](16) NOT NULL,
    user_gender [NVARCHAR](15) NOT NULL,
);
GO
INSERT INTO users (first_name, last_name, user_login, user_pass, user_gender)
VALUES ('admin', 'admin', 'admin', 'admin', 'male');
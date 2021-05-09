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
CREATE TABLE tasks
(
    taskId INT IDENTITY(1,1) PRIMARY KEY,
    task [NVARCHAR](50) NOT NULL,
    taskDate [DATE] NOT NULL,
);
GO
INSERT INTO tasks (task, taskDate)
VALUES ('reading a book', '20.12.2020');
DELETE FROM tasks WHERE task='reading';
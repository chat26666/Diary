CREATE DATABASE diary;

USE diary;

CREATE TABLE writer (
        writerId INT AUTO_INCREMENT PRIMARY KEY,
        name VARCHAR(30) NOT NULL,
        email VARCHAR(50) NOT NULL,
        createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE diary (
        diaryId INT AUTO_INCREMENT PRIMARY KEY,
        writerId INT NOT NULL,
        title VARCHAR(30) NOT NULL,
        password VARCHAR(100) NOT NULL,
        plan VARCHAR(200) NOT NULL,
        createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
        updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
        FOREIGN KEY (writerId) REFERENCES writer(writerId) ON DELETE CASCADE
);

DELIMITER $$
CREATE TRIGGER after_diary_update
AFTER UPDATE ON diary
FOR EACH ROW
BEGIN
    UPDATE writer
    SET updatedAt = CURRENT_TIMESTAMP
    WHERE writerId = NEW.writerId;
    END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER after_diary_insert
AFTER INSERT ON diary
FOR EACH ROW
BEGIN
    UPDATE writer
    SET createdAt = CURRENT_TIMESTAMP
    WHERE writerId = NEW.writerId;
    END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER after_diary_delete
AFTER DELETE ON diary
FOR EACH ROW
BEGIN
    UPDATE writer
    SET updatedAt = CURRENT_TIMESTAMP
    WHERE writerId = OLD.writerId;
    END$$
DELIMITER ;

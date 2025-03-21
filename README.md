### 1. 프로젝트 소개
사용자 일정 관리 API Spring 6기 과제입니다.
<br>
<br>
<br>
### 2. 개발환경
Java 17.0.14
IntelliJ IDEA 2024.3.3
Spring Boot 3.4.4
DBMS 설치 환경 CentOS Linux release 7.9.2009
MYSQL 8.0.41 for Linux on x86_64 (MySQL Community Server - GPL)
<br>
<br>
<br>
### 3. 주요기능
-사용자 생성<br>
-일정 생성<br>
-일정 전체조회(날짜,이름)<br>
-일정 단건조회<br>
-일정 변경<br>
-일정 삭제<br>
-일정 페이지네이션<br>
<br>
<br>
<br>
### API 명세서
[API명세서_일정 다이어리.xlsx](https://github.com/user-attachments/files/19383257/API._.xlsx)
![image](https://github.com/user-attachments/assets/e9420f18-b6a0-4070-beb6-80e4cddef630)
<br>
<br>
<br>
### ERD
![ERD](https://github.com/user-attachments/assets/7792bbb7-6da0-466a-8058-ba52e6479588)
CREATE DATABASE diary;

USE diary;

CREATE TABLE writer (
    	writerId INT AUTO_INCREMENT PRIMARY KEY,
    	name VARCHAR(30) NOT NULL,
   		email VARCHAR(50) NOT NULL,
    	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    	updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE diary (
	diaryId INT AUTO_INCREMENT PRIMARY KEY,
	writerId INT NOT NULL,
	name VARCHAR(30) NOT NULL,
	password VARCHAR(100) NOT NULL,
	plan VARCHAR(200) NOT NULL,
    	createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    	updatedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
	FOREIGN KEY (writerId) REFERENCES writer(writerId) ON DELETE CASCADE	
);
<br>
<br>

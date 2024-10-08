CREATE TABLE PERSON_TBL(
    ID NUMBER PRIMARY KEY ,
    FIRST_NAME NVARCHAR2(20),
    LAST_NAME NVARCHAR2(25),
    NATIONALID NVARCHAR2(10) ,
    PHONE_NUMBER NVARCHAR2(11)  ,
    ROLE NVARCHAR2(20) ,
    USERNAME NVARCHAR2(20) ,
    PASSWORD NVARCHAR2(20) ,
    STATUS NUMBER(1),
    ACCESS_LEVEL CHAR(4) DEFAULT '0000'
);
CREATE SEQUENCE PERSON_SEQ START WITH 1 INCREMENT BY 1;

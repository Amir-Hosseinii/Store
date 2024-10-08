CREATE TABLE INVOICE_TBL(
    ID NUMBER PRIMARY KEY ,
    INVOICE_TIME TIMESTAMP,
    TOTAL_AMOUNT NUMBER,
    INVOICE_TYPE NVARCHAR2(6),
    SHOPPING_TYPE NVARCHAR2(8),
    PERSON REFERENCES PERSON_TBL
);
CREATE SEQUENCE INVOICE_SEQ START WITH 1 INCREMENT BY 1;

DECLARE num number;
BEGIN 
SELECT COUNT(1) INTO num FROM USER_TABLES WHERE TABLE_NAME = UPPER('customer') ; 
IF num > 0 THEN 
EXECUTE IMMEDIATE 'DROP TABLE customer' ; 
END IF; 
END;
/

CREATE TABLE customer (
CUSTOMER_ID NUMBER(20) NULL ,
NAME VARCHAR2(100 BYTE) NOT NULL ,
ADDRESS VARCHAR2(255 BYTE) NULL ,
CREATED_DATE DATE NULL
)
LOGGING
NOCOMPRESS
NOCACHE;
/

create  sequence my_sequence
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
nocache;

create or replace trigger my_trigger
before insert on customer
for each row
when (new.CUSTOMER_ID is null)
begin
   select my_sequence.nextval
    into :new.CUSTOMER_ID
    from dual;
end;

ALTER TABLE CAR_INFO ADD PRIMARY KEY (CUSTOMER_ID);

INSERT INTO customer VALUES (1, 'mkyong1', 'address1', SYSDATE());
INSERT INTO customer VALUES (2, 'mkyong2', 'address2', SYSDATE());



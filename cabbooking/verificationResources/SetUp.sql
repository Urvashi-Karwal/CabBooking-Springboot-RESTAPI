CREATE TABLE CAB
(
	CAB_NO INT IDENTITY,
	MODEL_NAME VARCHAR(10) NOT NULL,
	DRIVER_PHONE_NO BIGINT NOT NULL,
	AVAILABILITY VARCHAR(4) NOT NULL
);

CREATE TABLE BOOKING
(
	BOOKING_ID INT IDENTITY,
	CUSTOMER_NAME VARCHAR(20) NOT NULL,
	PHONE_NO BIGINT NOT NULL,
	BOOKING_TYPE VARCHAR(20) NOT NULL,
	CAB_NO INT UNIQUE
);

INSERT INTO CAB VALUES (451678, 'Honda', 9823478234, 'No');
INSERT INTO CAB VALUES (567897, 'Mahindra', 9643345654, 'No');
INSERT INTO CAB VALUES (234987, 'Toyota', 9643675654, 'No');
INSERT INTO CAB VALUES (456783, 'Toyota', 9947835654, 'Yes');
INSERT INTO CAB VALUES (159723, 'Maruti', 8744435654, 'Yes');


INSERT INTO BOOKING VALUES (1001, 'Michel', 9867542341, 'Shared', 451678);
INSERT INTO BOOKING VALUES (1002, 'Robert', 8745316754, 'Personal', 567897);
INSERT INTO BOOKING VALUES (1003, 'Peter', 9645336766, 'Shared', 234987);
SET SERVEROUTPUT ON;

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, LogDate, ActionTaken, Details)
    VALUES (
        :NEW.TransactionID,
        SYSDATE,
        'INSERT',
        'Transaction ' || :NEW.TransactionType || ' for amount ' || :NEW.Amount || ' was inserted'
    );
END;
/

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20004, 'Deposit amount must be positive');
    ELSIF :NEW.TransactionType = 'Withdrawal' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20005, 'Withdrawal amount must be positive');
        END IF;

        SELECT Balance INTO v_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;

        IF v_balance < :NEW.Amount THEN
            RAISE_APPLICATION_ERROR(-20006, 'Withdrawal amount exceeds balance');
        END IF;
    END IF;
END;
/
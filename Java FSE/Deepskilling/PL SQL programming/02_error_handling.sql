SET SERVEROUTPUT ON;

CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_source_account_id IN NUMBER,
    p_target_account_id IN NUMBER,
    p_amount IN NUMBER
) AS
    v_source_balance NUMBER;
    v_target_balance NUMBER;
BEGIN
    SELECT Balance INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_source_account_id
    FOR UPDATE;

    SELECT Balance INTO v_target_balance
    FROM Accounts
    WHERE AccountID = p_target_account_id
    FOR UPDATE;

    IF v_source_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds');
    END IF;

    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_source_account_id;

    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_target_account_id;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        INSERT INTO ErrorLog (ModuleName, ErrorMessage)
        VALUES ('SafeTransferFunds', SQLERRM);
        ROLLBACK;
        RAISE;
END;
/

CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_employee_id IN NUMBER,
    p_percentage IN NUMBER
) AS
    v_rows_updated NUMBER;
BEGIN
    UPDATE Employees
    SET Salary = Salary + (Salary * p_percentage / 100)
    WHERE EmployeeID = p_employee_id;

    v_rows_updated := SQL%ROWCOUNT;

    IF v_rows_updated = 0 THEN
        RAISE NO_DATA_FOUND;
    END IF;

    COMMIT;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        INSERT INTO ErrorLog (ModuleName, ErrorMessage)
        VALUES ('UpdateSalary', 'Employee ID ' || p_employee_id || ' does not exist');
        ROLLBACK;
    WHEN OTHERS THEN
        INSERT INTO ErrorLog (ModuleName, ErrorMessage)
        VALUES ('UpdateSalary', SQLERRM);
        ROLLBACK;
END;
/

CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_customer_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
) AS
    v_exists NUMBER;
BEGIN
    SELECT COUNT(*) INTO v_exists
    FROM Customers
    WHERE CustomerID = p_customer_id;

    IF v_exists > 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Customer already exists');
    END IF;

    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified, IsVIP)
    VALUES (p_customer_id, p_name, p_dob, p_balance, SYSDATE, 'FALSE');

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN
        INSERT INTO ErrorLog (ModuleName, ErrorMessage)
        VALUES ('AddNewCustomer', SQLERRM);
        ROLLBACK;
END;
/
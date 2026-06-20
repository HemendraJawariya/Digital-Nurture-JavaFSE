SET SERVEROUTPUT ON;

BEGIN
    FOR customer_rec IN (SELECT CustomerID, DOB FROM Customers) LOOP
        IF TRUNC(MONTHS_BETWEEN(SYSDATE, customer_rec.DOB) / 12) > 60 THEN
            UPDATE Loans
            SET InterestRate = GREATEST(InterestRate - 1, 0)
            WHERE CustomerID = customer_rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

BEGIN
    FOR customer_rec IN (SELECT CustomerID, Balance FROM Customers) LOOP
        IF customer_rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 'TRUE'
            WHERE CustomerID = customer_rec.CustomerID;
        END IF;
    END LOOP;
    COMMIT;
END;
/

BEGIN
    FOR loan_rec IN (
        SELECT l.LoanID, c.Name, l.EndDate
        FROM Loans l
        JOIN Customers c ON c.CustomerID = l.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: Loan ' || loan_rec.LoanID || ' for ' || loan_rec.Name || ' is due on ' || TO_CHAR(loan_rec.EndDate, 'YYYY-MM-DD'));
    END LOOP;
END;
/
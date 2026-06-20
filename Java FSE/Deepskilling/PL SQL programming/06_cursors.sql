SET SERVEROUTPUT ON;

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT c.CustomerID, c.Name, t.TransactionDate, t.Amount, t.TransactionType
        FROM Customers c
        JOIN Accounts a ON a.CustomerID = c.CustomerID
        JOIN Transactions t ON t.AccountID = a.AccountID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
        ORDER BY c.CustomerID, t.TransactionDate;

    v_statement_rec GenerateMonthlyStatements%ROWTYPE;
    v_current_customer_id Customers.CustomerID%TYPE := NULL;
BEGIN
    OPEN GenerateMonthlyStatements;
    LOOP
        FETCH GenerateMonthlyStatements INTO v_statement_rec;
        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;

        IF v_current_customer_id IS NULL OR v_current_customer_id <> v_statement_rec.CustomerID THEN
            DBMS_OUTPUT.PUT_LINE('Monthly statement for ' || v_statement_rec.Name || ' (Customer ' || v_statement_rec.CustomerID || ')');
            v_current_customer_id := v_statement_rec.CustomerID;
        END IF;

        DBMS_OUTPUT.PUT_LINE('  ' || TO_CHAR(v_statement_rec.TransactionDate, 'YYYY-MM-DD') || ' | ' || v_statement_rec.TransactionType || ' | ' || v_statement_rec.Amount);
    END LOOP;
    CLOSE GenerateMonthlyStatements;
END;
/

DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance
        FROM Accounts
        FOR UPDATE;

    v_account_rec ApplyAnnualFee%ROWTYPE;
BEGIN
    OPEN ApplyAnnualFee;
    LOOP
        FETCH ApplyAnnualFee INTO v_account_rec;
        EXIT WHEN ApplyAnnualFee%NOTFOUND;

        UPDATE Accounts
        SET Balance = v_account_rec.Balance - 50,
            LastModified = SYSDATE
        WHERE CURRENT OF ApplyAnnualFee;
    END LOOP;
    CLOSE ApplyAnnualFee;
    COMMIT;
END;
/

DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, InterestRate
        FROM Loans
        FOR UPDATE;

    v_loan_rec UpdateLoanInterestRates%ROWTYPE;
BEGIN
    OPEN UpdateLoanInterestRates;
    LOOP
        FETCH UpdateLoanInterestRates INTO v_loan_rec;
        EXIT WHEN UpdateLoanInterestRates%NOTFOUND;

        UPDATE Loans
        SET InterestRate = GREATEST(v_loan_rec.InterestRate - 0.5, 3)
        WHERE CURRENT OF UpdateLoanInterestRates;
    END LOOP;
    CLOSE UpdateLoanInterestRates;
    COMMIT;
END;
/
DECLARE
    CURSOR loan_cursor IS
        SELECT LoanId, CurrentInterestRate
        FROM Loans;
    
    v_loanId Loans.LoanId%TYPE;
    v_currentInterestRate Loans.CurrentInterestRate%TYPE;
    v_newInterestRate Loans.CurrentInterestRate%TYPE;

    
    v_interestRateIncrease CONSTANT NUMBER := 0.5;

BEGIN
    OPEN loan_cursor;
    LOOP
        FETCH loan_cursor INTO v_loanId, v_currentInterestRate;
        EXIT WHEN loan_cursor%NOTFOUND;

        
        v_newInterestRate := v_currentInterestRate + v_interestRateIncrease;
        
       
        UPDATE Loans
        SET CurrentInterestRate = v_newInterestRate
        WHERE LoanId = v_loanId;
        
        
        DBMS_OUTPUT.PUT_LINE('Updated interest rate for Loan ID: ' || v_loanId || ' to ' || v_newInterestRate);
    END LOOP;
    
    CLOSE loan_cursor;
END;

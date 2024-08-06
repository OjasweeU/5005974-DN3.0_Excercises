DECLARE
    CURSOR account_cursor IS
        SELECT AccountId, Balance
        FROM Accounts;
    
    v_accountId Accounts.AccountId%TYPE;
    v_balance Accounts.Balance%TYPE;
    v_annualFee CONSTANT NUMBER := 100; 

BEGIN
    OPEN account_cursor;
    LOOP
        FETCH account_cursor INTO v_accountId, v_balance;
        EXIT WHEN account_cursor%NOTFOUND;
        
        
        UPDATE Accounts
        SET Balance = Balance - v_annualFee
        WHERE AccountId = v_accountId;
        
        
        DBMS_OUTPUT.PUT_LINE('Applied annual fee to Account ID: ' || v_accountId);
    END LOOP;
    
    CLOSE account_cursor;
END;

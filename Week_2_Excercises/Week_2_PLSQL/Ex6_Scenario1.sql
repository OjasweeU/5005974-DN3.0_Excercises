DECLARE
    CURSOR transaction_cursor IS
        SELECT CustomerId, TransactionDate, Amount, Description
        FROM Transactions
        WHERE EXTRACT(MONTH FROM TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
          AND EXTRACT(YEAR FROM TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
    
    v_customerId Customers.CustomerId%TYPE;
    v_transactionDate Transactions.TransactionDate%TYPE;
    v_amount Transactions.Amount%TYPE;
    v_description Transactions.Description%TYPE;

BEGIN
    OPEN transaction_cursor;
    LOOP
        FETCH transaction_cursor INTO v_customerId, v_transactionDate, v_amount, v_description;
        EXIT WHEN transaction_cursor%NOTFOUND;
        
        
        DBMS_OUTPUT.PUT_LINE('Statement for Customer ID: ' || v_customerId);
        DBMS_OUTPUT.PUT_LINE('Transaction Date: ' || v_transactionDate);
        DBMS_OUTPUT.PUT_LINE('Amount: ' || v_amount);
        DBMS_OUTPUT.PUT_LINE('Description: ' || v_description);
        DBMS_OUTPUT.PUT_LINE('--------------------------------------');
    END LOOP;
    
    CLOSE transaction_cursor;
END;

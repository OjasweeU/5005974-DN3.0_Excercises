DECLARE
    v_customer_name VARCHAR2(100);
    v_due_date DATE;
    CURSOR c_loans_due IS
        SELECT l.CustomerID, l.EndDate, c.Name
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30;
BEGIN
    FOR rec IN c_loans_due LOOP
        v_customer_name := rec.Name;
        v_due_date := rec.EndDate;
        
        -- Print the reminder message
        DBMS_OUTPUT.PUT_LINE('Reminder: Dear ' || v_customer_name || ', your loan is due on ' || TO_CHAR(v_due_date, 'YYYY-MM-DD') || '.');
    END LOOP;
END;

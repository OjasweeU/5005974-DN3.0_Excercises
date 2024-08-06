DECLARE
    v_age NUMBER;
    v_interest_rate NUMBER;
    v_customer_id NUMBER;
    CURSOR c_customers IS
        SELECT CustomerID, EXTRACT(YEAR FROM SYSDATE) - EXTRACT(YEAR FROM DOB) AS age
        FROM Customers;
BEGIN
    FOR rec IN c_customers LOOP
        v_customer_id := rec.CustomerID;
        v_age := rec.age;
        
        IF v_age > 60 THEN
            -- Update the interest rate by applying a 1% discount
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = v_customer_id;
        END IF;
    END LOOP;
    COMMIT;
END;
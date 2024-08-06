
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer (
        p_CustomerId IN Customers.CustomerId%TYPE,
        p_CustomerName IN Customers.CustomerName%TYPE,
        p_DateOfBirth IN Customers.DateOfBirth%TYPE
    );

    PROCEDURE UpdateCustomer (
        p_CustomerId IN Customers.CustomerId%TYPE,
        p_CustomerName IN Customers.CustomerName%TYPE,
        p_DateOfBirth IN Customers.DateOfBirth%TYPE
    );

    FUNCTION GetCustomerBalance (
        p_CustomerId IN Customers.CustomerId%TYPE
    ) RETURN NUMBER;
END CustomerManagement;
/


CREATE OR REPLACE PACKAGE BODY CustomerManagement AS
    PROCEDURE AddCustomer (
        p_CustomerId IN Customers.CustomerId%TYPE,
        p_CustomerName IN Customers.CustomerName%TYPE,
        p_DateOfBirth IN Customers.DateOfBirth%TYPE
    ) IS
    BEGIN
        INSERT INTO Customers (CustomerId, CustomerName, DateOfBirth)
        VALUES (p_CustomerId, p_CustomerName, p_DateOfBirth);
    END AddCustomer;

    PROCEDURE UpdateCustomer (
        p_CustomerId IN Customers.CustomerId%TYPE,
        p_CustomerName IN Customers.CustomerName%TYPE,
        p_DateOfBirth IN Customers.DateOfBirth%TYPE
    ) IS
    BEGIN
        UPDATE Customers
        SET CustomerName = p_CustomerName,
            DateOfBirth = p_DateOfBirth
        WHERE CustomerId = p_CustomerId;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance (
        p_CustomerId IN Customers.CustomerId%TYPE
    ) RETURN NUMBER IS
        v_balance NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_balance
        FROM Accounts
        WHERE CustomerId = p_CustomerId;

        RETURN v_balance;
    END GetCustomerBalance;
END CustomerManagement;
/

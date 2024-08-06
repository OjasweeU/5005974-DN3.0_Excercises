
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount (
        p_AccountId IN Accounts.AccountId%TYPE,
        p_CustomerId IN Accounts.CustomerId%TYPE,
        p_Balance IN Accounts.Balance%TYPE
    );

    PROCEDURE CloseAccount (
        p_AccountId IN Accounts.AccountId%TYPE
    );

    FUNCTION GetTotalBalance (
        p_CustomerId IN Accounts.CustomerId%TYPE
    ) RETURN NUMBER;
END AccountOperations;
/


CREATE OR REPLACE PACKAGE BODY AccountOperations AS
    PROCEDURE OpenAccount (
        p_AccountId IN Accounts.AccountId%TYPE,
        p_CustomerId IN Accounts.CustomerId%TYPE,
        p_Balance IN Accounts.Balance%TYPE
    ) IS
    BEGIN
        INSERT INTO Accounts (AccountId, CustomerId, Balance)
        VALUES (p_AccountId, p_CustomerId, p_Balance);
    END OpenAccount;

    PROCEDURE CloseAccount (
        p_AccountId IN Accounts.AccountId%TYPE
    ) IS
    BEGIN
        DELETE FROM Accounts
        WHERE AccountId = p_AccountId;
    END CloseAccount;

    FUNCTION GetTotalBalance (
        p_CustomerId IN Accounts.CustomerId%TYPE
    ) RETURN NUMBER IS
        v_total_balance NUMBER;
    BEGIN
        SELECT SUM(Balance) INTO v_total_balance
        FROM Accounts
        WHERE CustomerId = p_CustomerId;

        RETURN v_total_balance;
    END GetTotalBalance;
END AccountOperations;
/

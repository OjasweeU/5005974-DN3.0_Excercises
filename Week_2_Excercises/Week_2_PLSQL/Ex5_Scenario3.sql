CREATE TRIGGER CheckTransactionRules
ON Transactions
BEFORE INSERT
AS
BEGIN
    
    IF EXISTS (
        SELECT 1
        FROM inserted i
        JOIN Accounts a ON i.AccountId = a.AccountId
        WHERE i.TransactionType = 'Withdrawal'
          AND i.Amount > a.Balance
    )
    BEGIN
        RAISERROR ('Withdrawal amount exceeds the balance.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END

    
    IF EXISTS (
        SELECT 1
        FROM inserted i
        WHERE i.TransactionType = 'Deposit'
          AND i.Amount <= 0
    )
    BEGIN
        RAISERROR ('Deposit amount must be positive.', 16, 1);
        ROLLBACK TRANSACTION;
        RETURN;
    END
END

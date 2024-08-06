CREATE TRIGGER LogTransaction
ON Transactions
AFTER INSERT
AS
BEGIN
    
    INSERT INTO AuditLog (TransactionId, TransactionDate, Amount, AccountId, Action, LogDate)
    SELECT 
        i.TransactionId,
        i.TransactionDate,
        i.Amount,
        i.AccountId,
        'INSERT',
        GETDATE()
    FROM inserted i;
END

CREATE TRIGGER UpdateCustomerLastModified
ON Customers
AFTER UPDATE
AS
BEGIN

    UPDATE Customers
    SET LastModified = GETDATE()
    FROM Customers c
    INNER JOIN inserted i ON c.CustomerId = i.CustomerId;
END

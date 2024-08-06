CREATE PROCEDURE SafeTransferFunds
    @FromAccountId INT,
    @ToAccountId INT,
    @Amount DECIMAL(18, 2)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;

        
        IF (SELECT Balance FROM Accounts WHERE AccountId = @FromAccountId) < @Amount
        BEGIN
            THROW 50001, 'Insufficient funds', 1;
        END

        
        UPDATE Accounts
        SET Balance = Balance - @Amount
        WHERE AccountId = @FromAccountId;

        
        UPDATE Accounts
        SET Balance = Balance + @Amount
        WHERE AccountId = @ToAccountId;

        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        
        ROLLBACK TRANSACTION;

        
        DECLARE @ErrorMessage NVARCHAR(4000);
        DECLARE @ErrorSeverity INT;
        DECLARE @ErrorState INT;

        SELECT
            @ErrorMessage = ERROR_MESSAGE(),
            @ErrorSeverity = ERROR_SEVERITY(),
            @ErrorState = ERROR_STATE();

        
        INSERT INTO ErrorLog (ErrorMessage, ErrorSeverity, ErrorState, ErrorDate)
        VALUES (@ErrorMessage, @ErrorSeverity, @ErrorState, GETDATE());

        
        RAISERROR (@ErrorMessage, @ErrorSeverity, @ErrorState);
    END CATCH
END

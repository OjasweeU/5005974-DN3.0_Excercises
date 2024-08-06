CREATE PROCEDURE ProcessMonthlyInterest
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;

        
        UPDATE Accounts
        SET Balance = Balance * 1.01
        WHERE AccountType = 'Savings';

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

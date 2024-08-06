CREATE PROCEDURE UpdateEmployeeBonus
    @DepartmentId INT,
    @BonusPercentage DECIMAL(5, 2)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION;

        
        UPDATE Employees
        SET Salary = Salary * (1 + @BonusPercentage / 100)
        WHERE DepartmentId = @DepartmentId;

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

CREATE FUNCTION HasSufficientBalance 
(
    @AccountId INT,
    @Amount DECIMAL(18, 2)
)
RETURNS BIT
AS
BEGIN
    DECLARE @HasSufficient BIT;

    IF EXISTS (SELECT 1 
               FROM Accounts 
               WHERE AccountId = @AccountId 
                 AND Balance >= @Amount)
    BEGIN
        SET @HasSufficient = 1; 
    END
    ELSE
    BEGIN
        SET @HasSufficient = 0; 
    END

    RETURN @HasSufficient;
END

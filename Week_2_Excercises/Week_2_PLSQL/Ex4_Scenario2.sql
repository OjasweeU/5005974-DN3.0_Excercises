CREATE FUNCTION CalculateMonthlyInstallment 
(
    @LoanAmount DECIMAL(18, 2),
    @AnnualInterestRate DECIMAL(5, 2),
    @LoanDurationYears INT
)
RETURNS DECIMAL(18, 2)
AS
BEGIN
    DECLARE @MonthlyInstallment DECIMAL(18, 2);
    DECLARE @MonthlyInterestRate DECIMAL(5, 4);
    DECLARE @NumberOfPayments INT;

    SET @MonthlyInterestRate = @AnnualInterestRate / 12 / 100;
   
    SET @NumberOfPayments = @LoanDurationYears * 12;

    IF @MonthlyInterestRate > 0
    BEGIN
        SET @MonthlyInstallment = 
            @LoanAmount * (@MonthlyInterestRate * POWER(1 + @MonthlyInterestRate, @NumberOfPayments)) / 
            (POWER(1 + @MonthlyInterestRate, @NumberOfPayments) - 1);
    END
    ELSE
    BEGIN
        SET @MonthlyInstallment = @LoanAmount / @NumberOfPayments;
    END

    RETURN @MonthlyInstallment;
END


CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee (
        p_EmployeeId IN Employees.EmployeeId%TYPE,
        p_EmployeeName IN Employees.EmployeeName%TYPE,
        p_Salary IN Employees.Salary%TYPE,
        p_JoinDate IN Employees.JoinDate%TYPE
    );

    PROCEDURE UpdateEmployee (
        p_EmployeeId IN Employees.EmployeeId%TYPE,
        p_EmployeeName IN Employees.EmployeeName%TYPE,
        p_Salary IN Employees.Salary%TYPE
    );

    FUNCTION CalculateAnnualSalary (
        p_EmployeeId IN Employees.EmployeeId%TYPE
    ) RETURN NUMBER;
END EmployeeManagement;
/


CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS
    PROCEDURE HireEmployee (
        p_EmployeeId IN Employees.EmployeeId%TYPE,
        p_EmployeeName IN Employees.EmployeeName%TYPE,
        p_Salary IN Employees.Salary%TYPE,
        p_JoinDate IN Employees.JoinDate%TYPE
    ) IS
    BEGIN
        INSERT INTO Employees (EmployeeId, EmployeeName, Salary, JoinDate)
        VALUES (p_EmployeeId, p_EmployeeName, p_Salary, p_JoinDate);
    END HireEmployee;

    PROCEDURE UpdateEmployee (
        p_EmployeeId IN Employees.EmployeeId%TYPE,
        p_EmployeeName IN Employees.EmployeeName%TYPE,
        p_Salary IN Employees.Salary%TYPE
    ) IS
    BEGIN
        UPDATE Employees
        SET EmployeeName = p_EmployeeName,
            Salary = p_Salary
        WHERE EmployeeId = p_EmployeeId;
    END UpdateEmployee;

    FUNCTION CalculateAnnualSalary (
        p_EmployeeId IN Employees.EmployeeId%TYPE
    ) RETURN NUMBER IS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary
        FROM Employees
        WHERE EmployeeId = p_EmployeeId;

        RETURN v_salary * 12; 
    END CalculateAnnualSalary;
END EmployeeManagement;
/

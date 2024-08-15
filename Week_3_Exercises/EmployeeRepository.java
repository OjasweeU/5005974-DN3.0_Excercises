import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByName(String name);

    List<Employee> findByEmailContainingIgnoreCase(String emailFragment);

    List<Employee> findByDepartmentName(String departmentName);

    @Query("SELECT e FROM Employee e WHERE e.name LIKE %:name%")
    List<Employee> searchByName(@Param("name") String name);

    @Query(value = "SELECT * FROM employees e WHERE e.department_id = :departmentId", nativeQuery = true)
    List<Employee> findByDepartmentId(@Param("departmentId") Long departmentId);

    @Query(name = "Employee.findByDepartment")
    List<Employee> findByDepartmentName(@Param("departmentName") String departmentName);

    @Query(name = "Employee.findByEmail")
    Employee findByEmail(@Param("email") String email);
}

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Transactional
    public void saveEmployeesInBatch(List<Employee> employees) {
        final int batchSize = 50; // Example batch size
        for (int i = 0; i < employees.size(); i++) {
            employeeRepository.save(employees.get(i));
            if (i % batchSize == 0 && i > 0) {
                // Flush and clear session to avoid memory issues
                employeeRepository.flush();
                employeeRepository.clear();
            }
        }
    }
}

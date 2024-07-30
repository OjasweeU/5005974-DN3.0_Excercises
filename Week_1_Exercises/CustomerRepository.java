
public interface CustomerRepository {
    String findCustomerById(int id);
}


public class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(int id) {
        // In a real application, this would access a database
        return "Customer " + id;
    }
}

public class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void displayCustomer(int id) {
        String customer = repository.findCustomerById(id);
        System.out.println("Customer Details: " + customer);
    }
}


public class TestDependencyInjection {
    public static void main(String[] args) {
        CustomerRepository repository = new CustomerRepositoryImpl();
        CustomerService service = new CustomerService(repository);

        service.displayCustomer(1);
    }
}

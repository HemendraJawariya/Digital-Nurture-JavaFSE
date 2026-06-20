public class DependencyInjection {

    interface CustomerRepository {
        String findCustomerNameById(int id);
    }

    static class InMemoryCustomerRepository implements CustomerRepository {
        @Override
        public String findCustomerNameById(int id) {
            if (id == 1) {
                return "Priya";
            }
            if (id == 2) {
                return "Rahul";
            }
            return "Unknown";
        }
    }

    static class CustomerService {
        private final CustomerRepository repository;

        CustomerService(CustomerRepository repository) {
            this.repository = repository;
        }

        public void printCustomer(int id) {
            System.out.println("Customer: " + repository.findCustomerNameById(id));
        }
    }

    public static void main(String[] args) {
        CustomerRepository repository = new InMemoryCustomerRepository();
        CustomerService service = new CustomerService(repository);

        service.printCustomer(1);
        service.printCustomer(2);
    }
}
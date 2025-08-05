package ServiceLayer;

import DAO.CustomerDAO;
import Model.Customer;
import Model.CustomerPattern;
import Model.CustomerType;
import Model.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    public List<Customer> getAllCustomers() {
        logger.info("Retrieving all customers");
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.isEmpty()) {
            logger.warn("No customers found");
        }
        return customers;
    }


    public void displayCustomerPatterns() {
        List<CustomerPattern> patterns = customerDAO.getCustomerPatterns();
        if (patterns.isEmpty()) {
            logger.warn("No customer patterns found");
            System.out.println("No customer patterns available.");
        } else {
            for (CustomerPattern pattern : patterns) {
                System.out.println("Customer Type: " + pattern.getCustomerType() +
                        ", Gender: " + pattern.getGender() +
                        ", Invoice Count: " + pattern.getInvoiceCount() +
                        ", Total Sales: " + pattern.getTotalSales());
            }
        }
    }

    public List<CustomerPattern> getCustomerPatternsByType(CustomerType type) {
        logger.info("Retrieving customer patterns for type: {}", type);
        return customerDAO.getCustomerPatternsByType(type);
    }


    public List<CustomerPattern> getCustomerPatternsByGender(Gender gender) {
        logger.info("Retrieving customer patterns for gender: {}", gender);
        return customerDAO.getCustomerPatternsByGender(gender);
    }
}
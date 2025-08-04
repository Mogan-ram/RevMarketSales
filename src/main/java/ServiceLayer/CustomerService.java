package ServiceLayer;

import DAO.CustomerDAO;
import Model.Customer;
import Model.CustomerPattern;
import Model.CustomerType;
import Model.Gender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service layer for customer-related operations.
 */
public class CustomerService {
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerDAO customerDAO;

    public CustomerService() {
        this.customerDAO = new CustomerDAO();
    }

    /**
     * Retrieves all customers.
     *
     * @return a list of Customer objects
     */
    public List<Customer> getAllCustomers() {
        logger.info("Retrieving all customers");
        List<Customer> customers = customerDAO.getAllCustomers();
        if (customers.isEmpty()) {
            logger.warn("No customers found");
        }
        return customers;
    }

    /**
     * Retrieves and displays customer purchasing patterns.
     */
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

    /**
     * Retrieves customer purchasing patterns filtered by customer type.
     *
     * @param type the customer type to filter by
     * @return a list of CustomerPattern objects
     */
    public List<CustomerPattern> getCustomerPatternsByType(CustomerType type) {
        logger.info("Retrieving customer patterns for type: {}", type);
        return customerDAO.getCustomerPatternsByType(type);
    }

    /**
     * Retrieves customer purchasing patterns filtered by gender.
     *
     * @param gender the gender to filter by
     * @return a list of CustomerPattern objects
     */
    public List<CustomerPattern> getCustomerPatternsByGender(Gender gender) {
        logger.info("Retrieving customer patterns for gender: {}", gender);
        return customerDAO.getCustomerPatternsByGender(gender);
    }
}
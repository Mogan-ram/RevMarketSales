package DAO;

import Model.Customer;
import Model.CustomerType;
import Model.Gender;
import Model.CustomerPattern;
import Util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for interacting with the customer table and related views.
 */
public class CustomerDAO {
    private static final Logger logger = LoggerFactory.getLogger(CustomerDAO.class);

    /**
     * Retrieves all customers from the customer table.
     *
     * @return a list of Customer objects
     * @throws RuntimeException if a database error occurs
     */
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching all customers");
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),
                        CustomerType.valueOf(rs.getString("customer_type").toUpperCase()),
                        Gender.valueOf(rs.getString("gender").toUpperCase())
                );
                customers.add(customer);
                logger.debug("Added customer: {}", customer);
            }
            if (customers.isEmpty()) {
                logger.warn("No customers found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching customers", e);
            throw new RuntimeException("Error while getting customers", e);
        }
        return customers;
    }

    /**
     * Retrieves customer purchasing patterns from the customer_distribution view.
     *
     * @return a list of CustomerPattern objects containing customer type, gender, invoice count, and total sales
     * @throws RuntimeException if a database error occurs
     */
    public List<CustomerPattern> getCustomerPatterns() {
        List<CustomerPattern> patterns = new ArrayList<>();
        String sql = "SELECT customer_type, gender, invoice_count, total_sales FROM customer_distribution";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching customer purchasing patterns");
            while (rs.next()) {
                CustomerPattern pattern = new CustomerPattern(
                        CustomerType.valueOf(rs.getString("customer_type").toUpperCase()),
                        Gender.valueOf(rs.getString("gender").toUpperCase()),
                        rs.getInt("invoice_count"),
                        rs.getBigDecimal("total_sales")
                );
                patterns.add(pattern);
                logger.debug("Added pattern: {}", pattern);
            }
            if (patterns.isEmpty()) {
                logger.warn("No customer patterns found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching customer patterns", e);
            throw new RuntimeException("Error while getting customer patterns", e);
        }
        return patterns;
    }

    /**
     * Retrieves customer purchasing patterns filtered by customer type.
     *
     * @param type the customer type to filter by
     * @return a list of CustomerPattern objects
     * @throws RuntimeException if a database error occurs
     */
    public List<CustomerPattern> getCustomerPatternsByType(CustomerType type) {
        List<CustomerPattern> patterns = new ArrayList<>();
        String sql = "SELECT customer_type, gender, invoice_count, total_sales " +
                "FROM customer_distribution WHERE customer_type = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, type.name());
            try (ResultSet rs = stmt.executeQuery()) {
                logger.info("Fetching customer patterns for type: {}", type);
                while (rs.next()) {
                    CustomerPattern pattern = new CustomerPattern(
                            CustomerType.valueOf(rs.getString("customer_type").toUpperCase()),
                            Gender.valueOf(rs.getString("gender").toUpperCase()),
                            rs.getInt("invoice_count"),
                            rs.getBigDecimal("total_sales")
                    );
                    patterns.add(pattern);
                    logger.debug("Added pattern: {}", pattern);
                }
                if (patterns.isEmpty()) {
                    logger.warn("No patterns found for customer type: {}", type);
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching customer patterns by type: {}", type, e);
            throw new RuntimeException("Error while getting customer patterns by type", e);
        }
        return patterns;
    }

    /**
     * Retrieves customer purchasing patterns filtered by gender.
     *
     * @param gender the gender to filter by
     * @return a list of CustomerPattern objects
     * @throws RuntimeException if a database error occurs
     */
    public List<CustomerPattern> getCustomerPatternsByGender(Gender gender) {
        List<CustomerPattern> patterns = new ArrayList<>();
        String sql = "SELECT customer_type, gender, invoice_count, total_sales " +
                "FROM customer_distribution WHERE gender = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, gender.name());
            try (ResultSet rs = stmt.executeQuery()) {
                logger.info("Fetching customer patterns for gender: {}", gender);
                while (rs.next()) {
                    CustomerPattern pattern = new CustomerPattern(
                            CustomerType.valueOf(rs.getString("customer_type").toUpperCase()),
                            Gender.valueOf(rs.getString("gender").toUpperCase()),
                            rs.getInt("invoice_count"),
                            rs.getBigDecimal("total_sales")
                    );
                    patterns.add(pattern);
                    logger.debug("Added pattern: {}", pattern);
                }
                if (patterns.isEmpty()) {
                    logger.warn("No patterns found for gender: {}", gender);
                }
            }
        } catch (SQLException e) {
            logger.error("Error fetching customer patterns by gender: {}", gender, e);
            throw new RuntimeException("Error while getting customer patterns by gender", e);
        }
        return patterns;
    }
}
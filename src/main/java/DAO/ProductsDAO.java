package DAO;

import Model.ProductRating;
import Util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Data Access Object for interacting with the products table and related views.
 */
public class ProductsDAO {
    private static final Logger logger = LoggerFactory.getLogger(ProductsDAO.class);

    /**
     * Retrieves average ratings by product line from the product_avg view.
     *
     * @return a list of ProductRating objects
     * @throws RuntimeException if a database error occurs
     */
    public List<ProductRating> getAverageRatings() {
        List<ProductRating> ratings = new ArrayList<>();
        String sql = "SELECT * FROM product_avg";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching average ratings");
            while (rs.next()) {
                ProductRating rating = new ProductRating(
                        rs.getString("product_line"),
                        rs.getDouble("average_rating")
                );
                ratings.add(rating);
                logger.debug("Added rating: {}", rating);
            }
            if (ratings.isEmpty()) {
                logger.warn("No average ratings found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching average ratings", e);
            throw new RuntimeException("Error while getting average ratings", e);
        }
        return ratings;
    }

    /**
     * Retrieves product profitability from the product_performance view.
     *
     * @return a map of product line to gross income
     * @throws RuntimeException if a database error occurs
     */
    public Map<String, BigDecimal> getProductProfitability() {
        Map<String, BigDecimal> profitability = new LinkedHashMap<>();
        String sql = "SELECT product_line, ROUND(SUM(total_income), 2) AS total_gross_income " +
                "FROM product_performance GROUP BY product_line ORDER BY total_income DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching product profitability");
            while (rs.next()) {
                profitability.put(rs.getString("product_line"), rs.getBigDecimal("total_gross_income"));
                logger.debug("Product Line: {}, Gross Income: {}", rs.getString("product_line"), rs.getBigDecimal("total_gross_income"));
            }
            if (profitability.isEmpty()) {
                logger.warn("No product profitability data found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching product profitability", e);
            throw new RuntimeException("Error while getting product profitability", e);
        }
        return profitability;
    }
}
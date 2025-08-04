package ServiceLayer;

import DAO.ProductsDAO;
import Model.ProductRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Service layer for product-related operations.
 */
public class ProductsService {
    private static final Logger logger = LoggerFactory.getLogger(ProductsService.class);
    private final ProductsDAO productsDAO;

    public ProductsService() {
        this.productsDAO = new ProductsDAO();
    }

    /**
     * Retrieves average ratings by product line.
     *
     * @return a list of ProductRating objects
     */
    public List<ProductRating> getAverageRatings() {
        logger.info("Retrieving average ratings");
        List<ProductRating> ratings = productsDAO.getAverageRatings();
        if (ratings.isEmpty()) {
            logger.warn("No average ratings found");
        }
        return ratings;
    }

    /**
     * Retrieves product profitability.
     *
     * @return a map of product line to gross income
     */
    public Map<String, BigDecimal> getProductProfitability() {
        logger.info("Retrieving product profitability");
        Map<String, BigDecimal> profitability = productsDAO.getProductProfitability();
        if (profitability.isEmpty()) {
            logger.warn("No product profitability data found");
        }
        return profitability;
    }
}
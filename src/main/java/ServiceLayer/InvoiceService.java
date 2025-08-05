package ServiceLayer;

import DAO.InvoiceDAO;
import DAO.ProductsDAO;
import Model.ProductRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public class InvoiceService {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceService.class);
    private final InvoiceDAO invoiceDAO;
    private final ProductsDAO productsDAO;

    public InvoiceService() {
        this.invoiceDAO = new InvoiceDAO();
        this.productsDAO = new ProductsDAO();
    }

    public BigDecimal[] getKPI() {
        logger.info("Retrieving KPIs");
        BigDecimal[] kpis = invoiceDAO.getKPI();
        if (kpis[0] == null || kpis[1] == null) {
            logger.warn("KPIs contain null values");
        }
        return kpis;
    }

    public void displayAverageRatings() {
        logger.info("Displaying average ratings by product line");
        List<ProductRating> ratings = productsDAO.getAverageRatings();
        if (ratings.isEmpty()) {
            logger.warn("No average ratings found");
            System.out.println("No average ratings available.");
        } else {
            for (ProductRating rating : ratings) {
                System.out.println("Product Line: " + rating.getProductLine() +
                        ", Rating: " + String.format("%.2f", rating.getAverageRating()));
            }
        }
    }

    public Map<String, BigDecimal> getSalesTrends() {
        logger.info("Retrieving monthly sales trends");
        Map<String, BigDecimal> trends = invoiceDAO.getSalesTrends();
        if (trends.isEmpty()) {
            logger.warn("No sales trends found");
        }
        return trends;
    }

    public Map<String, BigDecimal> getQuarterlySalesTrends() {
        logger.info("Retrieving quarterly sales trends");
        Map<String, BigDecimal> trends = invoiceDAO.getQuarterlySalesTrends();
        if (trends.isEmpty()) {
            logger.warn("No quarterly sales trends found");
        }
        return trends;
    }

    public Map<String, BigDecimal> getYearlySalesTrends() {
        logger.info("Retrieving yearly sales trends");
        Map<String, BigDecimal> trends = invoiceDAO.getYearlySalesTrends();
        if (trends.isEmpty()) {
            logger.warn("No yearly sales trends found");
        }
        return trends;
    }


    public Map<String, BigDecimal> getDiscountImpact() {
        logger.info("Retrieving discount impact");
        Map<String, BigDecimal> impact = invoiceDAO.getDiscountImpact();
        if (impact.isEmpty()) {
            logger.warn("No discount impact data found");
        }
        return impact;
    }
}
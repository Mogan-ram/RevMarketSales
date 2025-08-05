package DAO;

import Util.DBConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;


public class InvoiceDAO {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceDAO.class);


    public BigDecimal[] getKPI() {
        BigDecimal[] kpis = new BigDecimal[2];
        String sql = "SELECT ROUND(SUM(total_sales), 2) AS Total_sales, " +
                "ROUND(SUM(gross_income), 2) AS Total_gross_income FROM invoices";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching KPIs from invoices");
            if (rs.next()) {
                kpis[0] = rs.getBigDecimal(1);
                kpis[1] = rs.getBigDecimal(2);
                logger.debug("KPIs: Total Sales = {}, Gross Income = {}", kpis[0], kpis[1]);
            } else {
                logger.warn("No invoice data found for KPIs");
            }
        } catch (SQLException e) {
            logger.error("Error fetching KPIs", e);
            throw new RuntimeException("Error while getting KPIs", e);
        }
        return kpis;
    }


    public Map<String, BigDecimal> getSalesTrends() {
        Map<String, BigDecimal> trends = new LinkedHashMap<>();
        String sql = "SELECT DATE_FORMAT(dateTime, '%Y-%m') AS month, ROUND(SUM(total_sales), 2) AS total_sales " +
                "FROM invoices GROUP BY DATE_FORMAT(dateTime, '%Y-%m') ORDER BY month";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching monthly sales trends");
            while (rs.next()) {
                trends.put(rs.getString("month"), rs.getBigDecimal("total_sales"));
                logger.debug("Month: {}, Sales: {}", rs.getString("month"), rs.getBigDecimal("total_sales"));
            }
            if (trends.isEmpty()) {
                logger.warn("No sales trends found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching sales trends", e);
            throw new RuntimeException("Error while getting sales trends", e);
        }
        return trends;
    }


    public Map<String, BigDecimal> getQuarterlySalesTrends() {
        Map<String, BigDecimal> trends = new LinkedHashMap<>();
        String sql = "SELECT quarter, ROUND(SUM(total_sales), 2) AS total_sales\n" +
                "FROM (\n" +
                "    SELECT total_sales,\n" +
                "           CONCAT(YEAR(dateTime), '-Q', QUARTER(dateTime)) AS quarter\n" +
                "    FROM invoices\n" +
                ") AS sub\n" +
                "GROUP BY quarter\n" +
                "ORDER BY quarter;";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching quarterly sales trends");
            while (rs.next()) {
                trends.put(rs.getString("quarter"), rs.getBigDecimal("total_sales"));
                logger.debug("Quarter: {}, Sales: {}", rs.getString("quarter"), rs.getBigDecimal("total_sales"));
            }
            if (trends.isEmpty()) {
                logger.warn("No quarterly sales trends found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching quarterly sales trends", e);
            throw new RuntimeException("Error while getting quarterly sales trends", e);
        }
        return trends;
    }


    public Map<String, BigDecimal> getYearlySalesTrends() {
        Map<String, BigDecimal> trends = new LinkedHashMap<>();
        String sql = "SELECT YEAR(dateTime) AS year, ROUND(SUM(total_sales), 2) AS total_sales " +
                "FROM invoices GROUP BY YEAR(dateTime) ORDER BY year";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching yearly sales trends");
            while (rs.next()) {
                trends.put(rs.getString("year"), rs.getBigDecimal("total_sales"));
                logger.debug("Year: {}, Sales: {}", rs.getString("year"), rs.getBigDecimal("total_sales"));
            }
            if (trends.isEmpty()) {
                logger.warn("No yearly sales trends found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching yearly sales trends", e);
            throw new RuntimeException("Error while getting yearly sales trends", e);
        }
        return trends;
    }


    public Map<String, BigDecimal> getDiscountImpact() {
        Map<String, BigDecimal> impact = new LinkedHashMap<>();
        String sql = "SELECT CASE WHEN gross_margin_percentage < 0.1 THEN 'High Discount' ELSE 'Low Discount' END AS discount_level, " +
                "ROUND(SUM(total_sales), 2) AS total_sales " +
                "FROM invoices GROUP BY discount_level ORDER BY total_sales DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching discount impact");
            while (rs.next()) {
                impact.put(rs.getString("discount_level"), rs.getBigDecimal("total_sales"));
                logger.debug("Discount Level: {}, Sales: {}", rs.getString("discount_level"), rs.getBigDecimal("total_sales"));
            }
            if (impact.isEmpty()) {
                logger.warn("No discount impact data found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching discount impact", e);
            throw new RuntimeException("Error while getting discount impact", e);
        }
        return impact;
    }
}
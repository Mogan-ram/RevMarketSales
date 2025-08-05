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


public class InvoiceItemDAO {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceItemDAO.class);


    public Map<Integer, BigDecimal> getPeakSalesHours() {
        Map<Integer, BigDecimal> peakHours = new LinkedHashMap<>();
        String sql = "SELECT HOUR(i.dateTime) AS hour, ROUND(SUM(i.total_sales), 2) AS total_sales " +
                "FROM invoices i JOIN invoice_items ii ON i.invoice_id = ii.invoice_id " +
                "GROUP BY HOUR(i.dateTime) ORDER BY total_sales DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching peak sales hours");
            while (rs.next()) {
                peakHours.put(rs.getInt("hour"), rs.getBigDecimal("total_sales"));
                logger.debug("Hour: {}, Sales: {}", rs.getInt("hour"), rs.getBigDecimal("total_sales"));
            }
            if (peakHours.isEmpty()) {
                logger.warn("No peak sales hours found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching peak sales hours", e);
            throw new RuntimeException("Error while getting peak sales hours", e);
        }
        return peakHours;
    }


    public Map<String, BigDecimal> getPeakSalesDays() {
        Map<String, BigDecimal> peakDays = new LinkedHashMap<>();
        String sql = "SELECT DAYNAME(i.dateTime) AS day_name, ROUND(SUM(i.total_sales), 2) AS total_sales " +
                "FROM invoices i JOIN invoice_items ii ON i.invoice_id = ii.invoice_id " +
                "GROUP BY DAYNAME(i.dateTime) ORDER BY total_sales DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            logger.info("Fetching peak sales days");
            while (rs.next()) {
                peakDays.put(rs.getString("day_name"), rs.getBigDecimal("total_sales"));
                logger.debug("Day: {}, Sales: {}", rs.getString("day_name"), rs.getBigDecimal("total_sales"));
            }
            if (peakDays.isEmpty()) {
                logger.warn("No peak sales days found");
            }
        } catch (SQLException e) {
            logger.error("Error fetching peak sales days", e);
            throw new RuntimeException("Error while getting peak sales days", e);
        }
        return peakDays;
    }
}
package ServiceLayer;

import DAO.InvoiceItemDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class InvoiceItemService {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceItemService.class);
    private final InvoiceItemDAO invoiceItemDAO;

    public InvoiceItemService() {
        this.invoiceItemDAO = new InvoiceItemDAO();
    }

    public void displayPeakSalesHours() {
        logger.info("Displaying peak sales hours");
        Map<Integer, BigDecimal> peakHours = invoiceItemDAO.getPeakSalesHours();
        if (peakHours.isEmpty()) {
            logger.warn("No peak sales hours found");
            System.out.println("No peak sales hours available.");
            return;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
        for (Map.Entry<Integer, BigDecimal> entry : peakHours.entrySet()) {
            try {

                Date date = new SimpleDateFormat("HH").parse(String.valueOf(entry.getKey()));
                String formattedHour = sdf.format(date);
                System.out.println("Hour: " + formattedHour + ", Total Sales: $" + entry.getValue());
            } catch (Exception e) {
                logger.error("Error formatting hour: {}", entry.getKey(), e);
            }
        }
    }


    public void displayPeakSalesDays() {
        logger.info("Displaying peak sales days");
        Map<String, BigDecimal> peakDays = invoiceItemDAO.getPeakSalesDays();
        if (peakDays.isEmpty()) {
            logger.warn("No peak sales days found");
            System.out.println("No peak sales days available.");
            return;
        }
        for (Map.Entry<String, BigDecimal> entry : peakDays.entrySet()) {
            System.out.println("Day: " + entry.getKey() + ", Total Sales: $" + entry.getValue());
        }
    }
}
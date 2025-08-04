import ServiceLayer.BranchService;
import ServiceLayer.CustomerService;
import ServiceLayer.InvoiceService;
import ServiceLayer.InvoiceItemService;
import ServiceLayer.ProductsService;
import Model.CustomerPattern;
import Model.CustomerType;
import Model.Gender;
import Model.ProductRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Locale;

/**
 * Main class for the RevMarketSales CLI application with sub-menus.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Entry point for the menu-based CLI application.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        BranchService branchService = new BranchService();
        CustomerService customerService = new CustomerService();
        InvoiceService invoiceService = new InvoiceService();
        ProductsService productsService = new ProductsService();
        InvoiceItemService invoiceItemService = new InvoiceItemService();

        while (true) {
            displayMainMenu();
            try {
                System.out.print("Enter your choice (1-10): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        displayKPIsMenu(invoiceService, productsService);
                        break;
                    case 2:
                        displayTopBranch(branchService);
                        break;
                    case 3:
                        displayTopCity(branchService);
                        break;
                    case 4:
                        displayCustomerPatternsMenu(customerService);
                        break;
                    case 5:
                        displaySalesTrendsMenu(invoiceService);
                        break;
                    case 6:
                        displayPeakSalesHours(invoiceItemService);
                        break;
                    case 7:
                        displayPeakSalesDays(invoiceItemService);
                        break;
                    case 8:
                        displayProductProfitability(productsService);
                        break;
                    case 9:
                        displayDiscountImpact(invoiceService);
                        break;
                    case 10:
                        logger.info("Exiting application");
                        System.out.println("BYEEEEEEEE!");
                        scanner.close();
                        return;
                    default:
                        logger.warn("Invalid menu choice: {}", choice);
                        System.out.println("Invalid choice. Please select a number between 1 and 10.");
                }
            } catch (InputMismatchException e) {
                logger.warn("Invalid input, expected a number", e);
                System.out.println("Please enter a valid number.");
                scanner.nextLine(); // Clear invalid input
            } catch (Exception e) {
                logger.error("Error processing menu option", e);
                System.out.println("An error occurred: " + e.getMessage());
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n=== RevMarketSales Menu ===");
        System.out.println("1. View KPIs");
        System.out.println("2. View Top Branch");
        System.out.println("3. View Top City");
        System.out.println("4. View Customer Purchasing Patterns");
        System.out.println("5. View Sales Trends");
        System.out.println("6. View Peak Sales Hours");
        System.out.println("7. View Peak Sales Days");
        System.out.println("8. View Product Profitability");
        System.out.println("9. View Discount Impact");
        System.out.println("10. Exit");
    }

    private static void displayKPIsMenu(InvoiceService invoiceService, ProductsService productsService) {
        while (true) {
            System.out.println("\n=== KPIs Menu ===");
            System.out.println("1. Total Sales");
            System.out.println("2. Gross Income");
            System.out.println("3. Average Rating");
            System.out.println("4. Back to Main Menu");
            try {
                System.out.print("Enter your choice (1-4): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        logger.info("Displaying Total Sales");
                        BigDecimal[] kpis = invoiceService.getKPI();
                        System.out.println("Total Sales: " + (kpis[0] != null ? currencyFormat.format(kpis[0]) : "No data available."));
                        break;
                    case 2:
                        logger.info("Displaying Gross Income");
                        kpis = invoiceService.getKPI();
                        System.out.println("Gross Income: " + (kpis[1] != null ? currencyFormat.format(kpis[1]) : "No data available."));
                        break;
                    case 3:
                        logger.info("Displaying Average Ratings");
                        List<ProductRating> ratings = productsService.getAverageRatings();
                        if (ratings.isEmpty()) {
                            logger.warn("No average ratings found");
                            System.out.println("No average ratings available.");
                        } else {
                            for (ProductRating rating : ratings) {
                                System.out.println("Product Line: " + rating.getProductLine() +
                                        ", Rating: " + String.format("%.2f", rating.getAverageRating()));
                            }
                        }
                        break;
                    case 4:
                        return;
                    default:
                        logger.warn("Invalid KPIs menu choice: {}", choice);
                        System.out.println("Invalid choice. Please select a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                logger.warn("Invalid input, expected a number", e);
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            } catch (Exception e) {
                logger.error("Error processing KPIs menu option", e);
                System.out.println("An error occurred: " + e.getMessage());
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayCustomerPatternsMenu(CustomerService service) {
        while (true) {
            System.out.println("\n=== Customer Patterns Menu ===");
            System.out.println("1. All Patterns");
            System.out.println("2. By Customer Type");
            System.out.println("3. By Gender");
            System.out.println("4. Back to Main Menu");
            try {
                System.out.print("Enter your choice (1-4): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        logger.info("Displaying all customer patterns");
                        service.displayCustomerPatterns();
                        break;
                    case 2:
                        System.out.println("Enter Customer Type (Member/Normal): ");
                        String typeInput = scanner.nextLine().trim();
                        try {
                            CustomerType type = CustomerType.valueOf(typeInput.toUpperCase());
                            logger.info("Displaying customer patterns for type: {}", type);
                            List<CustomerPattern> patterns = service.getCustomerPatternsByType(type);
                            if (patterns.isEmpty()) {
                                logger.warn("No patterns found for customer type: {}", type);
                                System.out.println("No patterns available for " + type);
                            } else {
                                for (CustomerPattern pattern : patterns) {
                                    System.out.println("Customer Type: " + pattern.getCustomerType() +
                                            ", Gender: " + pattern.getGender() +
                                            ", Invoice Count: " + pattern.getInvoiceCount() +
                                            ", Total Sales: " + currencyFormat.format(pattern.getTotalSales()));
                                }
                            }
                        } catch (IllegalArgumentException e) {
                            logger.warn("Invalid customer type: {}", typeInput);
                            System.out.println("Invalid customer type. Please enter 'Member' or 'Normal'.");
                        }
                        break;
                    case 3:
                        System.out.println("Enter Gender (Male/Female/Other): ");
                        String genderInput = scanner.nextLine().trim();
                        try {
                            Gender gender = Gender.valueOf(genderInput.toUpperCase());
                            logger.info("Displaying customer patterns for gender: {}", gender);
                            List<CustomerPattern> patterns = service.getCustomerPatternsByGender(gender);
                            if (patterns.isEmpty()) {
                                logger.warn("No patterns found for gender: {}", gender);
                                System.out.println("No patterns available for " + gender);
                            } else {
                                for (CustomerPattern pattern : patterns) {
                                    System.out.println("Customer Type: " + pattern.getCustomerType() +
                                            ", Gender: " + pattern.getGender() +
                                            ", Invoice Count: " + pattern.getInvoiceCount() +
                                            ", Total Sales: " + currencyFormat.format(pattern.getTotalSales()));
                                }
                            }
                        } catch (IllegalArgumentException e) {
                            logger.warn("Invalid gender: {}", genderInput);
                            System.out.println("Invalid gender. Please enter 'Male', 'Female', or 'Color'.");
                        }
                        break;
                    case 4:
                        return;
                    default:
                        logger.warn("Invalid customer patterns menu choice: {}", choice);
                        System.out.println("Invalid choice. Please select a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                logger.warn("Invalid input, expected a number", e);
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            } catch (Exception e) {
                logger.error("Error processing customer patterns menu option", e);
                System.out.println("An error occurred: " + e.getMessage());
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displaySalesTrendsMenu(InvoiceService service) {
        while (true) {
            System.out.println("\n=== Sales Trends Menu ===");
            System.out.println("1. Monthly Trends");
            System.out.println("2. Quarterly Trends");
            System.out.println("3. Yearly Trends");
            System.out.println("4. Back to Main Menu");
            try {
                System.out.print("Enter your choice (1-4): ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                switch (choice) {
                    case 1:
                        logger.info("Displaying monthly sales trends");
                        Map<String, BigDecimal> monthlyTrends = service.getSalesTrends();
                        if (monthlyTrends.isEmpty()) {
                            logger.warn("No monthly sales trends found");
                            System.out.println("No monthly sales trends available.");
                        } else {
                            for (Map.Entry<String, BigDecimal> entry : monthlyTrends.entrySet()) {
                                System.out.println("Month: " + entry.getKey() +
                                        ", Total Sales: " + currencyFormat.format(entry.getValue()));
                            }
                        }
                        break;
                    case 2:
                        logger.info("Displaying quarterly sales trends");
                        Map<String, BigDecimal> quarterlyTrends = service.getQuarterlySalesTrends();
                        if (quarterlyTrends.isEmpty()) {
                            logger.warn("No quarterly sales trends found");
                            System.out.println("No quarterly sales trends available.");
                        } else {
                            for (Map.Entry<String, BigDecimal> entry : quarterlyTrends.entrySet()) {
                                System.out.println("Quarter: " + entry.getKey() +
                                        ", Total Sales: " + currencyFormat.format(entry.getValue()));
                            }
                        }
                        break;
                    case 3:
                        logger.info("Displaying yearly sales trends");
                        Map<String, BigDecimal> yearlyTrends = service.getYearlySalesTrends();
                        if (yearlyTrends.isEmpty()) {
                            logger.warn("No yearly sales trends found");
                            System.out.println("No yearly sales trends available.");
                        } else {
                            for (Map.Entry<String, BigDecimal> entry : yearlyTrends.entrySet()) {
                                System.out.println("Year: " + entry.getKey() +
                                        ", Total Sales: " + currencyFormat.format(entry.getValue()));
                            }
                        }
                        break;
                    case 4:
                        return;
                    default:
                        logger.warn("Invalid sales trends menu choice: {}", choice);
                        System.out.println("Invalid choice. Please select a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                logger.warn("Invalid input, expected a number", e);
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            } catch (Exception e) {
                logger.error("Error processing sales trends menu option", e);
                System.out.println("An error occurred: " + e.getMessage());
            }
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }

    private static void displayTopBranch(BranchService service) {
        logger.info("Displaying top branch");
        String topBranch = service.topBranch();
        if (topBranch == null || topBranch.isEmpty()) {
            logger.warn("No top branch found");
            System.out.println("No top branch available.");
        } else {
            System.out.println("Top Branch: " + topBranch);
        }
    }

    private static void displayTopCity(BranchService service) {
        logger.info("Displaying top city");
        String topCity = service.topCity();
        if (topCity == null || topCity.isEmpty()) {
            logger.warn("No top city found");
            System.out.println("No top city available.");
        } else {
            System.out.println("Top City: " + topCity);
        }
    }

    private static void displayPeakSalesHours(InvoiceItemService service) {
        logger.info("Displaying peak sales hours");
        service.displayPeakSalesHours();
    }

    private static void displayPeakSalesDays(InvoiceItemService service) {
        logger.info("Displaying peak sales days");
        service.displayPeakSalesDays();
    }

    private static void displayProductProfitability(ProductsService service) {
        logger.info("Displaying product profitability");
        Map<String, BigDecimal> profitability = service.getProductProfitability();
        if (profitability.isEmpty()) {
            logger.warn("No product profitability found");
            System.out.println("No product profitability data available.");
        } else {
            for (Map.Entry<String, BigDecimal> entry : profitability.entrySet()) {
                System.out.println("Product Line: " + entry.getKey() +
                        ", Gross Income: " + currencyFormat.format(entry.getValue()));
            }
        }
    }

    private static void displayDiscountImpact(InvoiceService service) {
        logger.info("Displaying discount impact");
        Map<String, BigDecimal> impact = service.getDiscountImpact();
        if (impact.isEmpty()) {
            logger.warn("No discount impact found");
            System.out.println("No discount impact data available.");
        } else {
            for (Map.Entry<String, BigDecimal> entry : impact.entrySet()) {
                System.out.println("Discount Level: " + entry.getKey() +
                        ", Total Sales: " + currencyFormat.format(entry.getValue()));
            }
        }
    }
}
package Model;

import java.math.BigDecimal;


public class CustomerPattern {
    private CustomerType customerType;
    private Gender gender;
    private int invoiceCount;
    private BigDecimal totalSales;

    public CustomerPattern() {
    }

    public CustomerPattern(CustomerType customerType, Gender gender, int invoiceCount, BigDecimal totalSales) {
        this.customerType = customerType;
        this.gender = gender;
        this.invoiceCount = invoiceCount;
        this.totalSales = totalSales;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getInvoiceCount() {
        return invoiceCount;
    }

    public void setInvoiceCount(int invoiceCount) {
        this.invoiceCount = invoiceCount;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public void setTotalSales(BigDecimal totalSales) {
        this.totalSales = totalSales;
    }

    @Override
    public String toString() {
        return "CustomerPattern{" +
               "customerType=" + customerType +
               ", gender=" + gender +
               ", invoiceCount=" + invoiceCount +
               ", totalSales=" + totalSales +
               '}';
    }
}
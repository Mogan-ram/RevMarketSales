package Model;

import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Invoices {
    private String invoice_id;
    private int branch_id;
    private int customer_id;
    private LocalDateTime dateTime;
    private String payment_method;
    private BigDecimal total_sales;
    private BigDecimal tax;
    private BigDecimal cogs;
    private BigDecimal grossMarginPercentage;
    private BigDecimal gross_income;

    public Invoices() {
    }

    public Invoices(String invoice_id, int branch_id, int customer_id, LocalDateTime dateTime,
                    String payment_method, BigDecimal total_sales, BigDecimal tax,
                    BigDecimal cogs, BigDecimal grossMarginPercentage, BigDecimal gross_income) {
        this.invoice_id = invoice_id;
        this.branch_id = branch_id;
        this.customer_id = customer_id;
        this.dateTime = dateTime;
        this.payment_method = payment_method;
        this.total_sales = total_sales;
        this.tax = tax;
        this.cogs = cogs;
        this.grossMarginPercentage = grossMarginPercentage;
        this.gross_income = gross_income;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public BigDecimal getTotal_sales() {
        return total_sales;
    }

    public void setTotal_sales(BigDecimal total_sales) {
        this.total_sales = total_sales;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getCogs() {
        return cogs;
    }

    public void setCogs(BigDecimal cogs) {
        this.cogs = cogs;
    }

    public BigDecimal getGrossMarginPercentage() {
        return grossMarginPercentage;
    }

    public void setGrossMarginPercentage(BigDecimal grossMarginPercentage) {
        this.grossMarginPercentage = grossMarginPercentage;
    }

    public BigDecimal getGross_income() {
        return gross_income;
    }

    public void setGross_income(BigDecimal gross_income) {
        this.gross_income = gross_income;
    }

    @Override
    public String toString() {
        return "Invoices{" +
                "invoice_id='" + invoice_id + '\'' +
                ", branch_id=" + branch_id +
                ", customer_id=" + customer_id +
                ", dateTime=" + dateTime +
                ", payment_method='" + payment_method + '\'' +
                ", total_sales=" + total_sales +
                ", tax=" + tax +
                ", cogs=" + cogs +
                ", grossMarginPercentage=" + grossMarginPercentage +
                ", gross_income=" + gross_income +
                '}';
    }
}
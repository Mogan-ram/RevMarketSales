package Model;

/**
 * Represents an invoice item in the supermarket database.
 */
public class InvoiceItem {
    private int invoice_item_id;
    private String invoice_id;
    private int product_id;
    private int quantity;

    public InvoiceItem() {
    }

    /**
     * Constructs an InvoiceItem with the specified details.
     *
     * @param invoice_item_id the unique identifier of the invoice item
     * @param invoice_id     the ID of the associated invoice
     * @param product_id     the ID of the associated product
     * @param quantity       the quantity purchased
     */
    public InvoiceItem(int invoice_item_id, String invoice_id, int product_id, int quantity) {
        this.invoice_item_id = invoice_item_id;
        this.invoice_id = invoice_id;
        this.product_id = product_id;
        this.quantity = quantity;
    }

    public int getInvoice_item_id() {
        return invoice_item_id;
    }

    public void setInvoice_item_id(int invoice_item_id) {
        this.invoice_item_id = invoice_item_id;
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "InvoiceItem{" +
                "invoice_item_id=" + invoice_item_id +
                ", invoice_id='" + invoice_id + '\'' +
                ", product_id=" + product_id +
                ", quantity=" + quantity +
                '}';
    }
}
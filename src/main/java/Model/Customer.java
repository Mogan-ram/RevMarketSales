package Model;

/**
 * Represents a customer in the supermarket database.
 */
public class Customer {
    private int customer_id;
    private CustomerType customer_type;
    private Gender gender;

    public Customer() {
    }

    /**
     * Constructs a Customer with the specified details.
     *
     * @param customer_id   the unique identifier of the customer
     * @param customer_type the type of customer (MEMBER or NORMAL)
     * @param gender       the gender of the customer
     */
    public Customer(int customer_id, CustomerType customer_type, Gender gender) {
        this.customer_id = customer_id;
        this.customer_type = customer_type;
        this.gender = gender;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public CustomerType getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(CustomerType customer_type) {
        this.customer_type = customer_type;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_type=" + customer_type +
                ", gender=" + gender +
                '}';
    }
}
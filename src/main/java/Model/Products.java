package Model;


public class Products {
    private int product_id;
    private String product_line;
    private double unit_price;
    private double product_rating;

    public Products() {
    }

    public Products(int product_id, String product_line, double unit_price, double product_rating) {
        this.product_id = product_id;
        this.product_line = product_line;
        this.unit_price = unit_price;
        this.product_rating = product_rating;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_line() {
        return product_line;
    }

    public void setProduct_line(String product_line) {
        this.product_line = product_line;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public double getProduct_rating() {
        return product_rating;
    }

    public void setProduct_rating(double product_rating) {
        this.product_rating = product_rating;
    }

    @Override
    public String toString() {
        return "Products{" +
                "product_id=" + product_id +
                ", product_line='" + product_line + '\'' +
                ", unit_price=" + unit_price +
                ", product_rating=" + product_rating +
                '}';
    }
}
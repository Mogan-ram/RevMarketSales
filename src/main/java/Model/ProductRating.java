package Model;

public class ProductRating {
    private String productLine;
    private double averageRating;

    public ProductRating() {
    }

    public ProductRating(String productLine, double averageRating) {
        this.productLine = productLine;
        this.averageRating = averageRating;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public String toString() {
        return "ProductRating{" +
               "productLine='" + productLine + '\'' +
               ", averageRating=" + averageRating +
               '}';
    }
}
package Model;

public class Branch {
    private int branch_id;
    private String branch_name;
    private String city;


    public Branch() {
    }

    public Branch(int branch_id, String branch_name, String city) {
        this.branch_id = branch_id;
        this.branch_name = branch_name;
        this.city = city;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Branch{" +
                "branch_id=" + branch_id +
                ", branch_name='" + branch_name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}

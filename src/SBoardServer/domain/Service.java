package SBoardServer.domain;

public class Service {

    private int id;
    private String name;
    private double price;
    private int employeeId;
    private int categories_id;

    public Service(int id, String name, double price, int employeeId, int categories_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.employeeId = employeeId;
        this.categories_id = categories_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getCategories_id() {
        return categories_id;
    }
}

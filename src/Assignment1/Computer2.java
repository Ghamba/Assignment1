package Assignment1;

public class Computer2 {
    private String brand;
    private String model;
    private int serialNumber;
    private double price;

    public Computer2(String brand, String model, int serialNumber, double price) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Brand: " + brand + "\nModel: " + model + "\nSN: " + serialNumber + "\nPrice: $" + price;
    }
}

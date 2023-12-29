package Assignment1;

public class Computer1 {
    private String brand;
    private String model;
    private long serialNumber;
    private double price;
    private static int numberOfCreatedComputers = 0;

    // Constructor
    public Computer1(String brand, String model, long serialNumber, double price) {
        this.brand = brand;
        this.model = model;
        this.serialNumber = serialNumber;
        this.price = price;
        numberOfCreatedComputers++;
    }

    // Accessors and Mutators
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

    public long getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(long serialNumber) {
        this.serialNumber = serialNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    // Display information
    @Override
	public String toString() {
		return "Computer [brand=" + brand + ", model=" + model + ", serialNumber=" + serialNumber + ", price=" + price
				+ "]";
	}

    
    

    // Find number of created computers
    public static int findNumberOfCreatedComputers() {
        return numberOfCreatedComputers;
    }
    

	// Equals method for comparing two Computer objects
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Computer1 computer = (Computer1) obj;
        return brand.equals(computer.brand) && model.equals(computer.model) && Double.compare(computer.price, price) == 0;
    }
}

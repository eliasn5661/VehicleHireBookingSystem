package nofalelias.babcock.VehicleHireBookingSystem.model;

import javax.persistence.*;

@Entity
public class Vehicle {

    @Id
    private String registrationNumber;
    private String make;
    private String model;
    private String fuelType;
    @Column
    private int vehicleTypeId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicleTypeId", referencedColumnName="ID",insertable=false, updatable=false)
    private DailyRate dailyRate;

    public Vehicle() {
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getPricePerDay() {
        return dailyRate.getPricePerDay();
    }

    public String getVehicleType() {
        return dailyRate.getVehicleType();
    }

    public int getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(int vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }
}

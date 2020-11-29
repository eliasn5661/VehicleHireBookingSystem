package nofalelias.babcock.VehicleHireBookingSystem.model;

import java.time.LocalDate;

public class VehicleHireBooking {

    private int id;

    private Customer customer;
    private Vehicle vehicle;
    private LocalDate hireDate;
    private LocalDate returnDate;

    public VehicleHireBooking() {
    }

    public VehicleHireBooking(Customer customer, Vehicle vehicle, LocalDate hireDate, LocalDate returnDate) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.hireDate = hireDate;
        this.returnDate = returnDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}

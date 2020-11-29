package nofalelias.babcock.VehicleHireBookingSystem.model;

import nofalelias.babcock.VehicleHireBookingSystem.common.CustomerType;
import nofalelias.babcock.VehicleHireBookingSystem.common.DateUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookedVehicles {

    List<VehicleHireBooking> vehicelsBooked = new ArrayList<>();

    public BookedVehicles() {
    }

    public synchronized boolean addBooking(Vehicle vehicle, Customer customer, LocalDate hireDate, LocalDate returnDate) {
        VehicleHireBooking vehicleHireBooking = new VehicleHireBooking(customer,vehicle,hireDate,returnDate);

        long found = vehicelsBooked.stream().filter(vhb -> isVehicleAlreadyBooked(vhb, vehicle, customer, hireDate, returnDate)).count();
        if(found > 0) {
            return false;
        }
        vehicelsBooked.add(vehicleHireBooking);
        return true;
    }

    /**
     * is the request vehicle has already booked for the given date
     * @param vehicle
     * @param date
     * @return
     */
    public boolean isVehicleBooked(Vehicle vehicle, LocalDate date) {
        Optional<VehicleHireBooking> first = vehicelsBooked.stream().filter(v -> v.getVehicle().getRegistrationNumber().equals(vehicle.getRegistrationNumber()))
                .filter(v -> v.getHireDate().isBefore(date) || v.getHireDate().equals(date))
                .filter(v -> v.getReturnDate().isAfter(date) || v.getReturnDate().equals(date))
                .findFirst();
        return first.isPresent();
    }

    /**
     * This static method will return true if the requested booking is already exists
     * @param vehicleHireBooking
     * @param vehicle
     * @param customer
     * @param hireDate
     * @param returnDate
     * @return
     */
    public static boolean isVehicleAlreadyBooked(VehicleHireBooking vehicleHireBooking, Vehicle vehicle, Customer customer, LocalDate hireDate, LocalDate returnDate) {
        if(!vehicleHireBooking.getVehicle().getRegistrationNumber().equals(vehicle.getRegistrationNumber())) {
            return false;
        }

        if((customer.getId() != vehicleHireBooking.getCustomer().getId()) ||  customer.getType().equals("company")) {
            // customers of type "company" can hire many vehicles in the same period of time
            return false;
        }

        return DateUtils.isOverLaped(vehicleHireBooking.getHireDate(), vehicleHireBooking.getReturnDate(), hireDate, returnDate);
    }
}

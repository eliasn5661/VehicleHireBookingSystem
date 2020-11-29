package nofalelias.babcock.VehicleHireBookingSystem.services;

import nofalelias.babcock.VehicleHireBookingSystem.exceptions.CustomerNotFoundException;
import nofalelias.babcock.VehicleHireBookingSystem.exceptions.VehicleNotFoundException;
import nofalelias.babcock.VehicleHireBookingSystem.model.BookedVehicls;
import nofalelias.babcock.VehicleHireBookingSystem.model.Customer;
import nofalelias.babcock.VehicleHireBookingSystem.model.Vehicle;
import nofalelias.babcock.VehicleHireBookingSystem.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CustomerService customerService;

    BookedVehicls bookedVehicls = new BookedVehicls();

    VehicleService() {

    }

    public List<Vehicle> getAllAvailableVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicleRepository.findAll().forEach(vehicles::add);
        LocalDate today = LocalDate.now();
        List<Vehicle> collect = vehicles.stream().filter(v -> !bookedVehicls.isVehicleBooked(v, today)).collect(Collectors.toList());
        return collect;
    }

    public Vehicle getVehicle(String regNumber) throws VehicleNotFoundException {
        return vehicleRepository.findById(regNumber).orElseThrow(VehicleNotFoundException::new);
    }

    public void addVehicle(Vehicle vehicle) {

        vehicleRepository.save(vehicle);
    }

    public void updateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    public synchronized  String bookVehicle(Integer customerId, String vehcileRegNumber, String hireDateStr, String returnDateStr) {
        LocalDate hireDate = null;
        LocalDate returnDate = null;
        try {
            hireDate = getLocalDate(hireDateStr);
            returnDate = getLocalDate(returnDateStr);
            if(hireDate.isAfter(returnDate)) {
                return "Hire date is after return date";
            }
            LocalDate today = LocalDate.now();
            if(hireDate.isBefore(today)) {
                return "Hire date must not be in the past";
            }
        } catch (Exception e) {
            return "Invalid hire date or return date  - format must be dd/MM/yyyyy";
        }
        Vehicle vehicle = null;
        try {
            vehicle = this.getVehicle(vehcileRegNumber);;
        } catch ( VehicleNotFoundException e) {
            return "Car not found ";
        }
        Customer customer= null;
        try {
            customer = customerService.findCustomer(customerId);
        } catch (CustomerNotFoundException e) {
           return "Customer Id"+ customerId+ " not found";
        }

        boolean result = bookedVehicls.addBooking(vehicle, customer, hireDate, returnDate);
        if(result) {
            return "Vehicle has been booked for the request dates";
        }

        return "Vehicle is not available";
    }

    public String getCostOfHire(String vehcileRegNumber, String hireDateStr, String returnDateStr) {
        int numDays = 0;
        try {
            LocalDate hireDate = getLocalDate(hireDateStr);
            LocalDate returnDate = getLocalDate(returnDateStr);
            if(hireDate.isAfter(returnDate)) {
                return "Hire date is after return date";
            }
            LocalDate today = LocalDate.now();
            if(hireDate.isBefore(today)) {
                return "Hire date must not be in the past";
            }
            numDays = Period.between(hireDate, returnDate).getDays();
            if(numDays == 0) {
                // scenario where it will be returned on the same day, charge one day rental
                numDays = 1;
            }
        } catch (Exception e) {
            return "Invalid hire date or return date  - format must be dd/MM/yyyyy";
        }
        try {
            Vehicle vehicle = this.getVehicle(vehcileRegNumber);
            Double pricePerDay = vehicle.getPricePerDay();
            StringBuilder builder = new StringBuilder();
            builder.append("Cost to hire ");
            builder.append(vehicle.getMake());
            builder.append(" model ");
            builder.append(vehicle.getModel() );
            builder.append( " car type ");
            builder.append(vehicle.getVehicleType());
            builder.append(" is \n");
            builder.append(String.valueOf(pricePerDay * numDays));
            return builder.toString();
        } catch ( VehicleNotFoundException e) {
            return "Car not found ";
        }

    }

    /**
     * this method will validate a date as as tring passed in the format dd/MM/yyyy
     * @param dateStr
     * @return
     */
    private LocalDate getLocalDate(String dateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        return localDate;
    }

}

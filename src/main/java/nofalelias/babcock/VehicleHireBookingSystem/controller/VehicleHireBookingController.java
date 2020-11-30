package nofalelias.babcock.VehicleHireBookingSystem.controller;

import nofalelias.babcock.VehicleHireBookingSystem.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleHireBookingController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping("/VehicleHireBooking")
    public List<String> getAvailableVehiclesForHire() {
        List<String> cars = new ArrayList<>();
        cars.add("None Avaliable");
        return cars;
    }

    @RequestMapping("/VehicleHireBooking/query")
    public String getCostOfHire(@QueryParam("vehicleRegNumber") String vehicleRegNumber, @QueryParam("hireDateStr") String hireDateStr, @QueryParam("returnDateStr") String returnDateStr) {
        return vehicleService.getCostOfHire(vehicleRegNumber, hireDateStr, returnDateStr);

    }

    @RequestMapping("/VehicleHireBooking/book")
    public String bookVehicle(@QueryParam("customerId") Integer customerId, @QueryParam("vehcileRegNumber") String vehcileRegNumber, @QueryParam("hireDateStr") String hireDateStr, @QueryParam("returnDateStr") String returnDateStr) {

        return vehicleService.bookVehicle(customerId, vehcileRegNumber, hireDateStr, returnDateStr);
    }
}

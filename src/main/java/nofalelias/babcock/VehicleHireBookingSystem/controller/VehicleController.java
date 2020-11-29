package nofalelias.babcock.VehicleHireBookingSystem.controller;

import nofalelias.babcock.VehicleHireBookingSystem.exceptions.VehicleNotFoundException;
import nofalelias.babcock.VehicleHireBookingSystem.model.Vehicle;
import nofalelias.babcock.VehicleHireBookingSystem.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @GetMapping("/")
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllAvailableVehicles();
    }

    @GetMapping("/{id}")
    public Vehicle getVehicles(@PathVariable("id") String regNumber) throws VehicleNotFoundException {
        return vehicleService.getVehicle(regNumber);
    }

    @RequestMapping(method=RequestMethod.POST,value="/Vehicle")
    public void addVehicles(@RequestBody Vehicle vehicle) {
        vehicleService.addVehicle(vehicle);
        return;
    }

    @RequestMapping(method=RequestMethod.PUT,value="/Vehicle")
    public void updateVehicles(@RequestBody Vehicle vehicle) {
        vehicleService.updateVehicle(vehicle);
        return;
    }
}

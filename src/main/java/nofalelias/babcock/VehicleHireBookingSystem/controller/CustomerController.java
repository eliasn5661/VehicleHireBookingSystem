package nofalelias.babcock.VehicleHireBookingSystem.controller;

import nofalelias.babcock.VehicleHireBookingSystem.model.Customer;
import nofalelias.babcock.VehicleHireBookingSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}

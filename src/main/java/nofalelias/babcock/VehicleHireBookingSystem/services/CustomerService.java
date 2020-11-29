package nofalelias.babcock.VehicleHireBookingSystem.services;

import nofalelias.babcock.VehicleHireBookingSystem.exceptions.CustomerNotFoundException;
import nofalelias.babcock.VehicleHireBookingSystem.model.Customer;
import nofalelias.babcock.VehicleHireBookingSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    CustomerService() {

    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        customerRepository.findAll().forEach(customers::add);
        return customers;
    }

    public Customer findCustomer(Integer id) throws CustomerNotFoundException {
        Customer customer = customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
        return customer;
    }

}

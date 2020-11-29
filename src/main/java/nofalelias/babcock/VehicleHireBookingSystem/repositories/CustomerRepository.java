package nofalelias.babcock.VehicleHireBookingSystem.repositories;

import nofalelias.babcock.VehicleHireBookingSystem.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}

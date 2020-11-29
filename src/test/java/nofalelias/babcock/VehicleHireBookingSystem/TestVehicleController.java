package nofalelias.babcock.VehicleHireBookingSystem;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.time.temporal.TemporalUnit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestVehicleController extends VehicleHireBookingSystemApplicationTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testGetAllVehiclesAfterMadeABooking() throws Exception {

        // the number of available vehicles should be 6 before any bookings , see data.sql in resourcews
        mockMvc.perform(MockMvcRequestBuilders.get("/api/")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(6)));

        // make a booking first
        LocalDate today = LocalDate.now();
        LocalDate returnDate = LocalDate.now().plusDays(3);
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();
        String todayStr = String.format("%02d", day)+"/"+String.format("%02d", month)+"/"+year;
        day = returnDate.getDayOfMonth();
        month = returnDate.getMonthValue();
        year = returnDate.getYear();
        String returnDateStr = String.format("%02d", day)+"/"+String.format("%02d", month)+"/"+year;

        // Book N20FAL vehicle
        mockMvc.perform(MockMvcRequestBuilders.get("/api/VehicleHireBooking/book?customerId=1&vehcileRegNumber=N20FAL&hireDateStr="+todayStr+"&returnDateStr="+returnDateStr)).andExpect(status().isOk());

        // the avaliable vehicles should be reduced by 1
        mockMvc.perform(MockMvcRequestBuilders.get("/api/")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(5)));
    }


    @Test
    public void testCalculateCostOfHiringVehicle() throws Exception {
        LocalDate today = LocalDate.now();
        LocalDate returnDate = LocalDate.now().plusDays(3);
        int day = today.getDayOfMonth();
        int month = today.getMonthValue();
        int year = today.getYear();
        String todayStr = String.format("%02d", day)+"/"+String.format("%02d", month)+"/"+year;
        day = returnDate.getDayOfMonth();
        month = returnDate.getMonthValue();
        year = returnDate.getYear();
        String returnDateStr = String.format("%02d", day)+"/"+String.format("%02d", month)+"/"+year;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/VehicleHireBooking/query?vehicleRegNumber=N20FAL&hireDateStr="+todayStr+"&returnDateStr="+returnDateStr)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Cost to hire Mercedes model E230 car type Small is \n75.0"));


    }
}

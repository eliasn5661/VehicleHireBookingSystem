package nofalelias.babcock.VehicleHireBookingSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@SpringBootApplication
@EnableSwagger2
public class VehicleHireBookingSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(VehicleHireBookingSystemApplication.class, args);

	}

	@Bean
	public Docket swaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/api/*"))
				.apis(RequestHandlerSelectors.basePackage("nofalelias.babcock.VehicleHireBookingSystem"))
				.build()
				.apiInfo(apiDetails());
	}

	private ApiInfo apiDetails() {
		return new ApiInfo(
				"Babcock Technical Test - Vehicle Hire Booking System",
				"A vehicle hire company has a number of cars and vans, and a variety of customers. \n The hire company has many vehicles of the same make and model. Vehicles are hired on a daily basis, rather than hourly",
				"1.0",
				"Free to use",
				new springfox.documentation.service.Contact("Nofal Elias","","eliasn@kyokushin.co.uk"),
				"Free",
				"None",
				Collections.emptyList());
	}

}

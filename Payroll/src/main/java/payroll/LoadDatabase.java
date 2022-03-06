package payroll;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository) {
	  
	Faker faker = new Faker(new Locale("fr"));
	
    return args -> {
    	for (int i = 0; i < 5 ; i++) {
    		log.info("Saving Employee : " + employeeRepository.save(
    				new Employee(
    						faker.name().firstName(),
    						faker.name().lastName(),
    						faker.job().position()
    						)));
		}
    	for (int i = 0; i < 20; i++) {
    		Status status;  
    		if ( i % 3 == 0 ) {
    			status = Status.COMPLETED;
    		} else {
    			status = Status.IN_PROGRESS;
    		}
    		log.info("Saving Order : " + orderRepository.save(
    				new Order(
    						faker.food().dish(),
    						status
    						)));
		}
      
    };
  }
}
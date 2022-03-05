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
  CommandLineRunner initDatabase(EmployeeRepository repository) {

	Faker faker = new Faker(new Locale("fr"));
    return args -> {
    	for (int i = 0; i < 3 ; i++) {
    		log.info("Preloading " + repository.save(
    				new Employee(
    						faker.name().firstName(),
    						faker.name().lastName(),
    						faker.job().position()
    						)));
		}
      
    };
  }
}
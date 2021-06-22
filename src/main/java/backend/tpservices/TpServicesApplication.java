package backend.tpservices;

import backend.tpservices.Models.Embedded.Contact;
import backend.tpservices.Models.Product;
import backend.tpservices.Models.Rating;
import backend.tpservices.Models.UserTypes.Client;
import backend.tpservices.Services.ClientService;
import backend.tpservices.Services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TpServicesApplication {

	private static final Logger log = LoggerFactory.getLogger(TpServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TpServicesApplication.class, args);
	}

	@EnableWebSecurity
	@Configuration
	class WebSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable();
					/*.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
					.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/login").permitAll()
					.anyRequest().authenticated();*/

			http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
					.and().csrf().ignoringAntMatchers("/h2-console/**")
					.and().headers().frameOptions().sameOrigin();
		}


	}

	@Bean
	public CommandLineRunner demo(ClientService clientService, ProductService productService){
		return (args -> { // db moze byt naplnena tu

			clientService.insertClientToDb(
					new Client(new Contact("Bojack",
										   "Horseman",
									    "+421452654280",
									          "checkni.to@dost.cool",
										   "Jablcko")));

			clientService.insertClientToDb(
					new Client(new Contact("Norika",
											"Mojsejova",
							             "+421 123 123 123",
											   "nevolajtemi@nepistemi.maily",
											"ding dong")));

			List<Rating> ratings = new ArrayList<>();
			ratings.add(new Rating("Ta ten najlepsi", 5));

			productService.insertProductToDb(new Product(
					"Norkovany kozuch",
					500000.0,
					"Kozuch z norky",
					ratings
					));

//			Client client = new Client();
//			Contact contact = new Contact("Bojack", "Horseman","+421 452 654 280", "checkni.to@dost.cool", "Jablcko");
//			client.setContact(contact);
//			clientRepo.save(client);

			//repository.save(new Client("Bojack", "Horsemano"));
//			repository.save(new Client("Mr.", "PeanutButter"));
/*
			log.info("Clients found with findAll():");

			for (Client client : repository.findAll()) {
				log.info(client.toString());
			}
			log.info("--------------------------------");
			Client client = repository.findByFirstName("Bojack");
			log.info(client.toString());*/
		});
	}
}

package backend.tpservices;

import Config.Constants;
import backend.tpservices.Modules.Address.Address;
import backend.tpservices.Modules.Contact.Contact;
import backend.tpservices.Modules.Order.Order;
import backend.tpservices.Modules.Order.OrderService;
import backend.tpservices.Modules.Product.Product;
import backend.tpservices.Modules.Client.Client;
import backend.tpservices.Modules.Company.Company;
import backend.tpservices.Modules.Client.ClientService;
import backend.tpservices.Modules.Company.CompanyService;
import backend.tpservices.Modules.Product.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.*;

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
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers("/**");
		}

//		@Override
//		protected void configure(HttpSecurity http) throws Exception {
//			http.csrf().disable()
//					.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
//					.authorizeRequests()
//					.antMatchers(HttpMethod.POST, "/login").permitAll()
//					.anyRequest().authenticated();
//
//			http.authorizeRequests().antMatchers("/h2-console/**").permitAll()
//					.and().csrf().ignoringAntMatchers("/h2-console/**")
//					.and().headers().frameOptions().sameOrigin();
//		}


	}

	@Bean
	public CommandLineRunner demo(ClientService clientService, CompanyService companyService, ProductService productService, OrderService orderService){
		return (args -> { // db moze byt naplnena tu

			Client client = new Client(new Contact("Bojack",
					"Horseman",
					"+421452654280",
					"checkni.to@dost.cool",
					"Jablcko"));

			clientService.insertClientToDb(client);

			clientService.insertClientToDb(
					new Client(new Contact("Norika",
											"Mojsejova",
							             "+421 123 123 123",
											   "nevolajtemi@nepistemi.maily",
											"ding dong")));

			companyService.insertCompanyToDb(
					new Company("Dedoles", Constants.CompanyType.KS,"00000000",
								"0000000000","SK0000000000",new Date(),
								new Address("ulica","cislo","mesto", "00000")
					)
			);

			//productService.insertProductToDb(product);
			List<Product> products = new ArrayList<>();

			for(int i=0; i<5; i++){
				products.add(
						new Product(
								Constants.ProductCategory.clothes,
								Constants.ProductState.inStorage,
								"Norkovany kozuch" + i,
								(double) i,
								"Kozuch z norky"
						)
				);
			}

			orderService.insertOrderToDb(
					new Order(
							client,
							Order.PaymentType.online,
							products//new ArrayList<>(Collections.singletonList(product))
					)
			);




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

package backend.tpservices;

import Config.Constants.*;
import backend.tpservices.Modules.Address.*;
import backend.tpservices.Modules.Contact.*;
import backend.tpservices.Modules.Order.*;
import backend.tpservices.Modules.Product.*;
import backend.tpservices.Modules.Client.*;
import backend.tpservices.Modules.Company.*;
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

	public void insertStrasneVelaCompanies(CompanyService companyService) {

		companyService.insertAllCompaniesToDb(Arrays.asList(
				new Company("Dedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Aedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Cedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Bedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Dedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Aedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Cedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Bedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Dedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Aedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Cedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Bedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Dedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Aedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Cedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				),
				new Company("Bedoles", CompanyType.KS, "00000000",
						"0000000000", "SK0000000000", new Date(),
						new Address("ulica", "cislo", "mesto", "00000")
				)
		));
	}

	@Bean
	public CommandLineRunner demo(ClientService clientService, CompanyService companyService, ProductService productService, OrderService orderService){
		return (args -> {

			Client client = new Client(new Contact("Bojack",
					"Horseman",
					"+421452654280",
					"checkni.to@dost.cool",
					"Jablcko"));

			clientService.insertClientToDb(client);

			clientService.insertClientToDb(
					new Client(new Contact("Norika",
											"Mojsejova",
							             "+421123123123",
											   "nevolajtemi@nepistemi.maily",
											"aabbCC00")));

			insertStrasneVelaCompanies(companyService);

			//productService.insertProductToDb(product);
			List<Product> products = new ArrayList<>();

			for(int i=0; i<5; i++){
				products.add(
						new Product(
								ProductCategory.clothes,
								ProductState.inStorage,
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

		});
	}
}

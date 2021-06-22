package backend.tpservices;

import Config.Constants;
import backend.tpservices.Models.Embedded.Address;
import backend.tpservices.Models.Embedded.Contact;
import backend.tpservices.Models.UserTypes.Client;
import backend.tpservices.Models.UserTypes.Company;
import backend.tpservices.Services.ClientService;
import backend.tpservices.Services.CompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class TpServicesApplication {

	private static final Logger log = LoggerFactory.getLogger(TpServicesApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(TpServicesApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClientService clientService, CompanyService companyService){
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

			companyService.insertCompanyToDb(
					new Company("Dedoles", Constants.CompanyType.KS,"00000000",
								"0000000000","SK0000000000",new Date(),
								new Address("ulica","cislo","mesto", "00000")
					)
			);
//
//			System.out.println(clientService);
//			System.out.println(companyService);
//			companyService.getAllCompanies();
//			Client client = new Client();
//			Contact contact = new Contact("Bojack", "Horseman","+421 452 654 280", "checkni.to@dost.cool", "Jablcko");
//			client.setContact(contact);
//			clientRepo.save(client);

			//repository.save(new Client("Bojack", "Horsemano"));
//			repository.save(new Client("Mr.", "PeanutButter"));

//			log.info("Clients found with findAll():");
//
//			for (Client client : repository.findAll()) {
//				log.info(client.toString());
//			}
//			log.info("--------------------------------");
//			Client client = repository.findByFirstName("Bojack");
//			log.info(client.toString());
		});
	}
}

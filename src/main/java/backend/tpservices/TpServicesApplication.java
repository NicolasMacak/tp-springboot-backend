package backend.tpservices;

import backend.tpservices.Models.Embedded.Contact;
import backend.tpservices.Models.UserTypes.Client;
import backend.tpservices.Repositories.ClientRepository;
import backend.tpservices.Repositories.ContactRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TpServicesApplication {

	private static final Logger log = LoggerFactory.getLogger(TpServicesApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TpServicesApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClientRepository clientRepo){
		return (args -> { // db moze byt naplnena tu
			Client client = new Client();
			Contact contact = new Contact("Bojack", "Horseman","+421 452 654 280", "checkni.to@dost.cool", "Jablcko");
			client.setContact(contact);

			clientRepo.save(client);

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

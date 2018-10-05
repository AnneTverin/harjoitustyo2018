package fi.training.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.training.bookstore.domain.Book;
import fi.training.bookstore.domain.BookRepository;
import fi.training.bookstore.domain.Category;
import fi.training.bookstore.domain.CategoryRepository;
import fi.training.bookstore.domain.User;
import fi.training.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Kauno"));
			crepository.save(new Category("Jännitys"));
			crepository.save(new Category("Lapset"));
			
			repository.save(new Book("Ernest Hemigway", "A Farewell to Arms", "1232323-21", 1929, 29.90, crepository.findByName("Kauno").get(0)));
			repository.save(new Book("Agatha Christie", "Neiti Lemon erehtyy", "9789510421796", 1955, 21.90, crepository.findByName("Jännitys").get(0)));	
			repository.save(new Book("Mauri Kunnas", "Suuri urheilukirja", "9511075187", 2018, 17.90, crepository.findByName("Lapset").get(0)));
			
			//Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}	
}



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

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Kauno"));
			crepository.save(new Category("Jännitys"));
			crepository.save(new Category("Lapset"));
			
			repository.save(new Book("Ernest Hemigway", "A Farewell to Arms", "1232323-21", 1929, 29.90, crepository.findByName("Kauno").get(0)));
			repository.save(new Book("George Orwell", "Animal Farm", "2212343-5", 1945, 21.90, crepository.findByName("Kauno").get(0)));	
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}	
}



package fi.training.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.training.bookstore.domain.CategoryRepository;
import fi.training.bookstore.domain.Book;
import fi.training.bookstore.domain.BookRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository; 
	
	@Autowired
	private CategoryRepository crepository;
	
	//tarkista tämä
	@RequestMapping(value="/login")
	public String login() {	
	    return "login";
	}	
	
     @RequestMapping(value="/booklist")
     public String bookList(Model model) {	
     model.addAttribute("books", repository.findAll());
         return "booklist";   
    }

     @RequestMapping(value="/books")
     public @ResponseBody List<Book> bookListRest() {	
         return (List<Book>) repository.findAll();
     }    
     
     @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
     public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
     	return repository.findById(bookId);
     }     
     //@PreAuthorize("hasAuthority('ADMIN')")
	 @RequestMapping(value = "/add")
	    public String addBook(Model model){
	    	model.addAttribute("book", new Book());
	    	model.addAttribute("categorys", crepository.findAll());
	    //mennään addbook html sivulle   ? Pitääkö tämä olla vain return: book 
	    	return "addbook";
	    }   
     
	 @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public String save(Book book){
	        repository.save(book);
	        return "redirect:booklist";
	    }    
	 
	    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
	    	repository.deleteById(bookId);
	        return "redirect:../booklist";
	    }  
	    
	   // @RequestMapping(value="/book", method=RequestMethod.GET)
	    //public String greetingForm(Model model) {
	        //model.addAttribute("book", new Book());
	        //return "book";
	    //}
	    
	   
		 
	    
	    //@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	    //public String updateBook(@PathVariable("id") Long Id, Model model) {
	   // 	Book book = repository.findById(id).get();
	    //	System.out.println("update book " + book.toString());
	   // 	model.addAttribute("book", book);
	    //return "updateBook";
	    //} 
	
}
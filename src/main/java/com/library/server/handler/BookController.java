package com.library.server.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.server.Constant;
import com.library.server.models.Book;
import com.library.server.models.BookRepository;

@Controller
@RequestMapping(path="/book")
public class BookController {
	private Constant constant = new Constant();

	@Autowired
    private BookRepository bookRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Integer addNewBook (@RequestBody Book book) {
		if (
			book.getIsbn() == null ||
			book.getMark() == null ||
			book.getName() == null ||
			book.getAmount() == null
		) {
			return Constant.Bad_Request;
		}

		if (book.getInventory() == null) {
			book.setInventory(book.getAmount());
		}

		bookRepository.save(book);
		return Constant.HTTP_OK;
	}

	// @RequestMapping(value = "delete", method = RequestMethod.POST)
	// public @ResponseBody Integer deleteBook(@RequestBody  book) {
	// 	if (book.getIsbn() != null) {
	// 		return bookRepository.delete();
	// 	}
	// 	return
	// }

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}
}
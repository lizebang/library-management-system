package com.library.server.handler;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.library.server.Constant;
import com.library.server.models.Book;
import com.library.server.models.BookRepository;

@Controller
@RequestMapping(path="/book")
public class BookController {
	
	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addNewBook (@RequestParam String isbn, @RequestParam String mark,
	@RequestParam String name, @RequestParam String tag, @RequestParam String author, @RequestParam String introduction,
	@RequestParam Integer amount, @RequestParam Integer inventory, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		if ((Integer)request.getSession().getAttribute(Constant.IsAdmin) != Constant.AdminUser) {
			map.put(Constant.Status, Constant.Permission_Denied);
			return map;
		}
	
		if (isbn.trim().equals("") || mark.trim().equals("") || name.trim().equals("") || amount < 0) {
			map.put(Constant.Status, Constant.Bad_Request);
			return map;
		}
	
		if (bookRepository.findByIsbn(isbn) != null) {
			map.put(Constant.Status, Constant.Repeated);
			return map;
		}
	
		if (inventory > amount) {
			inventory = amount;
		}
	
		Book book = new Book(isbn, mark, name, tag, author, introduction, amount, inventory);
		bookRepository.save(book);
	
		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteBook(@RequestParam String isbn, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		if ((Integer)request.getSession().getAttribute(Constant.IsAdmin) != Constant.AdminUser) {
			map.put(Constant.Status, Constant.Permission_Denied);
			return map;
		}
	
		bookRepository.deleteBookByIsbn(isbn);
	
		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}

	@RequestMapping(value = "/getbyisbn", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getByIsbn(@RequestParam String isbn) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		Book book = bookRepository.findByIsbn(isbn);
		if (book != null) {
			map.put(Constant.Status, Constant.HTTP_OK);
			map.put(Constant.Body, book);
			return map;
		}
	
		map.put(Constant.Status, Constant.Book_Not_Found);
		return map;
	}

	@RequestMapping(value = "/getbykeyword", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getByKeyword(@RequestParam String keyword, @RequestParam Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		Page<Book> books = bookRepository.findByKeyword(keyword, new PageRequest(page-1, Constant.Page_Size));
		if (books != null) {
			map.put(Constant.Status, Constant.HTTP_OK);
			map.put(Constant.Body, books);
			return map;
		}
	
		map.put(Constant.Status, Constant.Book_Not_Found);
		return map;
	}

	@RequestMapping(value = "/getfuzzy", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getFuzzy(@RequestParam String keyword, @RequestParam Integer page) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		Page<Book> books = bookRepository.findFuzzy(keyword, new PageRequest(page-1, Constant.Page_Size));
		if (books != null) {
			map.put(Constant.Status, Constant.HTTP_OK);
			map.put(Constant.Body, books);
			return map;
		}
	
		map.put(Constant.Status, Constant.Book_Not_Found);
		return map;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getAllBooks() {
		Map<String, Object> map = new HashMap<String, Object>();
	
		Iterable<Book> books = bookRepository.findAll();
		if (books != null) {
			map.put(Constant.Status, Constant.HTTP_OK);
			map.put(Constant.Body, books);
			return map;
		}
	
		map.put(Constant.Status, Constant.Book_Not_Found);
		return map;
	}
}

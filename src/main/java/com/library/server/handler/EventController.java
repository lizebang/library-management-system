// package com.library.server.handler;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.ResponseBody;

// import com.library.server.models.Book;
// import com.library.server.models.BookRepository;

// @Controller
// @RequestMapping(path="/event")
// public class EventController {
//     @Autowired
//     private BookRepository bookRepository;
//     @Autowired
// 	private UserRepository userRepository;

//     @RequestMapping(value = "/add", method = RequestMethod.POST)
// 	public @ResponseBody String addNewUser (@RequestBody User user) {

// 		userRepository.save(user);
// 		return "Saved";
// 	}

// 	@RequestMapping(value = "/all", method = RequestMethod.GET)
// 	public @ResponseBody Iterable<User> getAllUsers() {
// 		return userRepository.findAll();
// 	}
// }
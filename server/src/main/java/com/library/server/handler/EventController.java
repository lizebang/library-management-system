package com.library.server.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.library.server.models.Event;
import com.library.server.models.EventRepository;
import com.library.server.models.UserRepository;

@Controller
@RequestMapping(path = "/event")
public class EventController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/borrow", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> borrowBook(@RequestParam String isbn,
            HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        String phone = (String) request.getSession().getAttribute(Constant.Phone);
        if (phone == null) {
            map.put(Constant.Status, Constant.Not_Login);
            return map;
        }

        Integer amount = (Integer) request.getSession().getAttribute(Constant.Amount);
        if (Constant.Max_Margin - amount == 0) {
            map.put(Constant.Status, Constant.No_Margin);
            return map;
        }

        Book book = bookRepository.findByIsbn(isbn);
        if (book.getInventory().equals(0)) {
            map.put(Constant.Status, Constant.Zore_Inventory);
            return map;
        }

        int bookUpdateTimes =
                bookRepository.updateInventory(book.getInventory() - 1, book.getIsbn());
        int userUpdateTimes = userRepository.updateAmount(amount + 1, phone);
        if (bookUpdateTimes != userUpdateTimes) {
            map.put(Constant.Status, Constant.Error_Borrow);
            return map;
        }

        eventRepository
                .save(new Event(phone, isbn, book.getName(), book.getTag(), book.getAuthor()));
        request.getSession().setAttribute(Constant.Amount, amount + 1);

        map.put(Constant.Status, Constant.HTTP_OK);
        return map;
    }

    @RequestMapping(value = "/return", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> returnBook(@RequestParam String isbn,
            HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        String phone = (String) request.getSession().getAttribute(Constant.Phone);
        if (phone == null) {
            map.put(Constant.Status, Constant.Not_Login);
            return map;
        }

        Integer amount = (Integer) request.getSession().getAttribute(Constant.Amount);

        Book book = bookRepository.findByIsbn(isbn);

        int bookUpdateTimes =
                bookRepository.updateInventory(book.getInventory() + 1, book.getIsbn());
        int userUpdateTimes = userRepository.updateAmount(amount - 1, phone);
        if (bookUpdateTimes != userUpdateTimes) {
            map.put(Constant.Status, Constant.Error_Return);
            return map;
        }

        eventRepository.deleteByPhoneAndIsbn(phone, isbn);
        request.getSession().setAttribute(Constant.Amount, amount - 1);

        map.put(Constant.Status, Constant.HTTP_OK);
        return map;
    }

    @RequestMapping(value = "/getbyphone", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getByPhone(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();

        String phone = (String) request.getSession().getAttribute(Constant.Phone);
        if (phone == null) {
            map.put(Constant.Status, Constant.Not_Login);
            return map;
        }

        Integer amount = (Integer) request.getSession().getAttribute(Constant.Amount);

        Page<Event> events =
                eventRepository.findUserByPhone(phone, new PageRequest(0, Constant.Page_Size));
        if (events != null) {
            map.put(Constant.Status, Constant.HTTP_OK);
            List<Object> objects = new ArrayList<Object>();
            for (Event event : events) {
                objects.add(event.toMap());
            }
            map.put(Constant.Body, objects);
            map.put(Constant.TotalPages, events.getTotalPages());
            return map;
        }

        map.put(Constant.Status, Constant.User_Not_Found);
        return map;
    }
}

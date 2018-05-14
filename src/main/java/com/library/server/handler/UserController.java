package com.library.server.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.library.server.Constant;
import com.library.server.models.User;
import com.library.server.models.UserRepository;
import com.library.server.util.MD5;
import com.library.server.util.Checker;

@Controller
@RequestMapping(path="/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> addUser (@RequestParam String name,
	@RequestParam Integer sex, @RequestParam String phone, @RequestParam String password) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (sex != Constant.SexNo && sex != Constant.SexMale && sex != Constant.SexFemale) {
			map.put(Constant.Status, Constant.Bad_Request);
			return map;
		}
		if (!Checker.isPhone(phone)) {
			map.put(Constant.Status, Constant.Bad_Request);
			return map;
		}
		if (userRepository.findByPhone(phone) != null) {
			map.put(Constant.Status, Constant.Repeated);
			return map;
		}
		User user = new User(name, sex, phone, MD5.GetMD5Code(password));
		user.setIsAdmin(Constant.NormalUser);
		user.setAmount(0);
		userRepository.save(user);
		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteUser (@RequestParam Long id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if ((Integer)request.getSession().getAttribute(Constant.IsAdmin) != Constant.AdminUser) {
			map.put(Constant.Status, Constant.Permission_Denied);
			return map;
		}
		userRepository.deleteById(id);
		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}

	@RequestMapping(value = "/getbyname", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUsersByName (@RequestParam String name, @RequestParam int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<User> users = userRepository.findUserByName(name, new PageRequest(page-1, Constant.Page_Size));
		if (users != null) {
			map.put(Constant.Status, Constant.HTTP_OK);
			map.put(Constant.Body, users);
			return map;
		}
		map.put(Constant.Status, Constant.User_Not_Found);
		return map;
	}

	@RequestMapping(value = "/getbyid", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getUsersById (@RequestParam Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		Optional<User> user = userRepository.findById(id);
		if (user != null) {
			map.put(Constant.Status, Constant.HTTP_OK);
			map.put(Constant.Body, user);
			return map;
		}
		map.put(Constant.Status, Constant.User_Not_Found);
		return map;
	}

	@RequestMapping(value = "/all", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllUsers(@RequestParam int page) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<User> users = userRepository.findAll(new PageRequest(page-1, Constant.Page_Size));
		if (users != null) {
			map.put(Constant.Status, Constant.HTTP_OK);
			map.put(Constant.Body, users);
			return map;
		}
		map.put(Constant.Status, Constant.User_Not_Found);
		return map;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> login(@RequestParam String phone, @RequestParam String password, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (!Checker.isPhone(phone)) {
			map.put(Constant.Status, Constant.Bad_Request);
			return map;
		}
		User user = userRepository.findByPhone(phone);
		if (user == null) {
			map.put(Constant.Status, Constant.User_Not_Found);
			return map;
		}
		if (!user.getPassword().equals(MD5.GetMD5Code(password))) {
			map.put(Constant.Status, Constant.Login_Fail);
			return map;
		}
		HttpSession session = request.getSession();
		session.setAttribute(Constant.Name, user.getName());
		session.setAttribute(Constant.Phone, user.getPhone());
		session.setAttribute(Constant.IsAdmin, user.getIsAdmin());
		session.setAttribute(Constant.Amount, user.getAmount());
		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> logout(HttpServletRequest request){	
		Map<String, Object> map = new HashMap<String, Object>();
		request.getSession().removeAttribute(Constant.Name);
		request.getSession().removeAttribute(Constant.Phone);
		request.getSession().removeAttribute(Constant.IsAdmin);
		request.getSession().removeAttribute(Constant.Amount);
		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}
}

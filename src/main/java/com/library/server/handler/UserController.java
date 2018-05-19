package com.library.server.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> resetUser (@RequestParam String name, @RequestParam String phone, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		User user = userRepository.findByPhone(phone);
		if (user == null) {
			map.put(Constant.Status, Constant.User_Not_Found);
			return map;
		}

		if (!user.getName().equals(name)) {
			map.put(Constant.Status, Constant.User_Not_Found);
			return map;
		}

		userRepository.updatePassword(MD5.GetMD5Code(Constant.ResetPassword), phone);

		request.getSession().removeAttribute(Constant.Name);
		request.getSession().removeAttribute(Constant.Phone);
		request.getSession().removeAttribute(Constant.IsAdmin);
		request.getSession().removeAttribute(Constant.Amount);

		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}

	@RequestMapping(value = "/changeinfo", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changeInfo (@RequestParam String name, @RequestParam Integer sex, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (sex != Constant.SexNo && sex != Constant.SexMale && sex != Constant.SexFemale) {
			map.put(Constant.Status, Constant.Bad_Request);
			return map;
		}

		String phone = (String)request.getSession().getAttribute(Constant.Phone);
		if (phone == null) {
			map.put(Constant.Status, Constant.Not_Login);
            return map;
		}

		userRepository.updateInfo(name, sex, phone);

		request.getSession().setAttribute(Constant.Name, name);

		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}

	@RequestMapping(value = "/changephone", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changePhone (@RequestParam String newPhone, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String phone = (String)request.getSession().getAttribute(Constant.Phone);
		if (phone == null) {
			map.put(Constant.Status, Constant.Not_Login);
            return map;
		}

		if (!Checker.isPhone(newPhone)) {
			map.put(Constant.Status, Constant.Bad_Request);
			return map;
		}

		if (userRepository.findByPhone(newPhone) != null) {
			map.put(Constant.Status, Constant.Repeated);
			return map;
		}

		userRepository.updatePhone(newPhone, phone);

		request.getSession().setAttribute(Constant.Phone, newPhone);

		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}

	@RequestMapping(value = "/changepassword", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> changePassword (@RequestParam String oldPassword, @RequestParam String newPassword, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();

		String phone = (String)request.getSession().getAttribute(Constant.Phone);
		if (phone == null) {
			map.put(Constant.Status, Constant.Not_Login);
            return map;
		}

		User user = userRepository.findByPhone(phone);
		if (!MD5.GetMD5Code(oldPassword).equals(user.getPassword())) {
			map.put(Constant.Status, Constant.Wrong_Password);
			return map;
		}

		userRepository.updatePassword(MD5.GetMD5Code(newPassword), phone);

		request.getSession().removeAttribute(Constant.Name);
		request.getSession().removeAttribute(Constant.Phone);
		request.getSession().removeAttribute(Constant.IsAdmin);
		request.getSession().removeAttribute(Constant.Amount);

		map.put(Constant.Status, Constant.HTTP_OK);
		return map;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> deleteUser (@RequestParam Long id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		Integer isAdmin = (Integer)request.getSession().getAttribute(Constant.IsAdmin);
		if (isAdmin == null) {
			map.put(Constant.Status, Constant.Not_Login);
            return map;
		}
		if (isAdmin != Constant.AdminUser) {
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
			List<Object> objects = new ArrayList<Object>();
			for (User user : users) {
				objects.add(user.toMap());
			}
			map.put(Constant.Body, objects);
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
			map.put(Constant.Status, Constant.Wrong_Password);
			return map;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute(Constant.Name, user.getName());
		session.setAttribute(Constant.Phone, user.getPhone());
		session.setAttribute(Constant.IsAdmin, user.getIsAdmin());
		session.setAttribute(Constant.Amount, user.getAmount());
		
		map.put(Constant.Status, Constant.HTTP_OK);
		map.put(Constant.Body, user.toMap());
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

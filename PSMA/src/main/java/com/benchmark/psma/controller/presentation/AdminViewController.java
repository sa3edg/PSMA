package com.benchmark.psma.controller.presentation;


import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.benchmark.psma.common.security.PasswordHashProcessor;
import com.benchmark.psma.dao.model.User;
import com.benchmark.psma.dao.model.UserRole;

@Controller
public class AdminViewController extends BaseViewController{

	private static final Logger logger = LoggerFactory.getLogger(AdminViewController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome page!");
		return "login";
	}
	
	@RequestMapping(value = "/home**", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		if (request.isUserInRole(UserRole.ADMIN_ROLE)) {
			List<UserRole> users = daoService.getNotAdminUsers();
			model.addObject("users", users);
			model.setViewName("users");
		}else if (request.isUserInRole(UserRole.DATA_ENTRY_ROLE)) {
			model.setViewName("dataEnttry/offers");
		}else if (request.isUserInRole(UserRole.MERCHANT_ROLE)) {
			model.setViewName("marchant/merchantManager");
		}
		return model;

	}
	
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public ModelAndView getUsers() {
		ModelAndView model = new ModelAndView();
		List<UserRole> users = daoService.getNotAdminUsers();
		model.addObject("users", users);
		model.setViewName("users");
		return model;

	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}
	
	//for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();
		//check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();		
			model.addObject("username", userDetail.getUsername());
		}
		
		model.setViewName("403");
		return model;
	}
	
	@RequestMapping(value = "/addUserForm**", method = RequestMethod.GET)
	public ModelAndView addUserForm() {
		ModelAndView model = new ModelAndView();
		model.addObject("user", new User());
		model.setViewName("register");
		return model;
	}
	
	@RequestMapping(value = "/error**", method = RequestMethod.GET)
	public ModelAndView error() {
		ModelAndView model = new ModelAndView();
		model.addObject("errMsg", "this is Exception.class");
		model.setViewName("error");
		return model;
	}
	
	@RequestMapping(value ="/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") @Valid User user, BindingResult result) {
		
		if(result.hasErrors()) {
			return "register";
	    }
		String hashPassword = new PasswordHashProcessor().getHashPassword(user.getPassword());
		user.setPassword(hashPassword);
		daoService.addUser(user);
		return "redirect:/getUsers";
	}
	
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(@RequestParam("id") String id) {
		daoService.deleteUser(id);
		ModelAndView model = new ModelAndView();
		List<UserRole> users = daoService.getNotAdminUsers();
		model.addObject("users", users);
		model.setViewName("users");
		return model;
	}
}

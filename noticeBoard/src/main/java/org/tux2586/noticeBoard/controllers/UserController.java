package org.tux2586.noticeBoard.controllers;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.tux2586.noticeBoard.Exceptions.UserNotFoundException;
import org.tux2586.noticeBoard.beans.EatRecord;
import org.tux2586.noticeBoard.beans.User;

@Controller
@SessionAttributes("userName")
public class UserController {
	
	private static final String LOG_PREFIX = "********************";
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	private final static String LOGIN_PAGE = "loginPage";
	private final static String ERROR_PAGE = "errorPage";
	private final static String USER_HOME_PAGE = "UserPages/userHome";
	private final static String USER_PROFILE_PAGE = "UserPages/userProfile";
	private final static String USER_EAT_HISTORY_PAGE = "UserPages/eatHistory";
	
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") User user, ModelMap model) {
		user.save();
		model.addAttribute("userName", user.getfName());
		return "UserPages/userHome";
	}
	
	@RequestMapping(value = "/loginUser", method = RequestMethod.POST)
	public String loginUser(@ModelAttribute("user") User user, ModelMap model) {
		boolean isLoginSuccess = user.loginUser();
		logger.debug("Login for user " + user.getuName() + " is " + (isLoginSuccess?"Successful":"Unsuccessful"));
		
		if(isLoginSuccess){
			model.addAttribute("userName",user.getuName());
			return USER_HOME_PAGE;
		}else{
			model.addAttribute("isInvalid",true);
			return LOGIN_PAGE;
		}
	}
	
	@RequestMapping(value = "{userName}/viewProfile", method = RequestMethod.GET)
	public String getProfile(ModelMap model) {
		String userName = (String)model.get("userName");
		logger.debug(LOG_PREFIX+"Getting profile of " + userName);
		User user = new User(userName);
		model.addAttribute("user", user);
		return USER_PROFILE_PAGE;
	}
	
	@RequestMapping(value = "/{userName}/updateUser", method = RequestMethod.POST)
	public String updateUser(@ModelAttribute("user") User user, ModelMap model) {
		try {
			user.setuName((String)model.get("userName"));
			user.update();
		} catch (UserNotFoundException e) {
			logger.error(LOG_PREFIX+e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
			return ERROR_PAGE;
		}
		model.addAttribute("isUpdated",true);
		return USER_PROFILE_PAGE;
	}	
	
	@RequestMapping(value = "/{userName}/viewEatHistory", method = RequestMethod.GET)
	public String getEatHistory(ModelMap model) {
		try {
			String userName = (String)model.get("userName");
			User user = new User(userName);
			user.loadEatHistory();
			model.addAttribute("user",user);
		} catch (UserNotFoundException e) {
			logger.error(LOG_PREFIX+e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
			return ERROR_PAGE;
		}
		model.addAttribute("isUpdated",true);
		return USER_EAT_HISTORY_PAGE;
	}	
	
	@RequestMapping(value = "/{userName}/addEatRecord", method = RequestMethod.POST)
	public String addEatRecord(@ModelAttribute("eatRecord") EatRecord eatRecord, ModelMap model) {
		try {
			String userName = (String)model.get("userName");
			User user = new User(userName);
			logger.debug(eatRecord.getFoodName());
			logger.debug(eatRecord.getLocation().toString());
			logger.debug(""+ eatRecord.getDate());
		} catch (Exception e) {
			logger.error(LOG_PREFIX+e.getMessage());
			model.addAttribute("errorMessage",e.getMessage());
			return ERROR_PAGE;
		}
		model.addAttribute("isUpdated",true);
		return USER_EAT_HISTORY_PAGE;
	}	
}

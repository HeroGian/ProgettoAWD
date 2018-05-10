package ml.canecarroarmato.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {
	
	@RequestMapping(path={"/logout"}, method=RequestMethod.GET)
	public String logoutGet(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		session.removeAttribute("userSession");
	
		return "redirect:/";
	}
}

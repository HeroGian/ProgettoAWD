package ml.canecarroarmato.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ml.canecarroarmato.exceptions.PasswordException;
import ml.canecarroarmato.model.User;
import ml.canecarroarmato.repository.UserRepository;

/*
 * Gestione dell'autenticazione degli utenti 
 */
@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(path={"/login"}, method=RequestMethod.GET)
	public String loginGet() {
	
		return "login";
	}
	
	@RequestMapping(path={"/login"}, method=RequestMethod.POST)
	public String loginPost(HttpServletRequest request, Model model,
			@RequestParam(value="loginUsername") String username,
			@RequestParam(value="loginPassword") String password) {
		
		HttpSession session = request.getSession();
		
		User userSession = (User) userRepository.findByUsernameIgnoreCase(username);
		
		Map<String, String> JsonResp = new HashMap<String, String>();
		
		try {
			userSession.authenticate(password);
			
			session.setAttribute("userSession", userSession);
						
			return "redirect:/";
		}
		catch(NullPointerException e) {
			JsonResp.put("userErr", "* user does not exist.");
		}
		catch(PasswordException e) {
			JsonResp.put("passErr", "* password does not match.");
		}
		model.addAttribute("errors", JsonResp);
								  	      
		return "login";
	}
}

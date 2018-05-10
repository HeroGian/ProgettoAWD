package ml.canecarroarmato.controller;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ml.canecarroarmato.model.User;
import ml.canecarroarmato.repository.UserRepository;

/*
 * Gestisce la registrazione degli utenti tramite chiamata AJAX
 */
@Controller
public class RegistrationController {
	
    @Autowired
    UserRepository userRepository;
	
	@RequestMapping(path={"/signup"}, method=RequestMethod.GET)
	public String registrationGet() {
						  	      
		return "signup";
	}
	
	@ResponseBody
	@RequestMapping(
			path	 = { "/signup" }, 
			method	 = RequestMethod.POST, 
			produces = { MediaType.APPLICATION_JSON_VALUE }
	)
	public String registrationPost(@ModelAttribute @Valid User user, BindingResult result) {
								
		JSONObject resp = new JSONObject();
				
		// eventuali errori di validazione
		if(result.hasErrors()) {
			
			resp.put("validated", false);
			
			JSONObject errors = new JSONObject();
			
			for(FieldError e : result.getFieldErrors()) {				
				errors.put(e.getField(), e.getDefaultMessage());
			}
			
			resp.put("errorMessages", errors);
		}
		else{

			user.autoEncodePassword();
			
			try {
				userRepository.save(user);
				resp.put("validated", true);
			}
			catch(DataIntegrityViolationException e) {
				
				JSONObject errors = new JSONObject();
				
				if(e.getMostSpecificCause().getMessage().contains("username")) {
					errors.put("username", "* Username has already been taken.");
				}
				else {
					errors.put("email", "* Email has already been taken.");
				}
				
				resp.put("validated", false);
				resp.put("errorMessages", errors);				
			}
		}
		return resp.toString();
	}
}

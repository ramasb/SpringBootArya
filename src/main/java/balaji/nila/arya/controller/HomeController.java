package balaji.nila.arya.controller;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	String index(Principal principal) {
		return principal != null ? "home/main" : "home/login";
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String welcome(Map<String, Object> model) {
		model.put("message", this.message);
		return "welcome";
	}

}

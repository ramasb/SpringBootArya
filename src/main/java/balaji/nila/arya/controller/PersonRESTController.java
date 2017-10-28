package balaji.nila.arya.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import balaji.nila.arya.beans.Person;
import balaji.nila.arya.service.PersonService;

@RestController
@RequestMapping("/rest")
public class PersonRESTController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public Iterable<Person> getPersons(Model model) {
		Iterable<Person> persons = personService.listPersons();
		return persons;
	}
}
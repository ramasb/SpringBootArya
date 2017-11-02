package balaji.nila.arya.controller;

import java.time.LocalDateTime;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import balaji.nila.arya.beans.Person;
import balaji.nila.arya.service.PersonService;

@Controller
public class PersonController {

	@Autowired
	private PersonService personService;

	Log log = LogFactory.getLog(this.getClass());

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		LocalDateTime dt = LocalDateTime.now();
		log.info(dt + " : Entered listPersons");
		Iterable<Person> persons = null;
		try {
			persons = this.personService.listPersons();
			persons.forEach(System.out::println);
			log.info(dt + " : Retrieved listPersons - " + persons.toString());
		} catch (Exception ex) {
			log.info("Exception : " + ex);
		}
		model.addAttribute("person", new Person());
		model.addAttribute("persons", persons);
		return "person";
	}

	// For add and update person both
	@RequestMapping(value = "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p) {
		try {
			p.setPhoto(p.getFile().getBytes());

			this.personService.savePerson(p);
		} catch (Exception ex) {
			log.info("Exception : " + ex);
		}
		return "redirect:/persons";

	}

	@RequestMapping("/remove/{id}")
	public String removePerson(@PathVariable("id") Long id) {

		this.personService.removePerson(id);
		return "redirect:/persons";
	}

	@RequestMapping("/edit/{id}")
	public String editPerson(@PathVariable("id") Long id, Model model) {
		model.addAttribute("person", this.personService.getPersonById(id));
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}

}
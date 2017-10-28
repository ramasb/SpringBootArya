package balaji.nila.arya.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import balaji.nila.arya.beans.Person;
import balaji.nila.arya.dao.PersonDAO;
import balaji.nila.arya.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	private PersonDAO personDAO;

	@Override
	@Transactional
	public void savePerson(Person p) {
		this.personDAO.save(p);
	}
	@Override
	@Transactional	
	public Iterable<Person> listPersons() {
		return this.personDAO.findAll();
	}

	@Override
	@Transactional
	public Person getPersonById(Long id) {
		return this.personDAO.findOne(id);
	}

	@Override
	@Transactional
	public void removePerson(Long id) {
		this.personDAO.delete(id);
	}

}

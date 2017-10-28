package balaji.nila.arya.service;

import balaji.nila.arya.beans.Person;

public interface PersonService {

	public void savePerson(Person p);
		
	public Iterable<Person> listPersons();

	public Person getPersonById(Long id);

	public void removePerson(Long id);

}

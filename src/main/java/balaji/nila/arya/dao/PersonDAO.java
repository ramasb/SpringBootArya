package balaji.nila.arya.dao;

import org.springframework.data.repository.CrudRepository;

import balaji.nila.arya.beans.Person;

public interface PersonDAO extends CrudRepository<Person, Long>{

	
}

package balaji.nila.arya.dao;

import org.springframework.data.repository.CrudRepository;

import balaji.nila.arya.beans.Person;

public interface OrderDAO extends CrudRepository<Person, Long>{

	
}

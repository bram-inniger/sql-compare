package be.inniger.scratch.sql.service;

import be.inniger.scratch.sql.dao.AddressDao;
import be.inniger.scratch.sql.dao.PersonDao;
import be.inniger.scratch.sql.model.Address;
import be.inniger.scratch.sql.model.Person;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class PersonService {

    private final AddressDao addressDao;
    private final PersonDao personDao;

    public List<Person> findAll() {
        return personDao.findAll();
    }

    public Optional<Person> findById(int id) {
        return personDao.findById(id);
    }

    public Optional<Person> findByName(String name) {
        return personDao.findByName(name);
    }

    public void save(Person person) {
        personDao.save(person);
    }

    public void update(Person person) {
        personDao.update(person);
    }

    public void deleteById(int id) {
        personDao.deleteById(id);
    }

    public Optional<Person> getFullPersonById(int id) {
        return findById(id)
                .map(person -> new Person(
                        person.getId(),
                        person.getName(),
                        getAddresses(id)));
    }

    public List<Address> getAddresses(int personId) {
        return addressDao.findAllByPersonId(personId);
    }
}

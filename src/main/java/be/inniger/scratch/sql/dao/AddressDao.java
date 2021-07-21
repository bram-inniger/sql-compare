package be.inniger.scratch.sql.dao;

import be.inniger.scratch.sql.model.Address;

import java.util.List;

public interface AddressDao {

    List<Address> findAllByPersonId(int personId);

    void save(Address address);
}

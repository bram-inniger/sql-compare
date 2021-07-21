package be.inniger.scratch.sql.dao.jdbi;

import be.inniger.scratch.sql.dao.AddressDao;
import be.inniger.scratch.sql.model.Address;
import lombok.RequiredArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import java.util.List;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class JdbiAddressDao implements AddressDao {

    private final Jdbi jdbi;

    @Override
    public List<Address> findAllByPersonId(int personId) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM address WHERE person_id = :personId")
                        .bind("personId", personId)
                        .mapTo(Address.class)
                        .list());
    }

    @Override
    public void save(Address address) {
        //noinspection SqlInsertValues
        jdbi.useHandle(handle ->
                handle.createUpdate("INSERT INTO address(street, person_id) VALUES (:street, :personId)")
                        .bind("street", address.getStreet())
                        .bind("personId", address.getPersonId())
                        .execute());
    }
}

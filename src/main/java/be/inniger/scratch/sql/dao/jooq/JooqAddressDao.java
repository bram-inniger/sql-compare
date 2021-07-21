package be.inniger.scratch.sql.dao.jooq;

import be.inniger.scratch.sql.dao.AddressDao;
import be.inniger.scratch.sql.model.Address;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;

import java.util.List;

import static be.inniger.scratch.sql.dao.jooq.Tables.ADDRESS;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class JooqAddressDao implements AddressDao {

    private final DSLContext db;

    @Override
    public List<Address> findAllByPersonId(int personId) {
        return db.selectFrom(ADDRESS)
                .where(ADDRESS.PERSON_ID.eq(personId))
                .fetch(record -> new Address(
                        record.getId(),
                        record.getStreet(),
                        record.getPersonId()));
    }

    @Override
    public void save(Address address) {
        db.insertInto(ADDRESS, ADDRESS.STREET, ADDRESS.PERSON_ID)
                .values(address.getStreet(), address.getPersonId())
                .execute();
    }
}

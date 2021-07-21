package be.inniger.scratch.sql.dao.jdbctemplate;

import be.inniger.scratch.sql.dao.AddressDao;
import be.inniger.scratch.sql.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

@SuppressWarnings("ClassCanBeRecord")
@RequiredArgsConstructor
public class JdbcTemplateAddressDao implements AddressDao {

    private final NamedParameterJdbcTemplate template;

    @Override
    public List<Address> findAllByPersonId(int personId) {
        return template.query(
                "SELECT * FROM address WHERE person_id = :personId",
                Map.of("personId", personId),
                (rs, __) -> new Address(
                        rs.getInt("id"),
                        rs.getString("street"),
                        rs.getInt("person_id")));
    }

    @Override
    public void save(Address address) {
        //noinspection SqlInsertValues
        template.update(
                "INSERT INTO address(street, person_id) VALUES (:street, :personId)",
                Map.of(
                        "street", address.getStreet(),
                        "personId", address.getPersonId()));
    }
}

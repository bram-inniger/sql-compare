package be.inniger.scratch.sql.dao.mybatis;

import be.inniger.scratch.sql.dao.AddressDao;
import be.inniger.scratch.sql.model.Address;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MyBatisAddressMapper extends AddressDao {

    @Override
    @Select("SELECT id, street, person_id FROM address WHERE person_id = #{personId}")
    List<Address> findAllByPersonId(int personId);

    @SuppressWarnings("SqlInsertValues")
    @Override
    @Insert("INSERT INTO address (street, person_id) VALUES (#{street}, #{personId})")
    void save(Address address);
}

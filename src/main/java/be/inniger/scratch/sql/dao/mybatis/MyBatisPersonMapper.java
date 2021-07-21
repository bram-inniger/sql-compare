package be.inniger.scratch.sql.dao.mybatis;

import be.inniger.scratch.sql.dao.PersonDao;
import be.inniger.scratch.sql.model.Person;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Optional;

public interface MyBatisPersonMapper extends PersonDao {

    @Override
    @Select("SELECT * FROM person")
    List<Person> findAll();

    @Override
    @Select("SELECT * FROM person WHERE id = #{id}")
    Optional<Person> findById(int id);

    @Override
    @Select("SELECT * FROM person WHERE name = #{name}")
    Optional<Person> findByName(String name);

    @SuppressWarnings("SqlInsertValues")
    @Override
    @Insert("INSERT INTO person(name) VALUES (#{name})")
    void save(Person person);

    @Override
    @Update("UPDATE person SET name = #{name} WHERE id = #{id}")
    void update(Person person);

    @Override
    @Delete("DELETE FROM person WHERE id = #{id}")
    void deleteById(int id);
}

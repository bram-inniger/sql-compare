package be.inniger.scratch.sql;

import be.inniger.scratch.sql.App.SqlBackend;
import be.inniger.scratch.sql.model.Address;
import be.inniger.scratch.sql.model.Person;
import be.inniger.scratch.sql.service.PersonService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonTest {

    private App<?> app;
    private PersonService service;

    @ParameterizedTest
    @EnumSource(SqlBackend.class)
    public void canFindAll(SqlBackend backend) {
        setup(backend);

        assertThat(service.findAll())
                .hasSize(4)
                .containsExactlyInAnyOrder(
                        new Person(1, "Jake Doe"),
                        new Person(2, "Joseph Doe"),
                        new Person(3, "Mary Doe"),
                        new Person(4, "Abraham Doe"));
    }

    @ParameterizedTest
    @EnumSource(SqlBackend.class)
    public void canFindSingle(SqlBackend backend) {
        setup(backend);

        assertThat(service.findById(1))
                .isEqualTo(Optional.of(new Person(1, "Jake Doe")));
        assertThat(service.findByName("Mary Doe"))
                .isEqualTo(Optional.of(new Person(3, "Mary Doe")));
        assertThat(service.findByName("Nobody Inniger"))
                .isEqualTo(Optional.empty());
    }

    @ParameterizedTest
    @EnumSource(SqlBackend.class)
    public void canUpdateAndDelete(SqlBackend backend) {
        setup(backend);

        service.save(new Person("Joe Doe"));
        Optional<Person> joske = service.findByName("Joe Doe");
        int id = joske.map(Person::getId).orElseThrow();

        assertThat(joske)
                .isEqualTo(Optional.of(new Person(5, "Joe Doe")));
        assertThat(id)
                .isEqualTo(5);

        service.update(new Person(id, "Jon Doe"));

        assertThat(service.findById(5))
                .isEqualTo(Optional.of(new Person(5, "Jon Doe")));
        assertThat(service.findByName("Jon Doe"))
                .isEqualTo(Optional.of(new Person(5, "Jon Doe")));
        assertThat(service.findByName("Joe Doe"))
                .isEqualTo(Optional.empty());

        service.deleteById(id);

        assertThat(service.findById(5))
                .isEqualTo(Optional.empty());
        assertThat(service.findByName("Jon Doe"))
                .isEqualTo(Optional.empty());
    }

    @ParameterizedTest
    @EnumSource(SqlBackend.class)
    public void canFindFullPerson(SqlBackend backend) {
        setup(backend);

        assertThat(service.getFullPersonById(1))
                .isEqualTo(Optional.of(new Person(
                        1,
                        "Jake Doe",
                        List.of(
                                new Address(1, "The Street", 1),
                                new Address(2, "Boulevard", 1)
                        ))));
    }

    @ParameterizedTest
    @EnumSource(SqlBackend.class)
    public void canFindSingleAddress(SqlBackend backend) {
        setup(backend);

        assertThat(service.getAddresses(1))
                .hasSize(2)
                .containsExactlyInAnyOrder(
                        new Address(1, "The Street", 1),
                        new Address(2, "Boulevard", 1));
        assertThat(service.getAddresses(2))
                .isEmpty();
    }

    public void setup(SqlBackend backend) {
        app = App.createAndStart(backend);
        this.service = app.getPersonService();

        service.save(new Person("Jake Doe"));
        service.save(new Person("Joseph Doe"));
        service.save(new Person("Mary Doe"));
        service.save(new Person("Abraham Doe"));

        var addressMapper = app.getAddressService();
        addressMapper.save(new Address("The Street", 1));
        addressMapper.save(new Address("Boulevard", 1));
    }

    @AfterEach
    public void cleanup() {
        if (app != null) {
            app.stop();
        }
    }
}

package be.inniger.scratch.sql.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class Person {

    private final Integer id;
    private final String name;
    private final List<Address> addresses;

    public Person(String name) {
        this(null, name);
    }

    public Person(Integer personId, String name) {
        this(personId, name, new ArrayList<>());
    }

    public Person(Integer personId, String name, List<Address> addresses) {
        this.id = personId;
        this.name = name;
        this.addresses = addresses;
    }
}

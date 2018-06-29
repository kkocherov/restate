package restate;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Person implements Cloneable {
    String firstName;
    String middleName;
    String lastName;
    Set<Contact> contacts;

    public Person() {
        this.contacts = new HashSet<>();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Person person = (Person) super.clone();
        person.contacts = new HashSet<>();

        for (Contact contact: contacts) {
            person.contacts.add((Contact) contact.clone());
        }

        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(middleName, person.middleName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(contacts, person.contacts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, middleName, lastName, contacts);
    }
}

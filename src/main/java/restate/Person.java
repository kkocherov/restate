package restate;

import java.util.List;

enum ContactType { email, phone }

class Contact {
    ContactType type;
    String value;
}

public class Person implements Identifiable<Long> {
    Long id;
    String firstName;
    String middleName;
    String lastName;
    List<Contact> contacts;

    public Long id() {
        return this.id;
    }
}

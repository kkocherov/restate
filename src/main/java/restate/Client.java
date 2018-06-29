package restate;

import java.util.Set;

public class Client extends Person implements Cloneable {
    public Client(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

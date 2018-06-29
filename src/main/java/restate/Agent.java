package restate;

import java.util.Objects;

public class Agent extends Person implements Cloneable {
    Double commission;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


    @Override
    public String toString() {
        return "Agent{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Agent agent = (Agent) o;
        return Objects.equals(commission, agent.commission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), commission);
    }
}

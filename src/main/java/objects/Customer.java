package objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String info;

    public Customer(){}

    public Customer(int id, String firstName, String lastName, String info) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Customer[" +
                "id = " + id +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", info = '" + info + '\'' +
                ']';
    }
}

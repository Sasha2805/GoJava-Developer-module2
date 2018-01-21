package dbObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Developer {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String sex;

    public Developer(){}

    public Developer(int id, String firstName, String lastName, int age, String sex) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Developer[" +
                "id = " + id +
                ", firstName = '" + firstName + '\'' +
                ", lastName = '" + lastName + '\'' +
                ", age = " + age +
                ", sex = '" + sex + '\'' +
                ']';
    }
}

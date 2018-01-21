package dbObjects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Company {
    private int id;
    private String name;
    private String info;

    public Company(){}

    public Company(int id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    @Override
    public String toString() {
        return "Project[" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", info = '" + info + '\'' +
                ']';
    }
}

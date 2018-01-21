package objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Project {
    private int id;
    private String name;
    private String info;

    public Project(){}

    public Project(int id, String name, String info) {
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

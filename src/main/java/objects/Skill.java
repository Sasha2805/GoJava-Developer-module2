package objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Skill {
    private int id;
    private String branchDevelopment;
    private String skillLevel;

    public Skill(){}

    public Skill(int id, String branchOfDevelopment, String skillLevel) {
        this.id = id;
        this.branchDevelopment = branchOfDevelopment;
        this.skillLevel = skillLevel;
    }

    @Override
    public String toString() {
        return "Skill[" +
                "id = " + id +
                ", branchDevelopment = '" + branchDevelopment + '\'' +
                ", skillLevel = " + skillLevel +
                ']';
    }
}

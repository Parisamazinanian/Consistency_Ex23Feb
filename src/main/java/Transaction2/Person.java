package Transaction2;

public class Person {
    private Integer ID;
    private String name;
    private String lastName;
    private Integer age;

    public Person(Integer ID, String name, String lastName, Integer age) {
        this.ID = ID;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }
}

package task3.person;

public class Person {
    private String name;
    private String homePlanet;

    public Person(String name, String homePlanet) {
        this.name = name;
        this.homePlanet = homePlanet;
    }

    public String getName() { return name; }
    public String getHomePlanet() { return homePlanet; }

    @Override
    public String toString() {
        return getName();
    }
}
package sbp.Person;

public class Person implements Comparable<Person> {
    private String name;
    private String city;
    private int age;

    public Person(String name, String city, int age) {
        if (name == null || city == null) {
            throw new IllegalArgumentException("Name and city cannot be null");
        }
        this.name = name;
        this.city = city;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        // Сначала сравниваем по city
        int cityComparison = this.city.compareTo(other.city);
        if (cityComparison != 0) {
            return cityComparison;
        }
        // Затем по name
        return this.name.compareTo(other.name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Person person = (Person) o;
        return age == person.age &&
                name.equalsIgnoreCase(person.name) &&
                city.equalsIgnoreCase(person.city);
    }

    @Override
    public int hashCode() {
        int total = 19;
        return total + name.toLowerCase().hashCode() + city.toLowerCase().hashCode() + Integer.hashCode(age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }
}

package org.example.HW_L5_T2_Equals;

import java.util.Objects;

public class Person {
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

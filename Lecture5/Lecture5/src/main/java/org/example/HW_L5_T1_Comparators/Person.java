package org.example.HW_L5_T1_Comparators;

import java.util.Objects;

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
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", age=" + age +
                '}';
    }

    // Геттеры (при необходимости)
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
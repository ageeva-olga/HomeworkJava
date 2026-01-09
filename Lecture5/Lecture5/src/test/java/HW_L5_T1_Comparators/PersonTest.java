package HW_L5_T1_Comparators;

import org.example.HW_L5_T1_Comparators.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void differentNamesTest() {
        Person p1 = new Person("Alex", "City", 10);
        Person p2 = new Person("Zoe", "City", 5);

        assertTrue(p1.compareTo(p2) < 0);
        assertTrue(p2.compareTo(p1) > 0);
    }

    @Test
    void differentCitiesTest() {
        Person p1 = new Person("Alex", "A City", 10);
        Person p2 = new Person("Alex", "B City", 5);

        assertTrue(p1.compareTo(p2) < 0);
        assertTrue(p2.compareTo(p1) > 0);
    }

    @Test
    void equalPersonsSameCityAndNameTest() {
        Person p1 = new Person("John", "London", 25);
        Person p2 = new Person("John", "London", 30); // age не участвует в сравнении

        assertEquals(0, p1.compareTo(p2));
        assertEquals(0, p2.compareTo(p1));
    }

    @Test
    void nullConstructorArgsThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Person(null, "City", 10));
        assertThrows(IllegalArgumentException.class, () -> new Person("Name", null, 10));
    }

    @Test
    void toStringTest() {
        Person person = new Person("Alice", "Moscow", 25);
        String expected = "Person{name='Alice', city='Moscow', age=25}";

        assertEquals(expected, person.toString());
    }
}

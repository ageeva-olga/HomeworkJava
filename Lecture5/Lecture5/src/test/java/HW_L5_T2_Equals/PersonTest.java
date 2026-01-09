package HW_L5_T2_Equals;

import org.example.HW_L5_T2_Equals.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void equalsSameObjectTest() {
        Person person = new Person("Alice", "Moscow", 25);
        assertTrue(person.equals(person));
    }

    @Test
    void equalsNullTest() {
        Person person = new Person("Alice", "Moscow", 25);
        assertFalse(person.equals(null));
    }

    @Test
    void equalsDifferentClassTest() {
        Person person = new Person("Alice", "Moscow", 25);
        assertFalse(person.equals("Some string"));
    }

    @Test
    void equalsIgnoreCaseTest() {
        Person person1 = new Person("alice", "moscow", 25);
        Person person2 = new Person("ALICE", "Moscow", 25);

        assertTrue(person1.equals(person2));
        assertTrue(person2.equals(person1));
    }

    @Test
    void equalsWhenAgeDifferentTest() {
        Person person1 = new Person("Alice", "Moscow", 25);
        Person person2 = new Person("Alice", "Moscow", 30);

        assertFalse(person1.equals(person2));
    }

    @Test
    void equalsWhenNameDifferentTest() {
        Person person1 = new Person("Alice", "Moscow", 25);
        Person person2 = new Person("Bob", "Moscow", 25);

        assertFalse(person1.equals(person2));
    }

    @Test
    void equalsWhenCityDifferentTest() {
        Person person1 = new Person("Alice", "Moscow", 25);
        Person person2 = new Person("Alice", "London", 25);

        assertFalse(person1.equals(person2));
    }

    @Test
    void testHashCode_EqualObjectsHaveSameHashCode() {
        Person person1 = new Person("alice", "moscow", 25);
        Person person2 = new Person("ALICE", "MOSCOW", 25);

        assertEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    void testHashCode_DifferentObjectsHaveDifferentHashCodes() {
        Person person1 = new Person("Alice", "Moscow", 25);
        Person person2 = new Person("Bob", "London", 30);

        assertNotEquals(person1.hashCode(), person2.hashCode());
    }

    @Test
    void testToString() {
        Person person = new Person("Alice", "London", 30);
        String expected = "Person{name='Alice', city='London', age=30}";

        assertEquals(expected, person.toString());
    }

    @Test
    void nullConstructorArgsThrowsExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> new Person(null, "City", 20));
        assertThrows(IllegalArgumentException.class, () -> new Person("Name", null, 20));
    }
}

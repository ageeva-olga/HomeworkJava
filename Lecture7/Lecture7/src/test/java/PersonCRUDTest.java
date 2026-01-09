import org.junit.jupiter.api.*;
import sbp.Person.Person;
import sbp.Person.PersonCRUD;

import static org.junit.jupiter.api.Assertions.*;

public class PersonCRUDTest {
    private PersonCRUD crud;
    private static final String TEST_NAME = "Алексей";
    private static final String TEST_CITY = "Москва";
    private static final int TEST_AGE = 25;

    @BeforeEach
    public void setUp() {
        crud = new PersonCRUD();
        crud.connect();
    }

    @AfterEach
    public void tearDown() {
        if (crud != null) {
            crud.close();
        }
    }

    @Test
    @DisplayName("Создание нового Person")
    public void createPersonTest() {
        Person person = new Person(TEST_NAME, TEST_CITY, TEST_AGE);
        boolean result = crud.createPerson(person);

        assertTrue(result);
    }

    @Test
    @DisplayName("Чтение существующего Person по имени и городу")
    public void readExistingPersonTest() {
        Person person = new Person(TEST_NAME, TEST_CITY, TEST_AGE);
        crud.createPerson(person);

        Person found = crud.readPerson(TEST_NAME, TEST_CITY);
        assertNotNull(found);
        assertEquals(TEST_NAME, found.getName());
        assertEquals(TEST_CITY, found.getCity());
        assertEquals(TEST_AGE, found.getAge());
    }

    @Test
    @DisplayName("Чтение несуществующего Person")
    public void readNonExistentPersonTest() {
        Person found = crud.readPerson("Не существует", "Нет города");

        assertNull(found);
    }

    @Test
    @DisplayName("Обновление данных Person")
    public void updatePersonTest() {
        Person original = new Person(TEST_NAME, TEST_CITY, TEST_AGE);
        crud.createPerson(original);

        String newName = "Алексей М.";
        String newCity = "Санкт-Петербург";
        int newAge = 31;
        Person updated = new Person(newName, newCity, newAge);

        boolean result = crud.updatePerson(TEST_NAME, TEST_CITY, updated);
        assertTrue(result);

        Person found = crud.readPerson(newName, newCity);
        assertNotNull(found);
        assertEquals(newName, found.getName());
        assertEquals(newCity, found.getCity());
        assertEquals(newAge, found.getAge());
    }

    @Test
    @DisplayName("Удаление существующего Person")
    public void deletePersonTest() {
        Person person = new Person(TEST_NAME, TEST_CITY, TEST_AGE);
        crud.createPerson(person);

        boolean deleted = crud.deletePerson(TEST_NAME, TEST_CITY);
        assertTrue(deleted);

        Person found = crud.readPerson(TEST_NAME, TEST_CITY);
        assertNull(found);
    }

    @Test
    @DisplayName("Попытка удаления несуществующего Person")
    public void deleteNonExistentPersonTest() {
        boolean result = crud.deletePerson("Не существует", "Нет города");
        assertFalse(result);
    }
}

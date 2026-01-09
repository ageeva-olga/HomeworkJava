package sbp.Person;

import java.sql.*;

public class PersonCRUD {
    private final String url = "jdbc:h2:mem:testdb"; // Используем in-memory H2 базу
    private Connection connection;

    /**
     * Подключение к БД и создание таблицы, если не существует
     */
    public void connect() {
        try {
            this.connection = DriverManager.getConnection(url, "sa", "");
            createTable();
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Создание таблицы person с полями name, city, age
     */
    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS person (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "city VARCHAR(100) NOT NULL, " +
                "age INT NOT NULL" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица person создана или уже существует.");
        } catch (SQLException e) {
            System.err.println("Ошибка создания таблицы: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Добавление нового Person в БД
     * @param person объект Person
     * @return true, если успешно добавлен
     */
    public boolean createPerson(Person person) {
        String sql = "INSERT INTO person(name, city, age) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getCity());
            pstmt.setInt(3, person.getAge());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при добавлении Person: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Чтение Person по имени и городу (так как equals сравнивает name и city без учёта регистра)
     * @param name имя
     * @param city город
     * @return объект Person или null
     */
    public Person readPerson(String name, String city) {
        String sql = "SELECT name, city, age FROM person WHERE UPPER(name) = UPPER(?) AND UPPER(city) = UPPER(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, city);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Person(
                            rs.getString("name"),
                            rs.getString("city"),
                            rs.getInt("age")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при чтении Person: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Обновление данных о человеке по имени и городу
     * @param oldName старое имя
     * @param oldCity старый город
     * @param newPerson новые данные
     * @return true, если обновлено успешно
     */
    public boolean updatePerson(String oldName, String oldCity, Person newPerson) {
        String sql = "UPDATE person SET name = ?, city = ?, age = ? " +
                "WHERE UPPER(name) = UPPER(?) AND UPPER(city) = UPPER(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newPerson.getName());
            pstmt.setString(2, newPerson.getCity());
            pstmt.setInt(3, newPerson.getAge());
            pstmt.setString(4, oldName);
            pstmt.setString(5, oldCity);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при обновлении Person: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Удаление Person по имени и городу
     * @param name имя
     * @param city город
     * @return true, если удалён успешно
     */
    public boolean deletePerson(String name, String city) {
        String sql = "DELETE FROM person WHERE UPPER(name) = UPPER(?) AND UPPER(city) = UPPER(?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, city);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Ошибка при удалении Person: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Закрытие соединения
     */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Соединение с БД закрыто.");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

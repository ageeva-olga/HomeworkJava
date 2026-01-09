import org.junit.jupiter.api.Test;
import ru.edu.lesson6.TravelService;
import ru.edu.lesson6.CityInfo;
import ru.edu.lesson6.GeoPosition;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TravelServiceTests {

    private TravelService converter = new TravelService();

    @Test
    public void addCityTest() {
        converter.add(new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'57'')")));
        List<String> names = converter.citiesNames();
        assertTrue(names.contains("Moscow"));
    }

    @Test
    public void addCityDuplicateThrowsExceptionTest() {
        converter.add(new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'57'')")));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            converter.add(new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'57'')")));
        });
        assertTrue(exception.getMessage().contains("already exists"));
    }

    @Test
    public void removeCityTest() {
        converter.add(new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'57'')")));
        converter.remove("Moscow");
        assertTrue(converter.citiesNames().isEmpty());
    }

    @Test
    public void removeCityNonExistentThrowsExceptionTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            converter.remove("Unknown");
        });
        assertTrue(exception.getMessage().contains("does not exist"));
    }

    @Test
    public void testGetDistance_ValidCities_CorrectCalculation() {
        converter.add(new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'57'')")));
        converter.add(new CityInfo("N. Novgorod", new GeoPosition("56(19'37'')", "44(00'27'')")));

        int distance = converter.getDistance("Moscow", "N. Novgorod");
        assertTrue(distance > 400 && distance < 500, "Distance should be around 400 km");
    }

    @Test
    public void getCitiesNearTest() {
        converter.add(new CityInfo("N. Novgorod", new GeoPosition("56(19'37'')", "44(00'27'')")));
        converter.add(new CityInfo("Moscow", new GeoPosition("55(45'07'')", "37(36'57'')"))); //~400 км от Нижнего Новгорода
        converter.add(new CityInfo("Arzamas", new GeoPosition("55(23'00'')", "43(48'00'')"))); // ~110 км от Нижнего Новгорода
        converter.add(new CityInfo("Bor", new GeoPosition("56(21'29'')", "44(04'29'')"))); // ~25 км
        converter.add(new CityInfo("Kstovo", new GeoPosition("56(09'06'')", "44(11'44'')"))); // ~32 км

        List<String> near = converter.getCitiesNear("N. Novgorod", 150);
        assertEquals(3, near.size());
        assertTrue(near.contains("Arzamas"));
        assertTrue(near.contains("Bor"));
        assertTrue(near.contains("Kstovo"));
        assertFalse(near.contains("Moscow"));
    }

    @Test
    public void getCitiesNearCityNotFoundTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.getCitiesNear("Unknown", 50);
        });
    }
}

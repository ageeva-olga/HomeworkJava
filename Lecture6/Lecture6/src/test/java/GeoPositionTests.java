import org.junit.jupiter.api.Test;
import ru.edu.lesson6.GeoPosition;

import static org.junit.jupiter.api.Assertions.*;

public class GeoPositionTests {

    @Test
    public void simpleDegreesTest() {
        GeoPosition pos = new GeoPosition("55", "37");
        assertEquals(Math.toRadians(55.0), pos.getLatitude(), 1e-10);
        assertEquals(Math.toRadians(37.0), pos.getLongitude(), 1e-10);
    }

    @Test
    public void dmsFormatWithSecondsTest() {
        GeoPosition pos = new GeoPosition("55(45'07'')", "37(37'00'')");

        double expectedLat = Math.toRadians(55 + 45.0/60.0 + 7.0/3600.0);
        double expectedLon = Math.toRadians(37 + 37.0/60.0);

        assertEquals(expectedLat, pos.getLatitude(), 1e-10);
        assertEquals(expectedLon, pos.getLongitude(), 1e-10);
    }

    @Test
    public void dmsFormatOnlyMinutesTest() {
        GeoPosition pos = new GeoPosition("48(30')", "11(15')");

        double expectedLat = Math.toRadians(48 + 30.0 /60.0);
        double expectedLon = Math.toRadians(11 + 15.0/60.0);

        assertEquals(expectedLat, pos.getLatitude(), 1e-10);
        assertEquals(expectedLon, pos.getLongitude(), 1e-10);
    }

    @Test
    public void edgeCaseDegreesWithZeroMinutesSecondsTest() {
        GeoPosition pos = new GeoPosition("90(0'0'')", "180(0'0'')");

        assertEquals(Math.toRadians(90.0), pos.getLatitude(), 1e-10);
        assertEquals(Math.toRadians(180.0), pos.getLongitude(), 1e-10);
    }

    @Test
    public void parseCoordinateNullInputThrowsExceptionTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GeoPosition(null, "0");
        });
        assertTrue(exception.getMessage().contains("cannot be null or empty"));
    }

    @Test
    public void parseCoordinateEmptyStringThrowsExceptionTest() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GeoPosition("", "0");
        });
        assertTrue(exception.getMessage().contains("cannot be null or empty"));
    }

    @Test
    public void toStringTest() {
        GeoPosition pos = new GeoPosition("59", "57");
        String expectedStart = "GeoPosition{latitude=" + Math.toRadians(59);
        assertTrue(pos.toString().startsWith(expectedStart));
        assertTrue(pos.toString().contains("rad"));
        assertTrue(pos.toString().contains("longitude="));
    }
}

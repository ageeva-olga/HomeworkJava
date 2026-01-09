package ru.edu.lesson6;

/**
 * Geo position.
 */
public class GeoPosition {

    /**
     * Широта в радианах.
     */
    private double latitude;

    /**
     * Долгота в радианах.
     */
    private double longitude;

    /**
     * Ctor.
     *
     * @param latitudeGradus  - latitude in gradus
     * @param longitudeGradus - longitude in gradus
     *                        Possible values: 55, 55(45'07''), 59(57'00'')
     */
    public GeoPosition(String latitudeGradus, String longitudeGradus) {
        // parse and set latitude and longitude in radian
        this.latitude = Math.toRadians(parseCoordinate(latitudeGradus));
        this.longitude = Math.toRadians(parseCoordinate(longitudeGradus));
    }

    private double parseCoordinate(String coord) {
        if (coord == null || coord.isEmpty()) {
            throw new IllegalArgumentException("Coordinate string cannot be null or empty");
        }

        // Если нет скобки — значит, просто градусы
        if (!coord.contains("(")) {
            return Double.parseDouble(coord.trim());
        }

        // Разбираем D(M'S'')
        int degreeEnd = coord.indexOf('(');
        double degrees = Double.parseDouble(coord.substring(0, degreeEnd).trim());

        String minutesSeconds = coord.substring(degreeEnd + 1, coord.length() - 1); // убираем скобку и кавычки
        String[] parts = minutesSeconds.split("'");

        double minutes = 0;
        double seconds = 0;

        if (parts.length >= 1 && !parts[0].isEmpty()) {
            minutes = Double.parseDouble(parts[0]);
        }
        if (parts.length >= 2) {
            // Убираем возможные " из секунд
            String sec = parts[1].replace("\"", "").trim();
            if (!sec.isEmpty()) {
                seconds = Double.parseDouble(sec);
            }
        }

        return degrees + (minutes / 60.0) + (seconds / 3600.0);
    }

    // gettes and toString
    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude=" + latitude + " rad" +
                ", longitude=" + longitude + " rad" +
                '}';
    }
}

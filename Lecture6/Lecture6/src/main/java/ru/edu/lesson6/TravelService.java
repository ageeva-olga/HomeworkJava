package ru.edu.lesson6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Travel Service.
 */
public class TravelService {

    // do not change type
    private final List<CityInfo> cities = new ArrayList<>();

    /**
     * Append city info.
     *
     * @param cityInfo - city info
     * @throws IllegalArgumentException if city already exists
     */
    public void add(CityInfo cityInfo) {
        // do something
        if (cities.stream().anyMatch(city -> city.getName().equals(cityInfo.getName()))) {
            throw new IllegalArgumentException("City already exists: " + cityInfo.getName());
        }
        cities.add(cityInfo);
    }

    /**
     * remove city info.
     *
     * @param cityName - city name
     * @throws IllegalArgumentException if city doesn't exist
     */
    public void remove(String cityName) {
        // do something
        if (cities.stream().noneMatch(city -> city.getName().equals(cityName))) {
            throw new IllegalArgumentException("City does not exist: " + cityName);
        }
        cities.removeIf(city -> city.getName().equals(cityName));
    }

    /**
     * Get cities names.
     */
    public List<String> citiesNames() {
        return cities.stream()
                .map(CityInfo::getName)
                .collect(Collectors.toList());
    }

    /**
     * Get distance in kilometers between two cities.
     * https://www.kobzarev.com/programming/calculation-of-distances-between-cities-on-their-coordinates/
     *
     * @param srcCityName  - source city
     * @param destCityName - destination city
     * @throws IllegalArgumentException if source or destination city doesn't exist.
     */
    public int getDistance(String srcCityName, String destCityName) {
        CityInfo src = cities.stream()
                .filter(city -> city.getName().equals(srcCityName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("City not found: " + srcCityName));

        CityInfo dest = cities.stream()
                .filter(city -> city.getName().equals(destCityName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("City not found: " + destCityName));

        // Формула для расстояния между двумя точками на сфере (в км)
        double lat1 = src.getPosition().getLatitude();
        double lon1 = src.getPosition().getLongitude();
        double lat2 = dest.getPosition().getLatitude();
        double lon2 = dest.getPosition().getLongitude();

        double R = 6371; // радиус Земли в км
        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (int) Math.round(R * c);
    }

    /**
     * Get all cities near current city in radius.
     *
     * @param cityName - city
     * @param radius   - radius in kilometers for search
     * @throws IllegalArgumentException if city with cityName city doesn't exist.
     */
    public List<String> getCitiesNear(String cityName, int radius) {
        CityInfo center = cities.stream()
                .filter(city -> city.getName().equals(cityName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("City not found: " + cityName));

        return cities.stream()
                .filter(city -> !city.getName().equals(center.getName())) // исключаем сам город
                .filter(city -> getDistance(center.getName(), city.getName()) <= radius)
                .map(CityInfo::getName)
                .collect(Collectors.toList());
    }
}

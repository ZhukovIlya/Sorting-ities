package model;


import java.io.*;
import java.util.*;
import java.util.Scanner;

public class Сities {

    private final List<City> cities = new ArrayList<>();


    public List<City> getCities() {
        return cities;
    }

    public void downloadCities(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {
                cities.add(parse(scanner.nextLine()));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static City parse(String line) {
        Scanner scanner = new Scanner(line);
        scanner.useDelimiter(";");
        scanner.skip("\\d*");
        String name = scanner.next();
        String region = scanner.next();
        String district = scanner.next();
        int population = scanner.nextInt();
        String foundation = null;
        if (scanner.hasNext()) {
            foundation = scanner.next();
        }
        scanner.close();

        return new City(name, region, district, population, foundation);
    }
    public void print() {
        cities.forEach(System.out::println);
    }

    public void sortName(){
        cities.sort(Comparator.comparing(City::getName));
    }
    public void sortDistrict(){
        cities.sort(Comparator.comparing(City::getDistrict).thenComparing(City::getName));
    }

    public void maxPopulation() {
        City[] arrayCity = new City[cities.size()];
        arrayCity = cities.toArray(arrayCity);
        int maxValue = arrayCity[0].getPopulation();
        int maxIndex = 0;
        for (int i = 1; i < arrayCity.length; i++) {
            if (arrayCity[i].getPopulation() > maxValue) {
                maxValue = arrayCity[i].getPopulation();
                maxIndex = i;
            }

        }
        System.out.println(maxIndex + "=" + maxValue);
    }
    public void numberOfCitiesInTheRegion(){
        List<String> duplicates = new ArrayList<>();
        for (City e : cities) {
            duplicates.add(e.getRegion());

        }

        Map<String, Integer> counter = new HashMap<>();
        for (String x : duplicates) {
            int newValue = counter.getOrDefault(x, 0) + 1;
            counter.put(x, newValue);
        }
        for (Map.Entry<String, Integer> pair : counter.entrySet()) {
            String key = pair.getKey();
            Integer value = pair.getValue();
            System.out.println(key + " - " + value);
        }
    }
}


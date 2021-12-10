import model.Сities;



public class Main {
    public static void main(String[] args) {
        String file = "src/main/resources/city_ru.csv";
        Сities cities = new Сities();
        cities.downloadCities(file);
        cities.numberOfCitiesInTheRegion();

    }

}


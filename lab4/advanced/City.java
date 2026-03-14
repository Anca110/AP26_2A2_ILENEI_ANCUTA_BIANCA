import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class City {

    private List<Intersection> intersections;
    private List<Street> streets;
    private Map<Intersection, List<Street>> cityMap;//pt fiecare intersectie ce strazi sunt legate de ea

    public City(List<Intersection> intersections, List<Street> streets, Map<Intersection, List<Street>> cityMap) {
        this.intersections = intersections;
        this.streets = streets;
        this.cityMap = cityMap;
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public Map<Intersection, List<Street>> getCityMap() {
        return cityMap;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public void addIntersection(Intersection intersection){
        intersections.add(intersection);//adaug intersectii in lista de intersectii
        cityMap.put(intersection, new LinkedList<>());
    }

    public void addStreet(Street street){
        streets.add(street);

        Intersection intersection1=street.getIntersection1();
        Intersection intersection2=street.getIntersection2();

        cityMap.get(intersection1).add(street);
        cityMap.get(intersection2).add(street);
    }


    @Override
    public String toString(){
        return "City: "+ "intersections= " + intersections+ ", streets="+ streets;
    }
}

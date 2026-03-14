import java.util.List;

public class Route {
    private List<Intersection> intersections;
    private int totalLength;

    public Route(List<Intersection> intersections, int totalLength) {
        this.intersections = intersections;
        this.totalLength = totalLength;
    }

    public List<Intersection> getIntersections() {
        return intersections;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public String toString(){
        return "Route: totalLength= "+ totalLength+ ", intersections= "+ intersections;
    }
}

package lab2_advanced;

/**
 * solutie/ruta intre 2 locatii
 */
public class Solution {
    private Location[] route;
    private int routeLength;

    public Solution(Location[] route, int routeLength){
        this.route = route;
        this.routeLength = routeLength;
    }

    @Override
    public String toString() {
        String solution = "";
        for (int i = 0; i < routeLength; i++) {
            solution=solution + route[i].getName();
            if (i < routeLength - 1) {
                solution+= " -> ";
            }
        }
        return solution;
    }
}
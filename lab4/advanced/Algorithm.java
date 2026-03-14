import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Algorithm {
    private City city;

    public Algorithm(City city) {
        this.city = city;
    }


    //fiecare muchie e parcursa de cel mult 2 ori(odata cand mergi si odata cand te intorci) =>
    //costul maxim pt ruta poate fi maxim 2* cost MST (mst nu poate fi mai mare decat ruta optima)

    //construiesc arbore mst
    //fac dfs pe arbore
    //ordinea in care vizitez intersectiile devine ruta
    //la final te intorci la pct de plecare
    public Route findRoute() {

        Kruskal kruskal = new Kruskal(city);
        Solution mst = kruskal.findSolution();
        List<Street> mstStreets = mst.getStreets();

        Map<Intersection, List<Intersection>> mstMap = new HashMap<>();//intersectie cu lista vecinilor(intersectii) ei din mst

        for (Intersection intersection : city.getIntersections()) {
            mstMap.put(intersection, new ArrayList<>());
        }

        //daca un capat din strada se afla in map=> adaug celalalt capat de strada drept vecin
        for (Street street : mstStreets) {

            Intersection intersection1 = street.getIntersection1();
            Intersection intersection2 = street.getIntersection2();

            mstMap.get(intersection1).add(intersection2);
            mstMap.get(intersection2).add(intersection1);
        }

        List<Intersection> routeIntersections = new ArrayList<>();
        List<Intersection> visited = new ArrayList<>();//pt dfs

        Intersection startIntersection = city.getIntersections().get(0);//aleg intersectia din care incep

        dfs(startIntersection, mstMap, visited, routeIntersections);

        routeIntersections.add(startIntersection);//adaug intersectia de start la finalul rutei

        int totalLength = 0;

        for (int indexI = 0; indexI < routeIntersections.size() - 1; indexI++){

            //iau cate 2 intersectii una dupa alta pt a forma o strada si a indexI lua costul
            Intersection intersection1 = routeIntersections.get(indexI);
            Intersection intersection2 = routeIntersections.get(indexI + 1);

            for (Street street : city.getStreets()){
                if ((street.getIntersection1().equals(intersection1) && street.getIntersection2().equals(intersection2)) ||
                        (street.getIntersection2().equals(intersection1) && street.getIntersection1().equals(intersection2))) {

                    totalLength = totalLength + street.getLength();
                }
            }
        }
        return new Route(routeIntersections, totalLength);//construiesc ruta cu intersectii si costul

    }


    private void dfs(Intersection currentIntersection, Map<Intersection, List<Intersection>> mstMap, List<Intersection> visited, List<Intersection> routeIntersections){
        visited.add(currentIntersection);
        routeIntersections.add(currentIntersection);

        for (Intersection nextIntersection : mstMap.get(currentIntersection)) {//merg prin lista de intersectii vecine cu  intersectia curenta
            if (!visited.contains(nextIntersection)) {//daca vecinul intersectiei curente n a fost inca vizitat
                dfs(nextIntersection, mstMap, visited, routeIntersections);//merg cu dfs in continuare
            }
        }
    }


}

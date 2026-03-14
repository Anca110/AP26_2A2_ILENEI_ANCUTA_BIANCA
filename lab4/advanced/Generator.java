import java.util.*;

public class Generator {

    //generez un oras cu un nr de intersectii
    public City generate(int nrIntersection){

        Random random=new Random();//creaza un obiect care genereaza valori random

        List<Intersection> intersections=new ArrayList<>();
        List<Street> streets=new ArrayList<>();
        Map<Intersection, List<Street>> cityMap=new HashMap<>();

        //pt fiecare intersectie ii pastrez coordonate
        //pt ca lungimea unei strazi va fi calculata ca distanta dintre 2 pct
        //pt a respecta inegalitatea triunghiului
        Map<Intersection, int[]> xyMap = new HashMap<>();

        //generez intersectiile
        for (int indexI = 0; indexI < nrIntersection; indexI++){

            Intersection intersection=new Intersection("Intersection"+indexI);
            intersections.add(intersection);

            int x=random.nextInt(100);
            int y= random.nextInt(100);

            //initizalizez map ul cu o lista goala noua de coordonate
            xyMap.put(intersection, new int[]{x,y});
            cityMap.put(intersection, new ArrayList<>());//initializez lista de strazi pt aceasta intersectie
        }

        //generez strazile pt fiecare pereche de intersectii
        for(int indexI=0; indexI<intersections.size(); indexI++){
            for(int indexJ=indexI+1; indexJ<intersections.size(); indexJ++){

                Intersection intersection1= intersections.get(indexI);
                Intersection intersection2=intersections.get(indexJ);

                int[] xyIntersection1=xyMap.get(intersection1);//in map pt intersectia respectiva imi da coordonatele
                int[] xyIntersection2=xyMap.get(intersection2);

                int dx=xyIntersection1[0]-xyIntersection2[0];
                int dy=xyIntersection1[1]-xyIntersection2[1];
                int length=dx*dx + dy*dy;

                Street street=new Street("Street"+indexI+indexJ, length, intersection1, intersection2);
                streets.add(street);

                cityMap.get(intersection1).add(street);
                cityMap.get(intersection2).add(street);

            }
        }
        return new City(intersections, streets, cityMap);
    }
}

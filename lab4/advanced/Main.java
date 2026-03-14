import java.util.*;
import java.util.stream.IntStream;

import static java.util.Locale.filter;

public class Main {
    public static void main(String[] args) {

        //generare nume fake cu noua librarie
        //Faker e clasa din biblioteca externa javaFaker pe care am adaugat o in pom.xml
        //faker.adress() da un obiect de tip Adress
        //clasa Adress are metoda streetName() care genereaza nume random
        Intersection[] intersections1=new Intersection[10];

        IntStream.range(0,10).forEach(i-> {
            intersections1[i]=new Intersection("Intersection"+ i);
        });


        //am creat un obiect Faker cu care gener nume aleatorii pt obiecte

        Street s1 = new Street("Street1", 10, intersections1[0], intersections1[1]);
        Street s2 = new Street("Street2", 5, intersections1[1], intersections1[2]);
        Street s3 = new Street("Street3", 15, intersections1[2], intersections1[3]);
        Street s4 = new Street("Street4", 7, intersections1[3], intersections1[4]);
        Street s5 = new Street("Street5", 12, intersections1[1], intersections1[3]);
        Street s6 = new Street("Street6", 8, intersections1[1], intersections1[4]);
        Street s7 = new Street("Street7", 11, intersections1[2], intersections1[4]);

        //creez lista de strazi
        LinkedList<Street> streets = new LinkedList<>();

        streets.add(s1);
        streets.add(s2);
        streets.add(s3);
        streets.add(s4);
        streets.add(s5);
        streets.add(s6);
        streets.add(s7);



        //sortare strazi dupa lungime
        //am folosit comparator( lambda); regula de comparare externa
        //daca nu are comparator extern=> foloseste automat compareTo
        streets.sort((a,b)-> a.getLength()-b.getLength());



        //o lista permite duplicate; un Set nu permite
        //e o colectie de obiecte
        HashSet<Intersection> intersections2=new HashSet<>();

        //adaug intersectiile din vector in set
        for(int indexI = 0; indexI < intersections1.length; indexI++){
            intersections2.add(intersections1[indexI]);
        }
        //adaug duplicat
        intersections2.add(intersections1[0]);



        //HOMEWORK
        //filtrez lista de strazi a.i pastrez doar strazile cu length> val si pt care una dintre intersectii are cel putin 3 strazi

        //lista de intersectii pt oras
        List<Intersection> intersectionList=new LinkedList<>();
        for(int indexI=0; indexI<intersections1.length; indexI++){
            intersectionList.add(intersections1[indexI]);
        }

        //mapa pt oras (pt fiecare intersectie creez o lista goala )
        Map<Intersection, List<Street>> cityMap=new HashMap<>();
        for(int indexI=0; indexI<intersections1.length; indexI++){
            cityMap.put(intersections1[indexI], new LinkedList<>());
        }

        //merg prin toate strazile si pt cate o strada pt intersectiile ei, adaug strada respectiva in mapa
        //completez acele liste goale pt fiecare intersectie cu strazile
        for(Street street : streets){
            cityMap.get(street.getIntersection1()).add(street);
            cityMap.get(street.getIntersection2()).add(street);
        }

        //creez oras
        City city=new City(intersectionList, streets, cityMap );
        System.out.println(city);
        System.out.println();

        int valLength=6;
        System.out.println("strazi cu lungime mai mare de "+ valLength+ " si care unesc cel putin 3 strazi: ");


        city.getStreets().stream()//transfrom lista de strazi intr un stream
                .filter(street-> street.getLength()> valLength)
                //pastrez strazile care s conectate la o intersectie la care se intalnesc cel putin 3 strazi
                //verific pt fiecare capat al strazii (cele 2 intersectii) sa contina minim 3 strazi
                .filter(street-> {
                    Intersection intersection1=street.getIntersection1();
                    Intersection intersection2=street.getIntersection2();

                    int nrStrazi1=city.getCityMap().get(intersection1).size();
                    int nrStrazi2=city.getCityMap().get(intersection2).size();

                    return nrStrazi1>=3 || nrStrazi2>=3;
                })
                .forEach(street-> System.out.println(street));


        System.out.println();

        Kruskal kruskal = new Kruskal(city);
        List<Solution> solutions = kruskal.findSolutions(3);

        System.out.println("solutii:");
        for (Solution solution : solutions) {
            System.out.println(solution);
        }



        //ADVANCE
        System.out.println();

        Algorithm algorithm = new Algorithm(city);
        Route route = algorithm.findRoute();
        System.out.println(route);



        System.out.println();
        Generator generator=new Generator();
        City city1=generator.generate(10);

        Algorithm algorithm1=new Algorithm(city1);
        Route route1=algorithm1.findRoute();
        System.out.println(route1);




    }
}
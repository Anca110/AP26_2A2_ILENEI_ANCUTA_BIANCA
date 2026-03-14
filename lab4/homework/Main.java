import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {

        //generare nume fake cu noua librarie
        //Faker e clasa din biblioteca externa javaFaker pe care am adaugat o in pom.xml
        //faker.adress() da un obiect de tip Adress
        //clasa Adress are metoda streetName() care genereaza nume random
        Faker faker=new Faker();
        Intersection[] intersections1=new Intersection[10];

        IntStream.range(0,10).forEach(i-> {
            intersections1[i]=new Intersection(faker.address().streetName());
        });


        //am creat un obiect Faker cu care gener nume aleatorii pt obiecte

        Street s1 = new Street(faker.address().streetName(), 10, intersections1[0], intersections1[1]);
        Street s2 = new Street(faker.address().streetName(), 5, intersections1[1], intersections1[2]);
        Street s3 = new Street(faker.address().streetName(), 15, intersections1[2], intersections1[3]);
        Street s4 = new Street(faker.address().streetName(), 7, intersections1[3], intersections1[4]);
        Street s5 = new Street(faker.address().streetName(), 12, intersections1[1], intersections1[3]);
        Street s6 = new Street(faker.address().streetName(), 8, intersections1[1], intersections1[4]);
        Street s7 = new Street(faker.address().streetName(), 11, intersections1[2], intersections1[4]);

        //creez lista de strazi
        LinkedList<Street> streets = new LinkedList<>();

        streets.add(s1);
        streets.add(s2);
        streets.add(s3);
        streets.add(s4);
        streets.add(s5);
        streets.add(s6);
        streets.add(s7);


        Collections.sort(streets, (a,b)->Integer.compare(a.getLength(), b.getLength()));





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













    }
}
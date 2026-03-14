import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {

        Intersection[] intersections1 =new Intersection[10];
        //vector  colectie cu dimensiune fixa

        IntStream.range(0,10).forEach(i -> {
            intersections1[i] = new Intersection("Intersection"+i);
        });



        Street s1 = new Street("Street1", 10, intersections1[0], intersections1[1]);
        Street s2 = new Street("Street2", 5, intersections1[1], intersections1[2]);
        Street s3 = new Street("Street3", 15, intersections1[2], intersections1[3]);
        Street s4 = new Street("Street4", 7, intersections1[3], intersections1[4]);

        //creez lista de strazi
        LinkedList<Street> streets = new LinkedList<>();

        streets.add(s1);
        streets.add(s2);
        streets.add(s3);
        streets.add(s4);


        //sortare strazi dupa lungime
        //am folosit comparator( lambda); regula de comparare externa
        //daca nu are comparator extern=> foloseste automat compareTo
        //lambda e versiune scurta a metodei compare; e functie fara nume pe care o scrii direct unde ai nevoie

        Collections.sort(streets, (a,b)->Integer.compare(a.getLength(), b.getLength()));
        for(int indexI=0; indexI<streets.size(); indexI++){
            System.out.println(streets.get(indexI));
        }


        //o lista permite duplicate; un Set nu permite
        //e o colectie de obiecte
        HashSet<Intersection> intersections2=new HashSet<>();

        //adaug intersectiile din vector in set
        for(int indexI = 0; indexI < intersections1.length; indexI++){
            intersections2.add(intersections1[indexI]);
        }
        //adaug duplicat
        intersections2.add(intersections1[0]);

        for(int indexI = 0; indexI < intersections1.length; indexI++){
            if(intersections2.contains(intersections1[indexI])){
                System.out.println(intersections1[indexI]);
            }
        }




    }
}
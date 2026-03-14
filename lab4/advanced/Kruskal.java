import java.util.*;

public class Kruskal {

private City city;

    public Kruskal(City city) {
        this.city = city;
    }


    public Solution findSolution() {

        //copiez strazile intr o lista noua pt a le sorta
        List<Street> sortedStreets = new ArrayList<>(city.getStreets());
        Collections.sort(sortedStreets);//apeleaza automat compareTo din Street pt ca am facut o comparabila => stie automat cum sa sorteze

        //pt fiecare intersectie tin minte in ce grup se afla
        List<List<Intersection>> intersectionGroups = new ArrayList<>();

        //initializez grupurile
        //fiecare intersectie incepe intr un grup separat
        for (Intersection intersection : city.getIntersections()) {

            List<Intersection> intersectionGroup = new ArrayList<>();

            intersectionGroup.add(intersection);//adaug intersectia in grup
            intersectionGroups.add(intersectionGroup);//adaug grupul in lista de grupuri
        }

        List<Street> choosenStreets = new ArrayList<>();
        int totalCost = 0;

        //iau cate o strada din cele ordonate
        for (Street street : sortedStreets) {

            //daca mai am nevoie de strazi pt arborele minim
            if (choosenStreets.size() < city.getIntersections().size() - 1) {

                Intersection intersection1 = street.getIntersection1();
                Intersection intersection2 = street.getIntersection2();

                List<Intersection> group1 = findGroup(intersectionGroups, intersection1);
                List<Intersection> group2 = findGroup(intersectionGroups, intersection2);

                //daca sunt in grupuri diferite;
                // intersectiile strazii sunt in grupuri diferite=> nu exista drum deja intre ele=> pot adauga strada;
                // daca nu inseamna ca formeaza cicluri
                //am un grup de intersectii pe care l tot maresc pe masura ce adaug strazi
                if (group1 != group2) {
                    choosenStreets.add(street);
                    totalCost = totalCost + street.getLength();

                    //unesc grupurile
                    group1.addAll(group2);
                    intersectionGroups.remove(group2);
                }
            }
        }
        return new Solution(choosenStreets, totalCost);
    }




    public List<Solution> findSolutions(int k) {

        List<Solution> solutions = new ArrayList<>();

        for(int indexI=0; indexI<k; indexI++) {

            //copiez strazile intr o lista noua pt a le sorta
            List<Street> sortedStreets = new ArrayList<>(city.getStreets());
            Collections.sort(sortedStreets);//apeleaza automat compareTo din Street pt ca am facut o comparabila => stie automat cum sa sorteze

            //pt fiecare intersectie tin minte in ce grup se afla
            List<List<Intersection>> intersectionGroups = new ArrayList<>();

            //initializez grupurile
            //fiecare intersectie incepe intr un grup separat
            for (Intersection intersection : city.getIntersections()) {

                List<Intersection> intersectionGroup = new ArrayList<>();

                intersectionGroup.add(intersection);//adaug intersectia in grup
                intersectionGroups.add(intersectionGroup);//adaug grupul in lista de grupuri
            }

            List<Street> choosenStreets = new ArrayList<>();
            int totalCost = 0;

            //iau cate o strada din cele ordonate
            for (int indexJ = 0; indexJ < sortedStreets.size(); indexJ++) {
                Street street = sortedStreets.get(indexJ);

                //cand fac solutia indexI=> sar peste strada indexJ=> ies cu solutii dif
                if (indexJ == indexI){
                    continue;
                }

                //daca mai am nevoie de strazi pt arborele minim
                if (choosenStreets.size() < city.getIntersections().size() - 1) {

                    Intersection intersection1 = street.getIntersection1();
                    Intersection intersection2 = street.getIntersection2();

                    List<Intersection> group1 = findGroup(intersectionGroups, intersection1);
                    List<Intersection> group2 = findGroup(intersectionGroups, intersection2);

                    //daca sunt in grupuri diferite;
                    // intersectiile strazii sunt in grupuri diferite=> nu exista drum deja intre ele=> pot adauga strada;
                    // daca nu inseamna ca formeaza cicluri
                    //am un grup de intersectii pe care l tot maresc pe masura ce adaug strazi
                    if (group1 != group2) {
                        choosenStreets.add(street);
                        totalCost = totalCost + street.getLength();

                        //unesc grupurile
                        group1.addAll(group2);
                        intersectionGroups.remove(group2);
                    }
                }
            }
            solutions.add(new Solution(choosenStreets, totalCost));
        }
        //ordonez solutiile dupa cost
        solutions.sort((a, b) -> a.getTotalCost() - b.getTotalCost());
        return solutions;

    }

    //verific in ce grup se afla intersectia
    private List<Intersection> findGroup(List<List<Intersection>> groups, Intersection intersection){
        List<Intersection> findGroup=new ArrayList<>();

        //merg prin toate grupurile
        for(List<Intersection> group : groups){
            if(group.contains(intersection)){
                 findGroup=group;
            }
        }
        return findGroup;
    }


}




//sortez strazile crescator dupa cost
//ia strazile pe rand de la cea mai ieftina la cea mai scumpa
//adaug cate o strada doar daca nu formeaza ciclu
//opreste cand s au conectat toate intersectiile

//daca 2 inters sunt in acelasi grup=> nu pot adauga; daca sunt in grupuri dif=>pot adauga strada
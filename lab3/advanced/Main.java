import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        SocialNetwork network = new SocialNetwork();

        Person anca = new Person(11, "Anca", "11.02.2005");
        Person salome = new Person(35, "Salome", "12.05.2003");
        Person ingrid = new Person(40, "Ingrid", "10.07.2006");

        Company amazon = new Company(7, "Amazon", "Iasi");
        Company centric = new Company(9, "Centric", "Cluj");
        Company bitdefender = new Company(10, "Bitdefender", "Bucuresti");

        network.addProfile(anca);
        network.addProfile(salome);
        network.addProfile(amazon);
        network.addProfile(centric);
        network.addProfile(ingrid);
        network.addProfile(bitdefender);

        anca.addRelationships(salome, "friends ");
        salome.addRelationships(amazon, "works");
        anca.addRelationships(amazon, "employees ");
        salome.addRelationships(bitdefender, "director ");
        salome.addRelationships(centric, "colaboration ");
        ingrid.addRelationships(salome, "friends");
        ingrid.addRelationships(amazon, "works");


        System.out.println(network);


        System.out.println("vecini anca:");
        List<Profile> veciniAnca = network.getNeighbors(anca);
        for (int indexI = 0; indexI < veciniAnca.size(); indexI++) {
            System.out.println(veciniAnca.get(indexI).getName());
        }

        System.out.println("vecini salome:");
        List<Profile> veciniSalome = network.getNeighbors(salome);
        for (int indexI = 0; indexI < veciniSalome.size(); indexI++) {
            System.out.println(veciniSalome.get(indexI).getName());
        }

        System.out.println("vecini centric:");
        List<Profile> veciniCentric = network.getNeighbors(centric);
        for (int indexI = 0; indexI < veciniCentric.size(); indexI++) {
            System.out.println(veciniCentric.get(indexI).getName());
        }

        System.out.println("vecini amazon:");
        List<Profile> veciniAmazon = network.getNeighbors(amazon);
        for (int indexI = 0; indexI < veciniAmazon.size(); indexI++) {
            System.out.println(veciniAmazon.get(indexI).getName());
        }


        List<Profile> critical = network.findArticulationPoints();
        System.out.println("noduri critice:");
        for (int indexI = 0; indexI < critical.size(); indexI++) {
            System.out.println(critical.get(indexI).getName());
        }


        System.out.println();
        System.out.println("componente:");

        List<List<Profile>> components = network.findBiconectedComponents();

        for (int indexI = 0; indexI < components.size(); indexI++) {
            List<Profile> component = components.get(indexI);
            for (int indexJ = 0; indexJ < component.size(); indexJ++) {
                System.out.print(component.get(indexJ).getName() + " ");
            }
            System.out.println();

        }


    }
}
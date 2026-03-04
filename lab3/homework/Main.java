import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDate;

public class Main{
    public static void main(String[] args){

        SocialNetwork network= new SocialNetwork();

        Person anca=new Person(11, "Anca", "11.02.2005");
        Person salome=new Person(35,"Salome", "12.05.2003");

        Company amazon=new Company(7, "Amazon", "Iasi");
        Company centric=new Company(9, "Centric", "Cluj");
        Company bitdefender=new Company(10, "Bitdefender", "Bucuresti");

        network.addProfile(anca);
        network.addProfile(salome);
        network.addProfile(amazon);
        network.addProfile(centric);
        network.addProfile(bitdefender);

        anca.addRelationships(salome, "friends ");
        anca.addRelationships(amazon, "employees ");
        salome.addRelationships(centric, "director ");



        System.out.println(network);


    }
}
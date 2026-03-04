import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main{
    public static void main(String[] args){
        ArrayList<Profile> profiles= new ArrayList<>();

        profiles.add(new Person(11, "Anca"));
        profiles.add(new Person(35,"Salome"));

        profiles.add(new Company(7, "Amazon"));
        profiles.add(new Company(9, "Centric"));
        profiles.add(new Company(10, "Bitdefender"));

        profiles.sort((profile1, profile2) -> profile1.getName().compareTo(profile2.getName()));


        for(int i=0; i<profiles.size(); i++){
            Profile profile1=profiles.get(i);
            System.out.println(profile1.getName());
        }
    }
}
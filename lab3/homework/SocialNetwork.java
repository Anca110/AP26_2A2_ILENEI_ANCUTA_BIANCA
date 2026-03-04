import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

public class SocialNetwork {

    private List<Profile> profiles=new ArrayList<>();

    public SocialNetwork(){}

    public List<Profile> getProfiles(){
        return profiles;
    }

    public void addProfile(Profile profile){
        profiles.add(profile);
    }


    /**
     * Daca profile este Person => numar relatiile sale
     * Daca profile este Company => pentru fiecare persoana verific daca se afla in relatie cu Company si o numar
     * @param profile
     * @return importanta fiecarui profil
     */
    public int Importance(Profile profile){
        if(profile instanceof Person){
            return ((Person) profile).getRelationships().size();
        }

        int nr=0;
        for(int i=0; i<profiles.size(); i++){
            if(profiles.get(i) instanceof Person){
                Person persoana= (Person)profiles.get(i);

                if(persoana.getRelationships().containsKey(profile)){
                    nr++;
                }
            }
        }
        return nr;
    }


    /**
     * sortez lista de profile in functie de importanta fiecarui profil
     * @return un text care reprezinta fiecare profil cu importanta sa
     */
    @Override
    public String toString(){
        Comparator<Profile> comparator = new Comparator<Profile>(){
            @Override
            public int compare(Profile p1, Profile p2){
                if (Importance(p1) > Importance(p2)){
                    return -1;
                }
                if (Importance(p1) < Importance(p2)){
                    return 1;
                }
                return p1.getName().compareTo(p2.getName());
            }
        };
        profiles.sort(comparator);

        String sorted = "";
        for (int i = 0; i < profiles.size(); i++){
            Profile profil = profiles.get(i);
            sorted = sorted + profil.getName() + " cu importanta: " + Importance(profil) + "\n";
        }
        return sorted;
    }
}

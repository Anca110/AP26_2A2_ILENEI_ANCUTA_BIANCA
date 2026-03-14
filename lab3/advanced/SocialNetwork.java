import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class SocialNetwork {

    private List<Profile> profiles = new ArrayList<>();

    public SocialNetwork() {
    }

    public List<Profile> getProfiles() {
        return profiles;
    }

    public void addProfile(Profile profile) {
        profiles.add(profile);
    }


    /**
     * Daca profile este Person => numar relatiile sale
     * Daca profile este Company => pentru fiecare persoana verific daca se afla in relatie cu Company si o numar
     *
     * @param profile
     * @return importanta fiecarui profil
     */
    public int Importance(Profile profile) {
        if (profile instanceof Person) {
            return ((Person) profile).getRelationships().size();
        }

        int importance = 0;
        for (int indexI = 0; indexI < profiles.size(); indexI++) {
            if (profiles.get(indexI) instanceof Person) {
                Person persoana = (Person) profiles.get(indexI);

                if (persoana.getRelationships().containsKey(profile)) {
                    importance++;
                }
            }
        }
        return importance;
    }


    /**
     * sortez lista de profile in functie de importanta fiecarui profil
     *
     * @return un text care reprezinta fiecare profil cu importanta sa
     */
    @Override
    public String toString() {
        Comparator<Profile> comparator = new Comparator<Profile>() {
            @Override
            public int compare(Profile p1, Profile p2) {
                if (Importance(p1) > Importance(p2)) {
                    return -1;
                }
                if (Importance(p1) < Importance(p2)) {
                    return 1;
                }
                return p1.getName().compareTo(p2.getName());
            }
        };
        profiles.sort(comparator);

        String sorted = "";
        for (int indexI = 0; indexI < profiles.size(); indexI++) {
            Profile profil = profiles.get(indexI);
            sorted = sorted + profil.getName() + " cu importanta: " + Importance(profil) + "\n";
        }
        return sorted;
    }


    //pt un profil dat=> sa mi dea toti vecinii sai din retea
    public List<Profile> getNeighbors(Profile profile) {

        List<Profile> neighbors = new ArrayList<>();//aici salvez toate profilele conectate cu profilul dat

        //daca profilul e persoana => veciniit sunt profilele din relatiile directe
        if (profile instanceof Person) {
            Person person = (Person) profile;
            //iau toate profilele cu care are relatii(keySet() imi ia doar profilul, nu toata relatia)
            List<Profile> profileRelation = new ArrayList<>(person.getRelationships().keySet());

            for (int indexI = 0; indexI < profileRelation.size(); indexI++) {
                Profile currentProfile = profileRelation.get(indexI);

                if (!neighbors.contains(currentProfile)) {//verific daca este deja in lista sa nu apara de 2 ori
                    neighbors.add(currentProfile);//adaug veciniul pt profile dat
                }
            }

        }

        //caut cine are relatie cu profilul primit
        //verifica pt companii cu cn se afla in relatie
        //sau pt persoane (daca initial am verificat anca->salome; aici verific relatia salome->anca)
        for (int indexI = 0; indexI < profiles.size(); indexI++) {
            Profile currentProfile = profiles.get(indexI);
            if (currentProfile instanceof Person) {
                Person person = (Person) currentProfile;

                if (person.getRelationships().containsKey(profile)) {
                    if (!neighbors.contains(person)) {
                        neighbors.add(person);
                    }
                }
            }
        }

        return neighbors;
    }


    private int time;

    //porneste DFS din fiecare profil nevisitat
    //intoarce toate profilele/nodurile critice
    public List<Profile> findArticulationPoints() {
        //alg Tarjan ( cu DFS)
        //verific daca un nod este singura punte intre 2 zone ale grafului (pornesc dintr un nod, merg cat de adanc, ma intorc)

        List<Profile> articulationPoints = new ArrayList<>();

        Map<Profile, Boolean> visited = new HashMap<>();//daca un profil a fost deja vizitat in DFS ( Anca -> false)
        Map<Profile, Integer> discoverTime = new HashMap<>();//momentul cand am ajuns prima data intr un nod; anca descoperita la timp 1
        Map<Profile, Integer> minTimeReach = new HashMap<>();//care este cel mai mic discoverTime la care pot ajunge din nodul acesta sau subarborele lui
        //adica daca poate nodul respectiv sau copii lui sa ajunga la un nod descoperit mai devreme (daca este drum alternativ)
        //daca un nod are drum alternativ(pot sa ma duc la un nod cu timp mai mic) => nodul parinte nu e critic (parinte are timp mai mare)
        //daca parinte avea timp mai mic decat nodul maxim la care pot sa ma duc=> era critic
        //pt un nod imi da cel mai vechi(cu timp mic) nod la care pot ajunge eu sau copii mei
        Map<Profile, Profile> parent = new HashMap<>();

        for (int indexI = 0; indexI < profiles.size(); indexI++) {
            Profile currentProfile = profiles.get(indexI);
            visited.put(currentProfile, false);
            parent.put(currentProfile, null);
        }
        time = 0;

        for (int indexI = 0; indexI < profiles.size(); indexI++) {
            Profile currentProfile = profiles.get(indexI);

            if (visited.get(currentProfile) == false) {
                dfsArticulationPoints(currentProfile, visited, discoverTime, minTimeReach, parent, articulationPoints);
            }
        }
        return articulationPoints;
    }

    private void dfsArticulationPoints(Profile currentProfile, Map<Profile, Boolean> visited, Map<Profile, Integer> discoverTime, Map<Profile, Integer> minTimeReach, Map<Profile, Profile> parent, List<Profile> articulationPoints) {

        visited.put(currentProfile, true);//marchez ca am vizitat acest nod
        discoverTime.put(currentProfile, time);//ii pun un timp
        minTimeReach.put(currentProfile, time);//timpul cel mai mic la care pot ajunge din acest nod
        time++;

        int child = 0;

        List<Profile> neighbors = getNeighbors(currentProfile);

        //parcurg vecinii unui nod
        for (int indexI = 0; indexI < neighbors.size(); indexI++) {
            Profile neighbor = neighbors.get(indexI);

            //daca vecinul nu a fost vizitat=> merg cu dfs prin el
            if (visited.get(neighbor) == false) {
                parent.put(neighbor, currentProfile);//pun in mapa currentProfile drept parinte pt acest vecin
                child++;//numar cati copii are nodul in arborele dfs

                //merg cu dfs mai departe din acest vecin
                dfsArticulationPoints(neighbor, visited, discoverTime, minTimeReach, parent, articulationPoints);

                //actualizez minTime pt nodul curent
                //copilul meu poate ajunge la un nod descoperit mai devreme decat mn => inseamna ca si eu pot ajunge
                if (minTimeReach.get(neighbor) < minTimeReach.get(currentProfile)) {
                    minTimeReach.put(currentProfile, minTimeReach.get(neighbor));
                }

                //daca nodul curent e radacina
                if (parent.get(currentProfile) == null && child > 1) {
                    if (!articulationPoints.contains(currentProfile)) {
                        articulationPoints.add(currentProfile);
                    }
                }

                //daca nodul curent nu e radacina si are mai multi copii=> daca il scoti automat G se rupe => nod critic
                if (parent.get(currentProfile) != null) {
                    if (minTimeReach.get(neighbor) >= discoverTime.get(currentProfile)) {
                        if (!articulationPoints.contains(currentProfile)) {
                            articulationPoints.add(currentProfile);//adaug nodul curent in lista de noduri critice
                        }
                    }
                }
            }
            //daca vecinul e deja vizitat => inseamna ca exista o muchie inapoi
            else if (neighbor != parent.get(currentProfile)) {
                if (discoverTime.get(neighbor) < minTimeReach.get(currentProfile)) {
                    //pot ajunge la un nod descoperit mai devreme
                    minTimeReach.put(currentProfile, discoverTime.get(neighbor));
                }
            }
        }
    }


    //o lista care contine liste mai mici
    //listele mai mici sunt componentele biconecte ( formate din noduri a.i daca scot un nod dintre ele=> nu se deconecteaza)
    public List<List<Profile>> findBiconectedComponents() {

        List<List<Profile>> components = new ArrayList<>();

        //gasesc punctele de articulatie
        List<Profile> articulationPoints = findArticulationPoints();

        Map<Profile, Boolean> visited2 = new HashMap<>();

        //initializez toate nodurile ca nevisitate
        for (int indexI = 0; indexI < profiles.size(); indexI++) {
            Profile currentProfile = profiles.get(indexI);
            visited2.put(currentProfile, false);
        }

        //parcurg toate profilele
        for (int indexI = 0; indexI < profiles.size(); indexI++) {

            Profile currentProfile = profiles.get(indexI);

            //pornesc dfs din nodurile care nu sunt pct de articulatie
            if (visited2.get(currentProfile) == false && !articulationPoints.contains(currentProfile)) {
                //daca n a fost inca vizitat=> acest profil apartine unei componente inca neconstruite

                List<Profile> component = new ArrayList<>();

                //visitez profilele din cate o componenta
                dfsComponent(currentProfile, visited2, articulationPoints, component);

                //dupa ce am gasit componenta fara pct articulatie adaug pct artic care s  vecine cu aceasta componenta
                List<Profile> copyComponent = new ArrayList<>(component);

                for (int indexJ = 0; indexJ < copyComponent.size(); indexJ++) {
                    Profile node = copyComponent.get(indexJ);

                    List<Profile> neighbors = getNeighbors(node);

                    for (int indexK = 0; indexK < neighbors.size(); indexK++) {
                        Profile neighbor = neighbors.get(indexK);

                        if (articulationPoints.contains(neighbor) && !component.contains(neighbor)) {
                            component.add(neighbor);
                        }
                    }
                }

                components.add(component);
            }
        }
        return components;
    }


    //construiesc cate o componenta
    private void dfsComponent(Profile currentProfile, Map<Profile, Boolean> visited2, List<Profile> articulationPoints, List<Profile> component) {

        //marchez un nod vizitat si il adaug in componenta curenta
        visited2.put(currentProfile, true);
        if (!component.contains(currentProfile)) {
            component.add(currentProfile);
        }

        List<Profile> neighbors = getNeighbors(currentProfile);

        for (int indexI = 0; indexI < neighbors.size(); indexI++) {//parcurg vecinii nodului

            Profile neighbor = neighbors.get(indexI);

            //continui dfs daca vecinul n a fost vizit si nu e pct de articulatie
            if (visited2.get(neighbor) == false && !articulationPoints.contains(neighbor)) {
                dfsComponent(neighbor, visited2, articulationPoints, component);
            }
        }

    }


}

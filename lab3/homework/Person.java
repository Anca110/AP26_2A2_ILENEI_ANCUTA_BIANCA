import java.util.Map;
import java.util.HashMap;
public class Person implements Profile, Comparable<Person>{

    private int id;
    private String name;
    private String birthDate;

    private Map<Profile, String> relationships;

    public Person(int id, String name, String birthDate){
        this.id= id;
        this.name= name;
        this.birthDate=birthDate;

        this.relationships=new HashMap<>();//creaza o mapa noua
    }

    @Override
    public int getId(){
        return id;
    }
    @Override
    public String getName(){
        return name;
    }

    public String getBirthDate(){
        return birthDate;
    }

    public Map<Profile, String> getRelationships(){
        return relationships;
    }

    public void addRelationships(Profile profile, String relationship){
        relationships.put(profile, relationship);
    }


    @Override
    public int compareTo(Person other){
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString(){
        return "Person: id= "+ id+ ", name= "+ name +", birthDate= "+ birthDate;
    }
}
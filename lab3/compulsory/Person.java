public class Person implements Profile, Comparable<Person>{

    private int id;
    private String name;

    public Person(int id, String name){
        this.id= id;
        this.name= name;
    }

    //metode cerute de profile
    @Override
    public int getId(){
        return id;
    }
    @Override
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name= name;
    }

    //public interface Comparable<T> {
    //    int compareTo(T o);
    //}   =>interfata Comparable<>   =>trebuie implementata obligatoriu metoda compareTo
    @Override
    public int compareTo(Person other){
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString(){
        return "Person: id= "+ id+ ", name= '"+ name;
    }
}
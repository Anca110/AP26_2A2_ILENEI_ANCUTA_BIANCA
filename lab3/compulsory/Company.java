public class Company implements Profile, Comparable<Company>{
    private int id;
    private String name;

    public Company(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public int getId(){
        return id;
    }
    @Override
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public int compareTo(Company other){
        return this.name.compareTo(other.name);
    }
    //returneaza 0 daca s egale; <0 daca this vine inainte; >0 daca this vine dupa

    @Override
    public String toString(){
        return "Company: id= "+ id+ ", name= '"+ name;
    }
}
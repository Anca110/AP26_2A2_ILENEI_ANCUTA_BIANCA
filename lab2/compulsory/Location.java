package lab2_compulsory;

public class Location {
    private String name;
    private String type;
    private int x;
    private int y;

    public Location(){}

    public Location(String name, String type, int x, int y){
        this.name=name;
        this.type=type;
        this.x=x;
        this.y=y;
    }

    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type=type;
    }

    public int getX(){
        return this.x;
    }
    public void setX(int x){
        this.x=x;
    }

    public int getY(){
        return this.y;
    }
    public void setY(int y){
        this.y=y;
    }

    @Override
    public String toString(){
        return "Location: " + "name= " + name + " , type= " + type + " ,X= " + x + " ,Y= " + y;
    }





}

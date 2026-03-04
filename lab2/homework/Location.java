package lab2_homework;

/**
 * Reprezinta o locatie definita prin nume si coordonate x,y
 * Clasa abstracta sigilata care poate fi extinsa doar de clasele: Airport, City, GasStation
 */
public abstract sealed class Location permits City, Airport, GasStation{
    protected String name;
    protected int x;
    protected int y;

    /**
     * Constructor cu parametrii
     * @param name
     * @param x
     * @param y
     */
    public Location(String name, int x, int y){
        this.name=name;
        this.x=x;
        this.y=y;
    }


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
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
        return "Location: " + "name= " + name + " ,X= " + x + " ,Y= " + y;
    }

    /**
     * Compara doua locatii prin numele acestora
     * @param obj   obiectul pe care il compar
     * @return true daca locatiile au acelasi nume
     */
    @Override
    public boolean equals(Object obj){
        if(obj==null || !(obj instanceof Location))
            return false;
        Location other= (Location) obj;
        return this.name.equals(other.name);
    }



}

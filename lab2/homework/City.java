package lab2_homework;

public final class City extends Location {
    private int population;

    public City(String name, int x, int y, int population) {
        super(name, x, y);
        this.population=population;
    }

    public int getPopulation(){
        return population;
    }
    public void setPopulation(int population){
        this.population=population;
    }
    @Override
    public String toString(){
        return "City: " + "name= " + name + " ,X= " + x + " ,Y= " + y + ", population= "+ population;
    }

}

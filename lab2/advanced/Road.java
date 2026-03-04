package lab2_advanced;

/**
 * Drum intre 2 locatii
 *Definit prin: nume, tip, lungime, limita de viteza si 2 locatii (plecare si destinatie)
 */
public class Road {
    private String name;
    private RoadType type;
    private int length;
    private int speedLimit;

    private Location from;
    private Location to;

    public Road(){}

    /**
     * constructor cu parametrii
     * @param name
     * @param type
     * @param length
     * @param speedLimit
     * @param from
     * @param to
     */
    public Road(String name, RoadType type, int length, int speedLimit, Location from, Location to){
        this.name=name;
        this.type=type;
        this.length=length;
        this.speedLimit=speedLimit;
        this.from=from;
        this.to=to;
    }


    public RoadType getType() {
        return type;
    }

    public void setType(RoadType type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }
    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public Location getFrom() {
        return from;
    }
    public void setFrom(Location from) {
        this.from = from;
    }

    public Location getTo() {
        return to;
    }
    public void setTo(Location to) {
        this.to = to;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString(){
        return "Road: name= "+ name + " type= " + type + " , length= " + length + " ,speedLimit= " + speedLimit + " ,from= " + from + " ,to= "+ to;
    }


    /**
     * verifica daca lungimea drumului e cel putin egala cu distanta eulidiana dintre cele 2 locatii
     * @return true daca daca drumul este valid
     */
    public boolean validDistance(){
        int dx=from.getX()-to.getX();
        int dy=from.getY()-to.getY();

        if(this.length*this.length >=dx*dx + dy*dy )
            return true;
        return false;
    }

    /**
     * compara 2 drumuri pe baza numelui
     * @param obj   obicetul cu care se compara
     * @return true daca drumurile au acelasi nume
     */
    @Override
    public boolean equals(Object obj){
        if(obj==null || !(obj instanceof Road))
            return false;
        Road other= (Road)obj;
        return this.name.equals(other.name);
    }
}

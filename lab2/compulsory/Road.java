package lab2_compulsory;

public class Road {
    private String name;
    private RoadType type;
    private int length;
    private int speedLimit;

    private Location from;
    private Location to;

    public Road(){}

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

    //distanta dintre 2 locatii
    public boolean validDistance(){
        int dx=from.getX()-to.getX();
        int dy=from.getY()-to.getY();

        //length= sqrt(dx^2 + dy^2)
        if(this.length*this.length >=dx*dx + dy*dy )
            return true;
        return false;
    }
}

package lab2_advanced;

public final class GasStation extends Location {
    private int price;

    public GasStation(String name, int x, int y, int price) {
        super(name, x, y);
        this.price=price;
    }

    public int getPrice(){
        return price;
    }
    public void setPrice(int price){
        this.price=price;
    }
    @Override
    public String toString(){
        return "GassStation: " + "name= " + name + " ,X= " + x + " ,Y= " + y + ", price= "+ price;
    }

}

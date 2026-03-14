public class Street implements Comparable<Street> {
    private String name;
    private int length;
    private Intersection intersection1;
    private Intersection intersection2;

    public Street(String name, int length, Intersection intersection1, Intersection intersection2) {
        this.name = name;
        this.length = length;
        this.intersection1 = intersection1;
        this.intersection2 = intersection2;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public Intersection getIntersection1() {
        return intersection1;
    }

    public Intersection getIntersection2() {
        return intersection2;
    }

    @Override
    public String toString(){
        return name + ", length= "+ length +", intersection1= "+ intersection1+", intersection2= "+ intersection2;
    }

    //functie apelata automat in main ex collections.sort(streets)
    //compare e metoda din clasa Integer
    //metoda compareTo functioneaza doar pe obiect (int nu e obiect=> am transformat in Integer)
    @Override
    public int compareTo(Street other){
        return Integer.compare(this.length, other.length);
    }
}

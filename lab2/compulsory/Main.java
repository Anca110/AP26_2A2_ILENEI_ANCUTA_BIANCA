package lab2_compulsory;

public class Main{
    public static void main(String[] args){

        //constructor cu parametrii
        Location l1=new Location("Iasi", "City", 10, 20);
        System.out.println(l1);

        //constructor gol+ parametrii
        Location l2=new Location();
        l2.setName("Suceava");
        l2.setType("City");
        l2.setX(25);
        l2.setY(26);
        System.out.println(l2);

        //get
        System.out.println("Nume locatie: " + l2.getName());

        //drum intre cele 2 locatii
        Road r= new Road("D24", RoadType.EXPRESS, 30, 50, l1, l2);
        System.out.println(r);
        System.out.println("Drum valid sau nu: "+ r.validDistance());
    }
}
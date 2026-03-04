package lab2_homework;

public class Main{
    public static void main(String[] args){

        Problem problem1=new Problem();

        Location location1=new City("Suceava", 10, 20, 300000);
        Location location2=new Airport("IasiAirport", 25, 35, 400);

        problem1.addLocation(location1);
        problem1.addLocation(location2);

        Location location3=new City("Suceava", 12, 13, 30000);
        problem1.addLocation(location3);

        Road road1=new Road("A1", RoadType.EXPRESS, 12, 150, location1, location2);
        problem1.addRoad(road1);
        System.out.println(road1.toString());
        Road road2 = new Road("A1", RoadType.EXPRESS, 6, 90, location2, location1);
        problem1.addRoad(road2);

        System.out.println("Instanta problemei este valida: "+ problem1.validProblem());

        System.out.println("Este posibila deplasarea din Suceava catre IasiAirport: "+ problem1.canTravel(location1, location2));
    }
}
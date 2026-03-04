package lab2_advanced;

public class Main{
    public static void main(String[] args){

        Problem problem1=new Problem();

        Location location1=new City("Suceava", 10, 20, 300000);
        Location location3=new Airport("IasiAirport", 25, 35, 400);
        problem1.addLocation(location1);
        problem1.addLocation(location3);

        Location location2=new City("Pascani", 12, 25, 30000);
        problem1.addLocation(location2);

        Road road1=new Road("A1", RoadType.EXPRESS, 12, 150, location1, location2);
        problem1.addRoad(road1);
        System.out.println(road1.toString());
        Road road2 = new Road("A2", RoadType.EXPRESS, 6, 90, location2, location3);
        problem1.addRoad(road2);

        System.out.println("Instanta problemei este valida: "+ problem1.validProblem());

        System.out.println("Este posibila deplasarea din Suceava catre IasiAirport: "+ problem1.canTravel(location1, location2));



        System.out.println();



        Algorithm alg = new DijkstraAlgorithm(problem1);

        Solution sol1 = alg.solve(location1, location3, false);//cea mai scurta ruta
        System.out.println("Ruta gasita: " + sol1);

        Solution sol2 = alg.solve(location1, location3, true);//cea mai rapida ca timp
        System.out.println("Ruta gasita: " + sol2);




        System.out.println();

        Generator generator=new Generator();
        Problem problem2= generator.generate(500, 2000);

        System.out.println("Locatii generate: " + problem2.getLocationCount());
        System.out.println("Drumuri generate: " + problem2.getRoadCount());
        System.out.println("Prima locatie: " + problem2.getLocations()[0]);
        System.out.println("Ultima locatie: " + problem2.getLocations()[problem2.getLocationCount() - 1]);

        Algorithm algorithm= new DijkstraAlgorithm(problem2);

        Location from = problem2.getLocations()[0];
        Location to   = problem2.getLocations()[problem2.getLocationCount() - 1];

        long start = System.nanoTime();

        Solution sol = algorithm.solve(from, to, false);

        long end = System.nanoTime();

        System.out.println("Timp: " + (end - start) / 1_000_000.0);
        System.out.println("Ruta: " + sol);


    }
}
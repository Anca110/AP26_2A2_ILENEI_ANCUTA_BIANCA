package lab2_advanced;

import java.util.Random;

public class Generator {
    public Problem generate(int nLocations, int nRoads){
        Problem problem=new Problem();
        Random random=new Random();

        //creez locatii
        for(int i=0; i< nLocations; i++){
            int x=random.nextInt(100);
            int y=random.nextInt(100);

            Location location=new City("City"+i, x,y,100);
            problem.addLocation(location);
        }

        Location[] locations=problem.getLocations();
        int n=problem.getLocationCount();


        //creez drumuri intre 2 locatii random
        for(int i=0; i< nRoads; i++){
            int a=random.nextInt(n);
            int b=random.nextInt(n);
            if(a!=b){
                Location from=locations[a];
                Location to=locations[b];

                int length=1+random.nextInt(200);
                int speed=30+ random.nextInt(121);

                Road road=new Road("Road"+i, RoadType.HIGHWAY, length, speed, from, to);
                problem.addRoad(road);
            }
        }
        return problem;
    }
}

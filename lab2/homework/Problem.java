package lab2_homework;

/**
 * Instanta a problemei care contine un set de locatii si drumuri intre acestea
 */
public class Problem {
    private Location[] locations=new Location[100];
    private Road[] roads=new Road[100];
    private int locationCount=0;
    private int roadCount=0;

    public Problem(){};

    /**
     * adauga o locatie daca nu exista deja
     * @param location locatia care trebuie adaugata
     */
   public void addLocation(Location location){
       for (int i = 0; i < locationCount; i++) {
           if (locations[i].equals(location)) {
               System.out.println("Locatia: "+ location.getName()+ " deja exista in lista");
               return;
           }
       }
       locations[locationCount++] = location;
   }

    /**
     * adauga un drum daca nu exista deja
     * @param road drumul care trebuie adaugat
     */
    public void addRoad(Road road){
        for (int i = 0; i < roadCount; i++) {
            if (roads[i].equals(road)) {
                System.out.println("Drumul: "+ road.getName()+ " deja exista in lista");
                return;
            }
        }
        roads[roadCount++] = road;
    }

    /**
     * Verifica daca instanta problemei este valida
     * Daca fiecare drum are locatia de plecare si locatia destinatie existente
     * @return true daca problema este valida
     */
    public boolean validProblem(){
        for(int i=0; i<roadCount; i++){
            //pt un drum
            if(roads[i].getFrom()==null || roads[i].getTo()==null)
                return false;

            boolean exist = false;
            for (int j = 0; j < locationCount; j++) {
                if (locations[j].equals(roads[i].getFrom())) {
                    exist = true;
                }
            }
            if (!exist)
                return false;

            exist = false;
            for (int j = 0; j < locationCount; j++) {
                if (locations[j].equals(roads[i].getTo())) {
                    exist = true;
                }
            }
            if (!exist)
                return false;
        }
        return true;
    }


    /**
     * Verifica daca exista un drum intre 2 locatii
     * @param from locatia de plecare
     * @param to locatia destinatie
     * @return true daca deplasarea e posibila
     */
    public boolean canTravel(Location from, Location to) {

        if (from.equals(to))
            return true;

        boolean[] visited = new boolean[locationCount];

        return dfs(from, to, visited);
    }

    private boolean dfs(Location current, Location to, boolean[] visited) {

        if (current.equals(to))
            return true;

        int currentIndex = -1;

        for (int i = 0; i < locationCount; i++) {
            if (locations[i].equals(current)) {
                currentIndex = i;
            }
        }
        if (currentIndex == -1)
            return false;

        visited[currentIndex] = true;

        for (int i = 0; i < roadCount; i++) {
            Road r = roads[i];
            Location next = null;

            if (r.getFrom().equals(current)) {
                next = r.getTo();
            } else
                if (r.getTo().equals(current)) {
                next = r.getFrom();
            }

            if (next != null) {
                if (next.equals(to)) return true;

                int nextIndex = -1;
                for (int j = 0; j < locationCount; j++) {
                    if (locations[j].equals(next)) {
                        nextIndex = j;
                    }
                }

                if (nextIndex != -1 && !visited[nextIndex]) {
                    if (dfs(next, to, visited)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}

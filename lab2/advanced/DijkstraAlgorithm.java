package lab2_advanced;

/**
 * Algoritmul Dijkstra pentru a gasi cea mai scurta ruta (dupa lungime/timp).
 */
public class DijkstraAlgorithm extends Algorithm {

    public DijkstraAlgorithm(Problem problem) {
        super(problem);
    }

    @Override
    public Solution solve(Location from, Location to, boolean fastest) {

        //iau toate locatiile
        Location[] locations = problem.getLocations();
        int n = problem.getLocationCount();

        int indexFrom=-1;
        int indexTo=-1;

        for(int i=0; i<n; i++){
            if(locations[i].equals(from)){
                indexFrom=i;
            }
            if(locations[i].equals(to)){
                indexTo=i;
            }
        }

        double[] minCost = new double[n];//care este cel mai mic cost cunoscut pana la locatia i
        boolean[] fixedNod = new boolean[n];//am stabilit definitiv costul pt locaia i
        int[] parent = new int[n];//din ce nod am ajuns la i pe cel mai bun drum

        for (int i = 0; i < n; i++)//initializez
        {
            minCost[i] = Double.POSITIVE_INFINITY;
            fixedNod[i] = false;
            parent[i] = -1;
        }

        minCost[indexFrom]=0;//pt primul nod i am pus costul initial pana la el=0

        //la fiecare pas aleg un nod nou care nu are costul fixat inca si care are costul minim din toate cele nevizitate

        for(int i=0; i<n; i++){
            int nodAles=-1;
            double min=Double.POSITIVE_INFINITY;//caut cel mai mic cost(la inceput e infinit)

            //caut nodul nevizitat cu cost min
            for(int j=0; j<n; j++){
                if(fixedNod[j]==false){
                    if(minCost[j]<min){
                        min=minCost[j];
                        nodAles=j;
                    }
                }
            }
            if(nodAles==-1)//n am gasit un nod bun
                return null;
            fixedNod[nodAles]=true;//nodul i acum e fixat(face parte din ruta)

            //am ales nodul=> relaxarea vecinilor
            for(int j=0; j< n ; j++){
                if(fixedNod[j]==false) {
                    Road r=problem.findRoad(locations[nodAles], locations[j]);
                    if(r!=null) {
                        double costMuchie=r.getLength();//cat e costul de la nodul ales pana la un vecin

                        if(fastest==false)//am ales cea mai scurta ruta
                        {
                            costMuchie = r.getLength();
                        }
                            else {
                            costMuchie = r.getLength() / r.getSpeedLimit();
                        }

                        double costNou=minCost[nodAles] + costMuchie;

                        //daca noul cost e mai bun decat ce am pana acum=>actualizez
                        if(costNou<minCost[j]){
                            minCost[j]=costNou;
                            parent[j]=nodAles;
                        }
                    }

                }
            }

        }

        //daca nu exista drum pana la destinatie
        if(minCost[indexTo]== Double.POSITIVE_INFINITY){
            return new Solution(new Location[0], 0);        }

        //reconstruiesc ruta folosind parent(in care am retinut nodurile parinte de la fiecare nod din ruta)
        Location[] route=new Location[n];
        int length=0;

        int i=indexTo;
        while(i!=-1){
            route[length]=locations[i];
            length++;
            i=parent[i];
        }

        //inversez ruta ca sa fie from->to
        Location[] finalRoute=new Location[length];
        int k=0;
        for(int j=length-1; j>=0; j--) {
            finalRoute[k++] = route[j];
        }


        return new Solution(finalRoute, length);//intoarce la final dupa ce a calculat o solutie cu ruta( start->...->end)
    }
}
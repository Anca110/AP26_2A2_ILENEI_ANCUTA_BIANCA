package lab7.advanced.solver;

import lab7.advanced.dao.MovieDAO;
import lab7.advanced.model.Movie;
import lab7.advanced.model.MovieList;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


//ia toate filmele, vede care filme sunt legate prin actori comuni, incearca sa le puna in cat mai putine liste
//in aceiasi lista nu trb sa existe 2 filme legate
//listele ca mai echilibrate
public class MovieSolver {


    //Choco solver e pt ca sa nu mai scrii un algoritm de gasire a unei solutii
    //scrii doar regulile problemei si choco gaseste solutia
    //Model-tabla de jocș unde creezi vriabile, adaugi reguli
    private final MovieDAO movieDAO;

    public MovieSolver() {
        this.movieDAO = new MovieDAO();
    }

    //2 filme sunt legate daca au actor comun
    public boolean areRelated(Movie m1, Movie m2) {
        Set<Integer> actors1 = movieDAO.findActorsMovie(m1.getId());//multime de actori film1
        Set<Integer> actors2 = movieDAO.findActorsMovie(m2.getId());

        for (Integer actorId : actors1){
            if (actors2.contains(actorId)){
                return true;
            }
        }
        return false;
    }

    //primeste toate filmele si incearca sa gaseasca solutia finala
    //intoarce o lista cu liste de filme
    public List<MovieList> solve(List<Movie> movies, int minSize) {
        //daca nu exista filme n ai cum sa imparti
        if (movies.size()== 0) {
            return new ArrayList<>();
        }

        //o matrice care spune pt orice pereche de filme daca sunt sau nu legate
        boolean[][] related = relatedMatrix(movies);

        //vad daca pot pune toate filmele intr o lista, daca nu in 2, daca nu in 3...
        for (int k=1; k <=movies.size(); k++) {
            List<MovieList> solution = solveKLists(movies, related, k, minSize);
            //daca s a gasit o solutie pt toate filmele in k liste maopresc
            //se cauta k minim de liste
            if (solution != null) {
                return solution;
            }
        }
        return new ArrayList<>();
    }

    //related[0][1] = true film0 si film1 sunt legate
    private boolean[][] relatedMatrix(List<Movie> movies){

        int n = movies.size();
        boolean[][] related = new boolean[n][n];

        //treci prin toate perechile de filme
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if (areRelated(movies.get(i), movies.get(j))){
                    related[i][j] = true;
                    related[j][i] = true;
                }
            }
        }
        return related;
    }

    //verifica daca poate imparti filmele in exact k liste
    private List<MovieList> solveKLists(List<Movie> movies, boolean[][] related, int k, int minSize) {        int n = movies.size();

        //construiesc tabla de joc
        Model model = new Model("Movie grouping");

        //in ce lista merge filmul
        //listVars[0] = 1 filmul 0 merge în lista 1
        //IntVar e ca o variabila necunoscuta; x ∈ {0,1,2}
        //listVars inseamna ca filmul i poate fi in lista 0,1...k-1
        //nu stii inca raspunsul, dar solverul va gasi
        IntVar[] listVars = new IntVar[n];

        //pt fiecare film creez o variabila intre 0 si k-1. pt k=3 filmul poate fi in lista1,2 sau 3
        for (int i = 0; i < n; i++){
            listVars[i] = model.intVar("movie" + i, 0, k - 1);
        }

        //constrangeea principala
        //filme legate in liste diferite
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                if (related[i][j]){
                    //model.arithm(...) creaza o constrangere aritmetica
                    //variabila filmului i trebuie sa fie diferita de variabila filmului j
                    //.post() adaug aceasta constrangere in model
                    model.arithm(listVars[i], "!=", listVars[j]).post();
                }
            }
        }

        //echilibrare liste
        //listele trb sa aiba aceiasi dimensiune sau sa fie diferite cu cel mult 1
        //calculez cat de mari au voie sa fie listele
        int autoMin = n / k;
        int maxSize = (n % k == 0) ? autoMin : autoMin + 1;
        int actualMin = Math.max(autoMin, minSize + 1);

        //pt fiecare lista construiesc variabile 0/1
        //pt fiecare lista vad pt fiecare film daca este sau nu in lista
        //fiecare inList[] este o variabila 0/1
        for (int listIndex = 0; listIndex < k; listIndex++){
            IntVar[] inList = new IntVar[n];

            for (int i = 0; i < n; i++){
                inList[i] = model.intVar("inList"+ i+ listIndex, 0, 1);

                //daca filmul i e pus in listIndex atunci inList[i] = 1. daca nu e 0
                model.ifThen(model.arithm(listVars[i], "=", listIndex), model.arithm(inList[i], "=", 1));

                model.ifThen(model.arithm(listVars[i], "!=", listIndex), model.arithm(inList[i], "=", 0));
            }

            //nr filme din lista curenta
            //impun ca suma variabilelor inList sa fie egala cu count
            //ex daca pt lista 2 ai inList = [1,0,1,0,0] atunci suma e 2, adc are 2 filme
            IntVar count = model.intVar("count" + listIndex, actualMin, maxSize);            model.sum(inList, "=", count).post();
        }

        //solver ul efectiv
        //Choco insearca sa gaseasca valori pt toate variabilele astel incat toate constrangerile sa fie satisfacute
        Solver solver = model.getSolver();

        if (!solver.solve()) {
            return null;
        }//daca nu gaseste. nu se pot cu exact k liste

        //construiesc rezultaul final
        //obiectele finale List1, List2..
        List<MovieList> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(new MovieList("Lista" + (i + 1)));//i incepe de la 0
        }

        //pun filmele in lista
        //pt fiecare film aflu in ce lista l a pus solverul, il adaug in obiectul MovieList
        for (int i = 0; i < n; i++){
            int assignedList = listVars[i].getValue();
            result.get(assignedList).addMovie(movies.get(i));
        }

        return result;
    }
}
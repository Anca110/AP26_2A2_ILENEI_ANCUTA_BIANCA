package lab2_advanced;

/**
 * Clasa abstracta pentru algoritmi care gasesc o ruta intre doua locatii.
 */
public abstract class Algorithm {

    protected Problem problem;//clasele derivate pot folosi direct problem

    public Algorithm(Problem problem) {
        this.problem = problem;
    }

    public abstract Solution solve(Location from, Location to, boolean fastest);

}
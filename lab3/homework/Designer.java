import java.time.LocalDate;
public class Designer extends Person{
    private int nrProjects;

    public Designer(int id, String name, String birthDate, int nrProjects ){

        super(id, name, birthDate);
        this.nrProjects=nrProjects;
    }

    @Override
    public String toString(){
        return super.toString()+ ", nrProjects=" + nrProjects;
    }

}

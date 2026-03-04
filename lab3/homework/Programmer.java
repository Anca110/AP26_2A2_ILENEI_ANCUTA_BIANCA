import java.time.LocalDate;
public class Programmer extends Person{
    private String language;

    public Programmer(int id, String name, String birthDate, String language ){

        super(id, name, birthDate);
        this.language=language;
    }

    @Override
    public String toString(){
        return super.toString()+ ", language=" + language;
    }
}

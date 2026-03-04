package lab2_homework;

public final class Airport extends Location {
    private int nrTerminal;

    public Airport(String name, int x, int y, int nrTerminals) {
        super(name, x, y);
        this.nrTerminal=nrTerminals;
    }

    public int getTerminals(){
        return nrTerminal;
    }
    public void setTerminals(int nrTerminal){
        this.nrTerminal=nrTerminal;
    }
    @Override
    public String toString(){
        return "Airport: " + "name= " + name + " ,X= " + x + " ,Y= " + y + ", nrTerminal= "+ nrTerminal;
    }

}

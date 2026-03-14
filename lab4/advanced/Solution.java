import java.util.List;

public class Solution {
    private List<Street> streets;
    private int totalCost;

    public Solution(List<Street> streets, int totalCost) {
        this.streets = streets;
        this.totalCost = totalCost;
    }

    public List<Street> getStreets() {
        return streets;
    }

    public int getTotalCost() {
        return totalCost;
    }

    @Override
    public String toString(){
        return "Slution: streets= "+ streets+", cost total= "+ totalCost;
    }
}

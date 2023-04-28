import java.util.ArrayList;

public class StartUp {
    private ArrayList<String> locationCells;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocations(ArrayList<String> loc) {
        this.locationCells = loc;
    }

    public String checkYourSelf(String userInput) {
        String result = "miss";
        int index = locationCells.indexOf(userInput);
        if (index >= 0) {
            locationCells.remove(index);
            if (locationCells.isEmpty()) {
                result = "kill";
                System.out.println("Ouch! You sunk " + name + " :(" );
            } else {
                result = "hit";
            }
        }
        return result;
    }
}

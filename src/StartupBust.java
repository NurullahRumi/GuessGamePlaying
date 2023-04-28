import java.util.ArrayList;

public class StartupBust {

    private GameHelper helper = new GameHelper();
    private ArrayList<StartUp> startUps = new ArrayList<>();
    private int numOfGuesses = 0;

    private void setUpGame(){
        // first make some Startups and give them locations
        StartUp one = new StartUp();
        one.setName("poniez");
        StartUp two = new StartUp();
        two.setName("hacqi");
        StartUp three = new StartUp();
        three.setName("cabista");
        startUps.add(one);
        startUps.add(two);
        startUps.add(three);

        System.out.println("Your goal is to sink three Startups.");
        System.out.println("poniez, hacqi, cabista");
        System.out.println("Try to sink them all in the fewest number of guesses");

        for (StartUp startUp : startUps){
            ArrayList<String> newLocation = helper.placeStartup(3);
            startUp.setLocations(newLocation);
        }
    }

    private void startPlaying(){
        while (!startUps.isEmpty()){
            String userGuess = helper.getUserInput("Enter a guess");
            checkUserGuess(userGuess);
        }
        finishGame();
    }

    private void finishGame() {
        System.out.println("All Startups are dead! Your stock is now worthless");
        if(numOfGuesses <= 18){
            System.out.println("It only took you " + numOfGuesses + " guesses.");
            System.out.println("You got out before your options sank");
        }else {
            System.out.println("Took you long enough " + numOfGuesses + " guesses.");
            System.out.println("Fish are dancing with your options");
        }
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "miss";

        for (StartUp startUpToTest: startUps){
            result = startUpToTest.checkYourSelf(userGuess);

            if (result.equals("hit")){
                break;
            }
            if (result.equals("kill")){
                startUps.remove(startUpToTest);
                break;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        StartupBust game = new StartupBust();
        game.setUpGame();
        game.startPlaying();
    }
}

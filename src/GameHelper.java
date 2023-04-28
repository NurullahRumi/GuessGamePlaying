import constant.GameConstant;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameHelper {

    private final int[] grid = new int[GameConstant.GRID_SIZE];
    private final Random random = new Random();
    private int startupCount = 0;

    public String getUserInput(String txt) {
        System.out.println(txt + ": ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().toLowerCase();
    }

    public ArrayList<String> placeStartup(int startupSize) {
        // holds index to grid (0-48)
        int[] startupCoords = new int[startupSize];
        int attempts = 0;
        boolean success = false;

        startupCount++;
        int increment = getIncrement();

        while (!success & attempts++ < GameConstant.MAX_ATTEMPTS) {
            int location = random.nextInt(GameConstant.GRID_SIZE);

            for (int i = 0; i < startupCoords.length; i++) {
                startupCoords[i] = location;
                location += increment;
            }

            if (startupFits(startupCoords, increment)) {
                success = coordsAvailable(startupCoords);
            }
        }
        savePositionToGrid(startupCoords);

        ArrayList<String> alphaCells = convertCoordsToAlphaFormat(startupCoords);
        return alphaCells;
    }

    private boolean startupFits(int[] startupCoords, int increment) {
        int finalLocation = startupCoords[startupCoords.length - 1];
        if (increment == GameConstant.HORIZONTAL_INCREMENT) {
            //check end is on same row as start
            return calcRowFromIndex(startupCoords[0]) == calcRowFromIndex(finalLocation);
        } else {
            return finalLocation < GameConstant.GRID_SIZE;
        }
    }

    private boolean coordsAvailable(int[] startupCoords) {
        for (int coord : startupCoords) {
            if (grid[coord] != 0) {
                return false;
            }
        }
        return true;
    }

    private void savePositionToGrid(int[] startupCoords) {
        for (int index : startupCoords) {
            grid[index] = 1;
        }
    }

    private ArrayList<String> convertCoordsToAlphaFormat(int[] startupCoords) {
        ArrayList<String> alphaCells = new ArrayList<>();
        for (int index : startupCoords) {
            String alphaCoords = getAlphaCoordsFromIndex(index);
            alphaCells.add(alphaCoords);
        }
        return alphaCells;
    }

    private String getAlphaCoordsFromIndex(int index) {
        int row = calcRowFromIndex(index);
        int column = index % GameConstant.GRID_LENGTH;
        String letter = GameConstant.ALPHABET.substring(column, column + 1);
        return letter + row;
    }

    private int calcRowFromIndex(int index) {
        return index / GameConstant.GRID_LENGTH;
    }

    private int getIncrement() {
        if (startupCount % 2 == 0) {
            return GameConstant.HORIZONTAL_INCREMENT;
        } else {
            return GameConstant.VERTICAL_INCREMENT;
        }
    }
}

package khrystoforov;

import khrystoforov.fileutil.WorkWithFile;
import lombok.extern.java.Log;

import java.util.logging.Logger;

public class GameOfLife {
    private static final Logger log = Logger.getLogger(GameOfLife.class.getName());
    private static final String ALIVE = "X";
    private static final String DEAD = "O";

    /**
     * Start of program.
     *
     * @param fileNameInput  - input file name
     * @param fileNameOutput - output file name
     */
    public void game(String fileNameInput, String fileNameOutput) {
        String s = WorkWithFile.readFromFile(fileNameInput);
        String stringWithProperties = s.substring(0, s.indexOf(System.lineSeparator()));
        String[] arrayWithProperties = stringWithProperties.split(",");

        int width = Integer.parseInt(arrayWithProperties[0]);

        log.info("height: " + width);
        int height = Integer.parseInt(arrayWithProperties[1]);
        log.info("width: " + height);
        int seedCount = Integer.parseInt((arrayWithProperties[2]));
        log.info("seedCount: " + seedCount);

        String[] data = s.substring(stringWithProperties.length()
                + System.lineSeparator().length()).split("\\s+");

        World world = runTheWorld(new World(convertStringArrayToBooleanArray(data),
                seedCount, height, width));

        WorkWithFile.writeToFile(fileNameOutput, createStringFromArray(world));
    }

    private World runTheWorld(World world) {
        for (int i = 0; i < world.getMaxGeneration(); i++) {
            world.updateGeneration();
        }
        return world;
    }

    private String createStringFromArray(World world) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < world.getCurrentGeneration().getWidth(); i++) {
            for (int j = 0; j < world.getCurrentGeneration().getHeight(); j++) {
                String element = world.getCurrentGeneration().getCells()[i][j] ? ALIVE : DEAD;
                builder.append(element).append(" ");
            }

            builder.deleteCharAt(builder.length() - 1);
            builder.append("\r");
        }

        builder.deleteCharAt(builder.length() - 1);
        return builder.toString();
    }

    private boolean[] convertStringArrayToBooleanArray(String[] data) {
        boolean[] booleanData = new boolean[data.length];

        for (int i = 0; i < data.length; i++) {
            booleanData[i] = ALIVE.equals(data[i]);
        }
        return booleanData;
    }


}

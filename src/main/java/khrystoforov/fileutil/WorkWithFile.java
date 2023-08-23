package khrystoforov.fileutil;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

public class WorkWithFile {
    private static final Logger log = Logger.getLogger(WorkWithFile.class.getName());

    /**
     * Read from file.
     *
     * @param fileName - name
     * @return data from file
     */
    public static String readFromFile(String fileName) {
        try {
            Path path = new File("src/test/resources/" + fileName).toPath();

            return new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Write to file.
     *
     * @param fileName - file name
     * @param glider - glider
     */
    public static void writeToFile(String fileName, String glider) {
        File file = new File("target/test-classes/" + fileName);

        try {
            if (file.createNewFile()) {
               log.info("File is created!");
            } else {
                log.info("File already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(glider);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

package Utils;

import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class that writes to a JSON file
 */

public class FileIOHandler {
    public static void createDataFile(File file) {
        try {
            boolean success = file.createNewFile();
            if (success) {
                System.out.println("File was created");
            } else {
                System.out.println("The file was found");
            }

        } catch (IOException e) {
            System.out.println("Error while creating a file: " + e.getMessage());
        }
    }

    public static void writeToJSONFile(JSONObject jsonObject, String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(jsonObject.toString());
            bufferedWriter.close();

            System.out.println("Collection was successfully saved to the file. ");
        } catch (IOException e) {
            System.out.println("An error occurred while saving collection to the JSON-file.");
            System.out.println(e.getMessage());
        }
    }
}

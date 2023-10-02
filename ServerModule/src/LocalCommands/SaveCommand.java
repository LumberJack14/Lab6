package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.DragonToJSON;
import Utils.FileIOHandler;
import org.json.simple.JSONObject;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "save"
 */

@CommandInfo(name = "save", description = "save the collection to a file")
public class SaveCommand implements Command {
    @Override
    public void execute(List<String> tokens) {

        try {
            if (!tokens.isEmpty()) {
                throw new TokenMismatchException(0);
            }
        } catch (TokenMismatchException e) {
            System.out.println(e.getMessage());
            return;
        }

        CollectionManager cm = CollectionManager.getInstance();
        String path = cm.getPath();
        System.out.println("Saving to the file: " + path);
        Vector<DragonX> collection = cm.getCollection();
        JSONObject collectionJSON = DragonToJSON.createArrayInsideObject(collection);
        FileIOHandler.writeToJSONFile(collectionJSON, cm.getPath());
    }
}


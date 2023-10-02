package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.CommandWrapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "info"
 */

@CommandInfo(name = "info", description = "print information about the collection to standard output")
public class InfoCommand implements ClientCommand {
    @Override
    public String executeClient(CommandWrapper commandWrapper) {
        ArrayList<String> tokens = commandWrapper.getTokens();

        try {
            if (!tokens.isEmpty()) {
                throw new TokenMismatchException(0);
            }
        } catch (TokenMismatchException e) {
            return e.getMessage();
        }

        String response = "";
        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        DragonX oldestDragon = collection.stream().min(Comparator.comparing(DragonX::getCreationDate)).orElse(null);
        String initLine;
        if (oldestDragon == null) {
            initLine = "Collection doesn't have any items so it's not initialized";
        } else {
            initLine = oldestDragon.getCreationDate().toString();
        }

        DragonX newestDragon = collection.stream().max(Comparator.comparing(DragonX::getCreationDate)).orElse(null);
        String newestLine;
        if (newestDragon == null) {
            newestLine = "No items had been added yet";
        } else {
            newestLine = newestDragon.getName() + " (" + newestDragon.getCreationDate().toString() + ")";

        }

        response += "Collection type: Vector<Dragon>\n";
        response += "Initialization date: " + initLine + "\n";
        response += "Collection contains items: " + collection.size() + "\n";
        response += "Recently added item: " + newestLine + "\n";

        return response;
    }

    @Override
    public void execute(List<String> tokens) {

    }
}

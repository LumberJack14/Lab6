package LocalCommands;


import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.CommandWrapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "add"
 */

@CommandInfo(name = "add", description = "add a new element to the collection")
public class AddCommand implements ClientCommand {

    public void execute(List<String> tokens) {}

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

        CollectionManager collectionManager = CollectionManager.getInstance();

        DragonX dragon = commandWrapper.getDragon();
        Vector<DragonX> collection = collectionManager.getCollection();
        dragon.setId(collection.size());
        dragon.setCreationDate(LocalDate.now());
        collection.add(dragon);
        collectionManager.setCollection(collection);

        response += "New element has been successfully added!";
        return response;
    }
}

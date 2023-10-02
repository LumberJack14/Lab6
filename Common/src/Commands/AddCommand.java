package Commands;


import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "add"
 */

@CommandInfo(name = "add", description = "add a new element to the collection")
public class AddCommand implements Command {

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
        CollectionManager collectionManager = CollectionManager.getInstance();

        DragonX dragon = collectionManager.generateNewDragon();
        Vector<DragonX> collection = collectionManager.getCollection();
        collection.add(dragon);
        collectionManager.setCollection(collection);

        System.out.println("New element has been successfully added!");
    }
}

package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "remove_by_id"
 */

@CommandInfo(name = "remove_by_id", description = "remove an element from the collection by its id")
public class RemoveById implements Command{
    @Override
    public void execute(List<String> tokens) {
        try {
            if (tokens.size() != 1) {
                throw new TokenMismatchException(1);
            }
        } catch (TokenMismatchException e) {
            System.out.println(e.getMessage());
            return;
        }

        long givenID;

        try {
            givenID = Long.parseLong(tokens.get(0));
        } catch (NumberFormatException e) {
            System.out.println("Error while parsing ID" + e.getMessage());
            return;
        }

        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        boolean deleted = false;
        for (DragonX dragon: collection) {
            if (dragon.getId() == givenID) {
                collection.remove(dragon);
                System.out.println("Dragon with ID '" + givenID + "' was successfully removed");
                deleted = true;
                break;
            }
        }

        if (!deleted) {
            System.out.println("Couldn't find a dragon with provided ID.");
        }
    }
}

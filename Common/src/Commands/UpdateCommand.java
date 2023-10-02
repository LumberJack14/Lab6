package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "update"
 */

@CommandInfo(name = "update", description = "update the value of the collection element, which id is equal to the given one")
public class UpdateCommand implements Command{
    @Override
    public void execute(List<String> tokens) {
        long givenID;

        try {
            if (tokens.size() != 1) {
                throw new TokenMismatchException(1);
            }

            givenID = Long.parseLong(tokens.get(0));
        } catch (TokenMismatchException | NumberFormatException exception) {
            System.out.println(exception.getMessage());
            return;
        }


        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        boolean updated = false;
        for (DragonX dragon: collection) {
            if (dragon.getId() == givenID) {

                System.out.println("Dragon, which will be updated:\n");
                dragon.displayInfo();

                DragonX newDragon = cm.generateNewDragon();
                newDragon.setId(givenID);
                collection.remove(dragon);
                collection.add(newDragon);

                System.out.println("Dragon with ID '" + givenID + "' was successfully updated");
                updated = true;
                break;
            }
        }

        if (!updated) {
            System.out.println("Couldn't find a dragon with provided ID.");
        }


    }
}

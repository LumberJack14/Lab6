package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Class, that implements console command "remove_lower"
 */

@CommandInfo(name = "remove_lower", description = "Remove all elements from the collection that are smaller than the given one")
public class RemoveLowerCommand implements Command {

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
        Vector<DragonX> collection = cm.getCollection();

        DragonX dragonToCompare = cm.generateNewDragon();
        Vector<DragonX> toDelete = collection
                .stream()
                .filter(item -> item.compareTo(dragonToCompare) < 0)
                .collect(Collectors.toCollection(Vector::new));

        if (toDelete.isEmpty()) {
            System.out.println("Couldn't find lower dragons");
            return;
        }

        System.out.println("Dragons to delete: ");
        toDelete.forEach(DragonX::displayInfo);
        toDelete.forEach(collection::remove);

        System.out.println("Lower dragons were successfully removed!");
    }
}

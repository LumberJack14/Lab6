package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;

import java.util.Comparator;
import java.util.List;

/**
 * Class, that implements console command "max_by_name"
 */

@CommandInfo(name = "max_by_name", description = "output any object from the collection whose name field value is the maximum")
public class MaxByNameCommand implements Command {
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
        DragonX dragonWithLongestName = cm.getCollection().stream()
                .max(Comparator.comparing(dragon -> dragon.getName().length()))
                .orElse(null);

        if (dragonWithLongestName == null) {
            System.out.println("Couldn't find a dragon with the longest name");
        } else {
            dragonWithLongestName.displayInfo();
        }
    }
}

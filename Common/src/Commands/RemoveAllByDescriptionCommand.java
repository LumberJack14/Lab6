package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "remove_all_by_description"
 */

@CommandInfo(name = "remove_all_by_description", description = "remove from the collection all elements whose description field value is equivalent to the given one")
public class RemoveAllByDescriptionCommand implements Command {
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

        String subString = tokens.get(0);

        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        System.out.println("Dragons to be removed: ");

        Vector<DragonX> toDelete = new Vector<>();
        collection.stream().filter(item ->
                item.getDescription().startsWith(subString)
        ).forEach(item -> {
            item.displayInfo();
            toDelete.add(item);
        });

        toDelete.forEach(collection::remove);

        System.out.println("Found dragons were successfully removed");
    }
}

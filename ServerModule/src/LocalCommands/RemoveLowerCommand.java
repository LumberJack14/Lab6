package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.CommandWrapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Class, that implements console command "remove_lower"
 */

@CommandInfo(name = "remove_lower", description = "Remove all elements from the collection that are smaller than the given one")
public class RemoveLowerCommand implements ClientCommand {

    public void execute(List<String> tokens){}

    @Override
    public String executeClient(CommandWrapper commandWrapper) {
        List<String> tokens = commandWrapper.getTokens();

        try {
            if (!tokens.isEmpty()) {
                throw new TokenMismatchException(0);
            }
        } catch (TokenMismatchException e) {
            return e.getMessage();
        }

        StringBuilder sb = new StringBuilder();

        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        DragonX dragonToCompare = commandWrapper.getDragon();
        dragonToCompare.setId(collection.size());
        dragonToCompare.setCreationDate(LocalDate.now());

        Vector<DragonX> toDelete = collection
                .stream()
                .filter(item -> item.compareTo(dragonToCompare) < 0)
                .collect(Collectors.toCollection(Vector::new));

        if (toDelete.isEmpty()) {
            return "Couldn't find lower dragons";
        }

        sb.append("Dragons to delete: \n");
        toDelete.forEach(item -> {
            sb.append(item.returnInfo());
        });
        toDelete.forEach(collection::remove);
        sb.append("Lower dragons were successfully removed!");

        return sb.toString();
    }
}

package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.CommandWrapper;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "remove_all_by_description"
 */

@CommandInfo(name = "remove_all_by_description", description = "remove from the collection all elements whose description field value is equivalent to the given one")
public class RemoveAllByDescriptionCommand implements ClientCommand {

    public void execute(List<String> tokens) {}

    @Override
    public String executeClient(CommandWrapper commandWrapper) {

        List<String> tokens  = commandWrapper.getTokens();

        try {
            if (tokens.size() != 1) {
                throw new TokenMismatchException(1);
            }
        } catch (TokenMismatchException e) {
            return e.getMessage();
        }

        StringBuilder cb = new StringBuilder();

        String subString = tokens.get(0);

        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        cb.append("Dragons to be removed:\n");

        Vector<DragonX> toDelete = new Vector<>();
        collection.stream().filter(item ->
                item.getDescription().startsWith(subString)
        ).forEach(item -> {
            cb.append(item.returnInfo());
            toDelete.add(item);
        });
        toDelete.forEach(collection::remove);

        cb.append("Found dragons were successfully removed");
        return cb.toString();
    }
}

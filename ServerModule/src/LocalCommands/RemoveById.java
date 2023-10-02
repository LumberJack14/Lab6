package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.CommandWrapper;

import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Class, that implements console command "remove_by_id"
 */

@CommandInfo(name = "remove_by_id", description = "remove an element from the collection by its id")
public class RemoveById implements ClientCommand {

    public void execute(List<String> tokens) {
    }

    @Override
    public String executeClient(CommandWrapper commandWrapper) {

        List<String> tokens = commandWrapper.getTokens();

        try {
            if (tokens.size() != 1) {
                throw new TokenMismatchException(1);
            }
        } catch (TokenMismatchException e) {
            return e.getMessage();
        }

        long givenID;

        try {
            givenID = Long.parseLong(tokens.get(0));
        } catch (NumberFormatException e) {
            return "Error while parsing ID" + e.getMessage();
        }

        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        StringBuilder sb = new StringBuilder();

        Optional<DragonX> toDelete = collection.stream().filter(item -> item.getId() == givenID).findFirst();

        if (toDelete.isEmpty()) {
            return "Couldn't find a dragon with provided ID.";
        }

        sb.append("Dragon that will be deleted: \n").append(toDelete.get().returnInfo());
        collection.remove(toDelete.get());
        sb.append("Dragon with ID '").append(givenID).append("' was successfully removed\n");

        return sb.toString();
    }
}

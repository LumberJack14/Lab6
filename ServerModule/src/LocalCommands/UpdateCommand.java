package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.CommandWrapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Vector;

/**
 * Class, that implements console command "update"
 */

@CommandInfo(name = "update", description = "update the value of the collection element, which ID is equal to the given one")
public class UpdateCommand implements ClientCommand {

    public void execute(List<String> tokens) {

    }

    @Override
    public String executeClient(CommandWrapper commandWrapper) {
        List<String> tokens = commandWrapper.getTokens();
        long givenID;

        try {
            if (tokens.size() != 1) {
                throw new TokenMismatchException(1);
            }

            givenID = Long.parseLong(tokens.get(0));
        } catch (TokenMismatchException | NumberFormatException exception) {
            return exception.getMessage();
        }

        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        StringBuilder sb = new StringBuilder();

        DragonX newDragon = commandWrapper.getDragon();
        newDragon.setId(givenID);
        newDragon.setCreationDate(LocalDate.now());
        Optional<DragonX> toUpdate = collection.stream().filter(item -> item.getId() == givenID).findFirst();

        if (toUpdate.isEmpty()) {
            return "Couldn't find a dragon with provided ID.";
        }

        sb.append("Dragon before the update:\n");
        sb.append(toUpdate.get().returnInfo());
        collection.remove(toUpdate.get());
        collection.add(newDragon);
        sb.append("Dragon with ID '").append(givenID).append("' was successfully updated");

        return sb.toString();
    }
}

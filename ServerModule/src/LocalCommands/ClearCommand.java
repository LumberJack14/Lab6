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
 * Class, that implements console command "clear"
 */

@CommandInfo(name = "clear", description = "clear the collection")
public class ClearCommand implements ClientCommand {

    public void execute(List<String> tokens) {}

    public String executeClient(CommandWrapper cm) {
        List<String> tokens = cm.getTokens();

        try {
            if (!tokens.isEmpty()) {
                throw new TokenMismatchException(0);
            }
        } catch (TokenMismatchException e) {
            return e.getMessage();
        }

        CollectionManager collectionManager = CollectionManager.getInstance();
        collectionManager.setCollection(new Vector<DragonX>());

        return "Collection was cleared. If you want to save it to the file type 'save' ";
    }
}

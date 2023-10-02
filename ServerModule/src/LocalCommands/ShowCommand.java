package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.CommandWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "show"
 */

@CommandInfo(name = "show", description = "print to standard output all elements of the collection in string representation")
public class ShowCommand implements ClientCommand {

    public void execute(List<String> tokens) {}

    @Override
    public String executeClient(CommandWrapper cm) {
        ArrayList<String> tokens = cm.getTokens();
        StringBuilder response = new StringBuilder();

        try {
            if (!tokens.isEmpty()) {
                throw new TokenMismatchException(0);
            }
        } catch (TokenMismatchException e) {
            return e.getMessage();
        }

        CollectionManager collectionManager = CollectionManager.getInstance();
        Vector<DragonX> collection = collectionManager.getCollection();

        response.append("This is the collection's state at the current moment. Some changes might need to be saved.\n");
        for (DragonX dragon : collection) {
            response.append(dragon.returnInfo());
        }

        return response.toString();
    }
}

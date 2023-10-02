package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;
import Utils.CommandWrapper;

import java.util.Comparator;
import java.util.List;

/**
 * Class, that implements console command "max_by_name"
 */

@CommandInfo(name = "max_by_name", description = "output any object from the collection whose name field value is the maximum")
public class MaxByNameCommand implements ClientCommand {

    public void execute(List<String> tokens) {}

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

        CollectionManager cm = CollectionManager.getInstance();
        DragonX dragonWithLongestName = cm.getCollection().stream()
                .max(Comparator.comparing(dragon -> dragon.getName().length()))
                .orElse(null);

        if (dragonWithLongestName == null) {
            return "Couldn't find a dragon with the longest name";
        } else {
            return dragonWithLongestName.returnInfo();
        }
    }
}

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
 * Class, that implements console command "filter_start_with_description"
 */

@CommandInfo(name = "filter_starts_with_description", description = "display elements whose description field value starts with the given substring")
public class FilterStartsWithDescriptionCommand implements ClientCommand {

    public void execute (List<String> tokens) {}

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

        StringBuilder sb = new StringBuilder();

        String subString = tokens.get(0);

        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        collection.stream().filter(item ->
            item.getDescription().startsWith(subString)
        ).forEach(item -> {
            sb.append(item.returnInfo());
        });
        return sb.toString();
    }
}

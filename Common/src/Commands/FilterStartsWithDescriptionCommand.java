package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "filter_start_with_description"
 */

@CommandInfo(name = "filter_starts_with_description", description = "display elements whose description field value starts with the given substring")
public class FilterStartsWithDescriptionCommand implements Command{
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

        collection.stream().filter(item ->
            item.getDescription().startsWith(subString)
        ).forEach(DragonX::displayInfo);

    }
}

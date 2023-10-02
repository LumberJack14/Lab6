package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "show"
 */

@CommandInfo(name = "show", description = "print to standard output all elements of the collection in string representation")
public class ShowCommand implements Command{

    @Override
    public void execute(List<String> tokens) {

        try {
            if (!tokens.isEmpty()) {
                throw new TokenMismatchException(0);
            }
        } catch (TokenMismatchException e) {
            System.out.println(e.getMessage());
            return;
        }

        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        System.out.println("This is the collection's state at the current moment. Some changes might need to be saved.\n");

        collection.forEach(DragonX::displayInfo);
    }
}

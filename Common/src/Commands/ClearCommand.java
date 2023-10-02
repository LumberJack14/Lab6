package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Exceptions.TokenMismatchException;
import Utils.CollectionManager;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "clear"
 */

@CommandInfo(name = "clear", description = "clear the collection")
public class ClearCommand implements Command{
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
        cm.setCollection(new Vector<DragonX>());

        System.out.println("Collection was cleared. If you want to save it to the file type 'save' ");
    }
}

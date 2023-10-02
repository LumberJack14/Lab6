package Commands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Utils.CollectionManager;

import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "remove_first"
 */

@CommandInfo(name = "remove_first", description = "remove the first element from the collection")
public class RemoveFirstCommand implements Command{
    @Override
    public void execute(List<String> tokens) {
        CollectionManager cm = CollectionManager.getInstance();
        Vector<DragonX> collection = cm.getCollection();

        DragonX firstDragon;

        try {
            firstDragon = collection.get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: No dragon found");
            return;
        }
        System.out.println("This dragon will be removed:");
        firstDragon.displayInfo();

        collection.remove(firstDragon);
        System.out.println("The dragon was successfully removed!");
    }
}

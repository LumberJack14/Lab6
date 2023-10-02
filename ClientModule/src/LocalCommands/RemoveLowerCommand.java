package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Exceptions.TokenMismatchException;
import Utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Class, that implements console command "remove_lower"
 */

@CommandInfo(name = "remove_lower", description = "Remove all elements from the collection that are smaller than the given one")
public class RemoveLowerCommand implements Command {

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

        DragonX dragonToCompare = DragonBuilder.generateNewDragon();
        ClientConnectionManager connectionManager = new ClientConnectionManager();
        tokens.add("remove_lower");
        Response response = connectionManager.sendCommandToServer((ArrayList<String>) tokens, dragonToCompare);
        if (response != null) {
            System.out.println(Colors.PURPLE + "SERVER RESPONSE:\n" + Colors.RESET);
            System.out.println(response.getData());
        }
    }
}

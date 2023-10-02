package LocalCommands;


import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Exceptions.TokenMismatchException;
import Utils.ClientConnectionManager;
import Utils.Colors;
import Utils.DragonBuilder;
import Utils.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Class, that implements console command "add"
 */

@CommandInfo(name = "add", description = "add a new element to the collection")
public class AddCommand implements Command {

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

        DragonX dragon = DragonBuilder.generateNewDragon();
        ClientConnectionManager connectionManager = new ClientConnectionManager();
        tokens.add("add");
        Response response = connectionManager.sendCommandToServer((ArrayList<String>) tokens, dragon);
        if (response != null) {
            System.out.println(Colors.PURPLE + "SERVER RESPONSE:\n" + Colors.RESET);
            System.out.println(response.getData());
        }
    }
}

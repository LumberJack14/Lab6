package LocalCommands;

import Annotations.CommandInfo;
import Collection.DragonX;
import Commands.Command;
import Exceptions.TokenMismatchException;
import Utils.*;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Class, that implements console command "update"
 */

@CommandInfo(name = "update", description = "update the value of the collection element, which id is equal to the given one")
public class UpdateCommand implements Command {

    @Override
    public void execute(List<String> tokens) {
        long givenID;

        try {
            if (tokens.size() != 1) {
                throw new TokenMismatchException(1);
            }
            givenID = Long.parseLong(tokens.get(0));
        } catch (TokenMismatchException | NumberFormatException exception) {
            System.out.println(exception.getMessage());
            return;
        }

        DragonX newDragon = DragonBuilder.generateNewDragon();
        ClientConnectionManager connectionManager = new ClientConnectionManager();
        tokens.remove(0);
        tokens.add("update");
        tokens.add(String.valueOf(givenID));
        Response response = connectionManager.sendCommandToServer((ArrayList<String>) tokens, newDragon);
        if (response != null) {
            System.out.println(Colors.PURPLE + "SERVER RESPONSE:\n" + Colors.RESET);
            System.out.println(response.getData());
        }


    }
}

package Utils;

import LocalCommands.*;

import java.io.*;
import java.nio.ByteBuffer;

public class QueryValidator {

    public static ByteBuffer validateQuery(ByteBuffer buf) {

        ByteArrayInputStream bais = new ByteArrayInputStream(buf.array());
        ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);

        try {
            ObjectInputStream ois = new ObjectInputStream(bais);

            CommandWrapper cm = (CommandWrapper) ois.readObject();
            ois.close();
            Response response = validateCommand(cm);

            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(response);
            oos.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error while validating query");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return ByteBuffer.wrap(baos.toByteArray());
    }

    private static Response validateCommand(CommandWrapper cm) {
        Response response = new Response(ResponseStatus.COMMAND_NOT_FOUND);
        ServerCommandManager commandManager = new ServerCommandManager();
        ClientCommand[] clientCommands = {
                new InfoCommand(),
                new ShowCommand(),
                new AddCommand(),
                new ClearCommand(),
                new FilterStartsWithDescriptionCommand(),
                new MaxByNameCommand(),
                new RemoveAllByDescriptionCommand(),
                new RemoveById(),
                new RemoveFirstCommand(),
                new RemoveLowerCommand(),
                new UpdateCommand(),

        };
        commandManager.registerClientCommands(clientCommands);
        String data = commandManager.executeClientCommand(cm);
        response.setData(data);

        return response;
    }
}

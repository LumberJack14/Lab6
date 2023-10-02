package LocalCommands;

import Annotations.CommandInfo;
import Commands.Command;
import Utils.ServerConnectionManager;

import java.util.List;

@CommandInfo(name = "start", description = "open the server to receive connections")
public class StartCommand implements Command {
    @Override
    public void execute(List<String> tokens) {
        System.out.println("Server is started. type 'stop' to stop the server and return to CLI.");
        ServerConnectionManager connectionManager = new ServerConnectionManager();
        connectionManager.receiveConnections();
        System.out.println("Server was stopped.");
    }
}

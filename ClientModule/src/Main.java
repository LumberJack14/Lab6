import Utils.ClientCLI;
import Utils.ClientConnectionManager;

public class Main {
    public static void main(String[] args) {
        ClientConnectionManager connectionManager = new ClientConnectionManager();
        ClientCLI cli = new ClientCLI();
        cli.run();
    }
}

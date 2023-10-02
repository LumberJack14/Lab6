package Utils;

import Collection.DragonX;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class ClientConnectionManager {

    public Response sendCommandToServer(ArrayList<String> tokens, DragonX dragon) {

        CommandWrapper cm = new CommandWrapper(tokens, dragon);
        ByteArrayOutputStream baos = new ByteArrayOutputStream(2048);
        ObjectOutputStream oos;
        Response response = null;

        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(cm);

            byte[] data = baos.toByteArray();

            DatagramSocket ds = new DatagramSocket();
            InetAddress host = InetAddress.getLocalHost();

            DatagramPacket dp = new DatagramPacket(data, data.length, host, 6789);
            ds.setSoTimeout(10000);
            ds.send(dp);

            byte[] responseBuf = new byte[2048];

            dp = new DatagramPacket(responseBuf, responseBuf.length);
            ds.receive(dp);

            ByteArrayInputStream bais = new ByteArrayInputStream(responseBuf);
            ObjectInputStream ois = new ObjectInputStream(bais);
            response = (Response) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            if (e instanceof SocketTimeoutException) {
                System.out.println("Connection timeout has run out. The server is probably down.\nTry again later.");
            } else {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
        return response;
    }
}

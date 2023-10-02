package Utils;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class ServerConnectionManager {

    private static final int PORT = 6789;
    private static final int BUFFER_SIZE = 2048;


    public void receiveConnections() {
        // setting up the environment
        Selector selector = null;
        DatagramChannel dc = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            dc = DatagramChannel.open();
            SocketAddress address = new InetSocketAddress(PORT);
            dc.configureBlocking(false);
            dc.bind(address);
            selector = Selector.open();
            dc.register(selector, SelectionKey.OP_READ);

        } catch (IOException e) {
            System.out.println("Error while setting up the environment");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        //working with connections
        try {
            while (true) {
                if (bufferedReader.ready()) {
                    String str = bufferedReader.readLine();
                    if (str.equals("stop")) {
                        break;
                    } else {
                        System.out.println("Unknown command");
                    }
                }

                int count = selector.selectNow();
                if (count == 0) {
                    continue;
                }

                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter;
                for (iter = keys.iterator(); iter.hasNext(); ) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    if (key.isValid()) {
                        if (key.isReadable()) {
                            DatagramChannel serv = (DatagramChannel) key.channel();
                            ByteBuffer buf = ByteBuffer.wrap(new byte[BUFFER_SIZE]);
                            InetSocketAddress address = (InetSocketAddress) serv.receive(buf);
                            ByteBuffer responseBuf = QueryValidator.validateQuery(buf);
                            serv.send(responseBuf, address);
                            serv.configureBlocking(false);
                        }

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error while cycling connections");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        try {
            selector.close();
            dc.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

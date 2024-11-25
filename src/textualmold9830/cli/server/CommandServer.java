package textualmold9830.cli.server;

import textualmold9830.cli.commands.Command;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class CommandServer implements Runnable{
    public static List<ClientThread> clients = new ArrayList<>();
    public static ServerSocket server;
    private static Thread commandServerThread;
    int port;
    public static void startServer(int port){
        commandServerThread = new Thread(new CommandServer(port));
        commandServerThread.start();
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            System.out.println("bound server on port: " + server.getLocalPort());
            while (true) {
                clients.add(new ClientThread(server.accept()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public CommandServer(int port) {
        this.port = port;
    }
}

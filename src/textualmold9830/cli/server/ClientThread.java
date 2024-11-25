package textualmold9830.cli.server;

import textualmold9830.cli.CommandManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread{
    Socket socket;
    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (socket.isConnected()) {
                CommandManager.executeFromString(input.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ClientThread(Socket socket) {
        this.socket = socket;
        start();
    }
}

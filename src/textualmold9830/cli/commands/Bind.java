package textualmold9830.cli.commands;

import textualmold9830.cli.server.CommandServer;
import textualmold9830.cli.util.StringToInt;

public class Bind implements Command{
    @Override
    public String name() {
        return "bind";
    }

    @Override
    public void run() {
        System.out.println("Usage: bind <port>");
    }

    @Override
    public void run(String[] args) {
        if (StringToInt.isInt(args[0])) {
            CommandServer.startServer(Integer.parseInt(args[0]));
        } else {
            System.out.println(args[0] + " is not a valid port");
        }
    }
}

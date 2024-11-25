package textualmold9830.cli.commands;

import textualmold9830.cli.CommandManager;
import textualmold9830.cli.util.StringToInt;

public class RepeatCommand implements Command{
    @Override
    public String name() {
        return "repeat";
    }

    @Override
    public void run() {
        System.out.println("Usage: repeat <int> command args");
    }

    @Override
    public void run(String[] args) {
        if (args.length < 2){
            System.out.println("Too few arguments");
            return;
        }
        if (args[0] == null || args[1] == null || !StringToInt.isInt(args[0])){
            System.out.println("Invalid arguments");
            return;
        }
        int repeats = Integer.parseInt(args[0]);
        String command = args[1];
        String[] commandArgs = new String[args.length-2];
        System.arraycopy(args, 2, commandArgs, 0, args.length - 2);
        for (int i = 0; i < repeats; i++) {
            CommandManager.executeCommand(command, commandArgs);
        }
    }
}

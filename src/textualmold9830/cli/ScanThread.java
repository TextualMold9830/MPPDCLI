package textualmold9830.cli;

import textualmold9830.cli.commands.Command;

import java.util.Scanner;

public class ScanThread implements Runnable{
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];
            String[] args = new String[parts.length-1];
            System.arraycopy(parts, 1, args, 0, parts.length - 1);
            Command cmd = CommandManager.commands.get(command);
            if (cmd != null) {
                cmd.execute(args);
            } else {
                System.out.println("Could not find command: " + command);
            }
        }

    }
}

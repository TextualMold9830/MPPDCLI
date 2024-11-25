package textualmold9830.cli;

import textualmold9830.cli.commands.Command;

import java.util.Scanner;

public class ScanThread implements Runnable{
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            //We might catch some error and not fuck the whole command system
            try {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String command = parts[0];
                String[] args = new String[parts.length - 1];
                System.arraycopy(parts, 1, args, 0, parts.length - 1);
                CommandManager.executeCommand(command, args);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}

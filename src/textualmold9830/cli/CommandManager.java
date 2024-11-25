package textualmold9830.cli;

import io.github.classgraph.*;
import textualmold9830.cli.commands.Command;
import textualmold9830.cli.variables.Variable;
import textualmold9830.cli.variables.VariableManager;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CommandManager {
    public static HashMap<String, Command> commands = new HashMap<>();

    public static void initCommands() {
        String pkg = "textualmold9830.cli.commands";
        try (ScanResult scanResult =
                     new ClassGraph()
                             .enableAllInfo()         // Scan classes, methods, fields, annotations
                             .acceptPackages(pkg)     // Scan com.xyz and subpackages (omit to scan all packages)
                             .scan()) {               // Start the scan
            for (ClassInfo classInfo : scanResult.getAllClasses()) {
                System.out.println(classInfo.getSimpleName());
                if (classInfo.implementsInterface(Command.class)) {
                    Command command = (Command) classInfo.loadClass().getDeclaredConstructor().newInstance();
                    commands.put(command.name(), command);
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void executeCommand(Command command, String[] args) {
        String[] argsCopy = new String[args.length];
        boolean set = false;

        for (int i = 0; i < args.length; i++) {
            set = false;
            if (args[i] != null) {
                if (args[i].startsWith("$")) {
                    String value = VariableManager.getValue(args[i].replace("$",""));
                    if (value != null) {

                        argsCopy[i] = value;
                        set = true;
                    } else {
                        System.out.println("Variable: " + args[i].replace("$", "") + " has null value");
                    }
                }

            }
            if (!set) {
                argsCopy[i] = args[i];
            }
        }
        command.execute(argsCopy);
    }

    public static void executeCommand(String command, String[] args) {
        Command cmd = commands.get(command);
        if (cmd != null){
            executeCommand(cmd, args);
        } else {
            System.out.println("Command "+ command +" not found");
        }
    }
    public static void executeFromString(String line){
        String[] parts = line.split(" ");
        String command = parts[0];
        String[] args = new String[parts.length - 1];
        System.arraycopy(parts, 1, args, 0, parts.length - 1);
        CommandManager.executeCommand(command, args);
    }
}


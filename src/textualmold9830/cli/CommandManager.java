package textualmold9830.cli;

import io.github.classgraph.*;
import textualmold9830.cli.commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class CommandManager {
    public static HashMap<String, Command> commands = new HashMap<>();
    public static void initCommands(){
        String pkg = "textualmold9830.cli.commands";
        try (ScanResult scanResult =
                     new ClassGraph()
                             .enableAllInfo()         // Scan classes, methods, fields, annotations
                             .acceptPackages(pkg)     // Scan com.xyz and subpackages (omit to scan all packages)
                             .scan()) {               // Start the scan
            for (ClassInfo classInfo : scanResult.getAllClasses()) {
                System.out.println(classInfo.getSimpleName());
                if (classInfo.implementsInterface(Command.class)){
                    Command command = (Command) classInfo.loadClass().getDeclaredConstructor().newInstance();
                    commands.put(command.name(), command);
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}

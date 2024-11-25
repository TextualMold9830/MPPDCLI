package textualmold9830.cli.commands;

import textualmold9830.cli.variables.VariableManager;

public class DeleteCommand implements Command{
    @Override
    public String name() {
        return "delete";
    }

    @Override
    public void run() {
        System.out.println("Usage: delete variable");
    }

    @Override
    public void run(String[] args) {
        if (args.length > 0 && args[0] != null) {
            VariableManager.removeVariable(args[0]);
            System.out.println("Removed variable " + args[0]);
            return;
        }
        System.out.println("Invalid argument provided");
    }
}

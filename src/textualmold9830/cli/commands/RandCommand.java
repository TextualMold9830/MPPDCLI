package textualmold9830.cli.commands;

import textualmold9830.cli.util.StringToInt;
import textualmold9830.cli.variables.RandomBetween;
import textualmold9830.cli.variables.VariableManager;

public class RandCommand implements Command{
    @Override
    public String name() {
        return "randbetween";
    }

    @Override
    public void run() {
        System.out.println("usage: randbetween min max name");
    }

    @Override
    public void run(String[] args) {
        if (args.length > 2) {
            if (args[0] != null && args[1] != null && args[2] != null){
                if (StringToInt.isInt(args[0]) && StringToInt.isInt(args[1])){
                    VariableManager.registerVariable(args[2],
                    new RandomBetween(Integer.parseInt(args[0]), Integer.parseInt(args[1]))
                    );
                }
            }
        }
    }
}

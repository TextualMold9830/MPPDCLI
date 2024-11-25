package textualmold9830.cli.commands;

import com.watabou.pixeldungeon.actors.mobs.Goo;

public class IncrementedVariableCommand implements Command{
    @Override
    public String name() {
        return "incrementedvar";
    }

    @Override
    public void run() {
        System.out.println("Usage: incrementedvar <name> incrementBy, start");
    }

    @Override
    public void run(String[] args) {
        Command.super.run(args);
    }
}

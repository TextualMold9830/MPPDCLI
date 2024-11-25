package textualmold9830.cli.commands;

import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import textualmold9830.cli.util.StringToInt;
import textualmold9830.cli.variables.CharPosVariable;
import textualmold9830.cli.variables.VariableManager;

public class CharPosCommand implements Command{
    @Override
    public String name() {
        return "charpos";
    }

    @Override
    public void run() {
        System.out.println("Usage: charpos actor_id");
    }

    @Override
    public void run(String[] args) {
        if (args.length > 1 && StringToInt.isInt(args[1])){
            Actor actor = Actor.findById(Integer.parseInt(args[1]));
            if (actor instanceof Char){
                Char ch = (Char) actor;
                VariableManager.registerVariable(args[0], new CharPosVariable(ch));
                return;
            } else {
                System.out.println("Char id was not provided");
                return;
            }
        }
        System.out.println("please provide the id of a char as an argument");
    }
}

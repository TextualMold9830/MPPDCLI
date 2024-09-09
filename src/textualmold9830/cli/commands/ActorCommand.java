package textualmold9830.cli.commands;

import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.levels.Level;
import textualmold9830.cli.function.ListActors;
import textualmold9830.cli.util.ActorUtil;
import textualmold9830.cli.util.StringToInt;

public class ActorCommand implements Command{
    @Override
    public String name() {
        return "actor";
    }

    @Override
    public void run(String[] args) {
        switch (args[0]) {
            case "list" :
                ListActors.printActors();
                break;
            case "pos":
                posResult(args);
                break;
            case "teleport":
                teleportResult(args);
                break;
            default:
                run();
                break;
        }
    }

    @Override
    public void run() {
        System.out.println("usage:");
        System.out.println("actor list");
        System.out.println("actor pos <id>");
        System.out.println("actor teleport <id> <pos>");
    }
    public void posResult(String[] args){
        //Check argument 1
        int id = -1;
        if (args[1] != null && StringToInt.isInt(args[1])){
            id = Integer.parseInt(args[1]);
        } else {
            System.out.println("Invalid position");
            return;
        }
        if (id < 0){
            return;
        }
        System.out.println("ID: " + id);
        Actor actor = ActorUtil.findActorByID(id);
        if (actor instanceof Char c) {
            System.out.println(c.pos);
        } else {
            System.out.println("Not a character: " + actor.getClass().getSimpleName());

        }
    }
    public void teleportResult(String[] args){
        if (args[1] != null) {
            if (StringToInt.isInt(args[1])) {
                int id = Integer.parseInt(args[1]);
                if (id > -1) {
                    Actor result = Actor.findById(id);
                    if (result instanceof Char){
                        Char character = (Char) result;
                        if(StringToInt.isInt(args[2])){
                            character.pos = StringToInt.optionalInt(args[2], character.pos);
                            character.move(character.pos);
                            System.out.println("Set position of "+ character.name + " to: " + character.pos);
                        } else {
                            if (args[2] == null) {
                                System.out.println("Position argument is needed");
                            } else {
                                System.out.println("Invalid position");
                            }
                        }
                    } else {
                        System.out.println("actor with id: "+id +" is not a character");
                    }
                } else {
                    System.out.println("Invalid id");
                }
            }
        } else {
            System.out.println("invalid actor id");
        }
    }
}

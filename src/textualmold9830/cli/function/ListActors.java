package textualmold9830.cli.function;

import com.watabou.pixeldungeon.Dungeon;
import com.watabou.pixeldungeon.actors.Actor;
import com.watabou.pixeldungeon.actors.Char;
import com.watabou.pixeldungeon.actors.buffs.Buff;
import com.watabou.pixeldungeon.levels.Level;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class ListActors {
    public static void printActors(){
        ArrayList<Actor> actors = new ArrayList<>(Actor.all());
        actors.sort(Comparator.comparingInt(Actor::id));
        for (Actor actor : actors) {

           if (actor instanceof Char) {
               Char character = (Char) actor;
               System.out.println(String.format("%3d %10s %4d(%2d,%2d) %d/%d", actor.id(), actor.getClass().getSimpleName(), character.pos, character.pos% Level.WIDTH, character.pos/Level.WIDTH, character.getHP(), character.getHT()));
           } else if (actor instanceof Buff b) {
               System.out.println(String.format("%3d %10s, %10s", actor.id(), actor.getClass().getSimpleName(), b.target));

           }

           else {
               System.out.println(String.format("%3d %10s", actor.id(), actor.getClass().getSimpleName()));

           }

        }
    }
}

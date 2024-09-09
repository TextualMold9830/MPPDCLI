package textualmold9830.cli.util;

import com.watabou.pixeldungeon.actors.Actor;

public class ActorUtil {
    public static Actor findActorByID(int id){
        for (Actor actor : Actor.all()) {
            if (actor.id() == id) {
                return actor;
            }
        }
        return null;
    }
}

package textualmold9830.cli.variables;

import com.watabou.pixeldungeon.actors.Char;

public class CharPosVariable implements Variable{
    public Char ch;

    @Override
    public String getValue() {
        if (ch.isAlive()){
            return String.valueOf(ch.pos);
        }
        return null;
    }

    public CharPosVariable(Char ch) {
        this.ch = ch;
    }
}
